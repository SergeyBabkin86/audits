let sortingPara;
let order;
let size;
let prevCell;

function getReports(page) {
    const keyword = document.getElementById('keyword').value;
    $.ajax({
        url: '/api/v1/reports',
        type: 'GET',
        data: {
            keyword: keyword,
            page: page,
            size: size,
            sort: sortingPara,
            order: order
        },
        success: function (response) {
            createTable(response);
            $('pagination').empty();
            createPaginationGet(response);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function createTable(response) {
    $("#table-rep tr>td").remove();
    $.each(response.content, (i, report) => {
        let row = '<tr>' +
            '<td style="text-align: center; white-space: nowrap">' + report.status + '</td>' +
            `<td><a id="edit-report" data-toggle="modal" href="#reportEditDialog" data-id="${report.id}">${report.number}</a></td>` +
            '<td>' + report.subject + '</td>' +
            '<td>' + report.location + '</td>' +
            '<td>' + report.auditedBy + '</td>' +
            '<td>' + report.leadAuditor + '</td>' +
            '<td style="text-align: center; white-space: nowrap">' + report.endDate + '</td>' +
            '</tr>';
        $('#table-rep tbody').append(row);
    });
}

$(document).ready(function () {
    (function () {
        getReports(0);
    })();

    $('#reportEditDialog').on('shown.bs.modal', function (event) {
        let button = $(event.relatedTarget)
        let reportId = button.data('id')
        if (reportId !== undefined) {
            $("#myForm input, #myForm select, #myForm textarea").attr('disabled', true);
        }
        if (reportId) {
            $.get({
                url: '/api/v1/reports/' + reportId,
                success: (data) => {
                    let modal = $(this)

                    document.getElementById('reportEditDialogLabel').textContent = "Audit report No " + data.number;

                    if (data.schedule === "SCHEDULED") {
                        $('#radio-scheduled').prop("checked", true);
                        $('#radio-unscheduled').prop("checked", false);
                    } else {
                        $("#radio-scheduled").prop("checked", false);
                        $("#radio-unscheduled").prop("checked", true);
                    }

                    if (data.type === "INTERNAL") {
                        $('#radio-internal').prop("checked", true);
                        $('#radio-external').prop("checked", false);
                    } else {
                        $("#radio-internal").prop("checked", false);
                        $("#radio-external").prop("checked", true);
                    }

                    if (data.form === "ONSITE") {
                        $('#radio-onsite').prop("checked", true);
                        $('#radio-desktop').prop("checked", false);
                    } else {
                        $("#radio-onsite").prop("checked", false);
                        $("#radio-desktop").prop("checked", true);
                    }
                    modal.find('#report-id').val(data.id)
                    modal.find('#report-organisation').val(data.organisation)
                    modal.find('#report-department').val(data.department)
                    modal.find('#report-location').val(data.location)
                    modal.find('#report-manager').val(data.manager)
                    modal.find('#report-scope').val(data.scope)
                    modal.find('#report-schedule').val(data.schedule)
                    modal.find('#report-type').val(data.type)
                    modal.find('#report-form').val(data.form)
                    modal.find('#report-subject').val(data.subject)
                    modal.find('#report-criteria').val(data.criteria)
                    modal.find('#report-start-date').val(data.startDate)
                    modal.find('#report-end-date').val(data.endDate)
                    modal.find('#report-audited-by').val(data.auditedBy)
                    modal.find('#report-lead-auditor').val(data.leadAuditor)
                    modal.find('#report-auditor').val(data.auditor)
                    modal.find('#report-expert').val(data.expert)
                    modal.find('#report-trainee').val(data.trainee)
                    modal.find('#report-auditee').val(data.auditee)
                },
                error: (err) => {
                    alert(err);
                }
            });
        }
    })

    $('#save-report-button').click(function () {
        let modal = $('#reportEditDialog')
        let formSchedule = $('input[name="report-schedule-radio"]:checked').val();
        let formType = $('input[name="report-type-radio"]:checked').val();
        let formValue = $('input[name="report-form-radio"]:checked').val();

        let report = {
            organisation: modal.find('#report-organisation').val(),
            department: modal.find('#report-department').val(),
            location: modal.find('#report-location').val(),
            manager: modal.find('#report-manager').val(),
            scope: modal.find('#report-scope').val(),
            schedule: formSchedule,
            type: formType,
            form: formValue,
            subject: modal.find('#report-subject').val(),
            criteria: modal.find('#report-criteria').val(),
            startDate: modal.find('#report-start-date').val(),
            endDate: modal.find('#report-end-date').val(),
            auditedBy: modal.find('#report-audited-by').val(),
            leadAuditor: modal.find('#report-lead-auditor').val(),
            auditor: modal.find('#report-auditor').val(),
            expert: modal.find('#report-expert').val(),
            trainee: modal.find('#report-trainee').val(),
            auditee: modal.find('#report-auditee').val(),
        };
        if (modal.find('#report-id').val()) {
            report.id = modal.find('#report-id').val()
            $.ajax({
                url: '/api/v1/reports/update',
                type: 'POST',
                data: JSON.stringify(report),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: () => {
                    console.log("Audit report with id: " + report.id + "has been updated.");
                    location.reload()
                },
                error: (err) => {
                    alert(err);
                }
            })
        } else {
            $.ajax({
                url: '/api/v1/reports',
                type: 'POST',
                data: JSON.stringify(report),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: () => {
                    console.log("Audit report has been saved with id: " + report.id);
                    location.reload()
                },
                error: (err) => {
                    alert(err);
                }
            })
        }
    });

    $('#btn-fltr-apply').click(function () {
        size = document.getElementById('sel-size').value;
        $('#filterDialog').modal('hide');
        getReports(0);
    });

    $("#table-rep th").click(function () {
        const currentCell = $(this).closest('th');
        if (prevCell === undefined) {
            prevCell = currentCell;
        }
        if (currentCell.attr('data-sort') !== prevCell.attr('data-sort')) {
            $(prevCell).removeClass();
            prevCell = currentCell;
        }
        if (sortingPara === undefined) {
            sortingPara = currentCell.attr('data-sort');
            order = -1;
        } else if (currentCell.attr('data-sort') === sortingPara && order === 1) {
            order = -1;
        } else if (currentCell.attr('data-sort') === sortingPara && order === -1) {
            order = 1;
        } else {
            sortingPara = currentCell.attr('data-sort');
            order = -1;
        }
        if ($(this).hasClass('desc') || $(this).hasClass('asc')) {
            $(this).toggleClass('asc desc');
        } else {
            $(this).addClass('desc');
        }
        getReports(0);
    });
});


$(document).on("click", "#delete-report", function () {
    let reportId = document.getElementById('report-id').value;
    $.ajax({
        url: '/api/v1/reports/' + reportId,
        type: "DELETE",
        success: function () {
            console.log("report id: " + reportId + "has been deleted.");
            location.reload()
        },
        error: (err) => {
            alert(err);
        }
    })
});

$(document).on("click", "#edit-report-button", function () {
    $("#myForm input, #myForm select, #myForm textarea").attr('disabled', false);
});

$(document).on("click", ".close", function () {
    location.reload()
});

$(document).on("click", "#btn-reload", function () {
    location.reload()
});

$(document).on("change", "#keyword", function () {
    getReports(0);
});

$(document).on("search", "#keyword", function () {
    getReports(0);
});










