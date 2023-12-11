function createPaginationGet(response) {
    const pageCorrection = 1; // Corrective constant. f() fetchReports uses SpringBoot pagination value 0 for page 1.
    let pages = response.totalPages;
    let page = response.pageable.pageNumber + pageCorrection;
    let str = '<ul>';
    let active;
    let pageCutLow = page - 1;
    let pageCutHigh = page + 1;

    if (page > 1) {
        str += `<li class="page-item previous no"><a onclick="getReports(${(page - 1) - pageCorrection})"><</a></li>`;
    }
    if (pages < 6) {
        for (let p = 1; p <= pages; p++) {
            active = page === p ? "active" : "no";
            str += `<li class="${active}"><a onclick="getReports(${p - pageCorrection})">${p}</a></li>`;
        }
    } else {
        if (page > 2) {
            str += `<li class="no page-item"><a onclick="getReports(${1 - pageCorrection})">1</a></li>`;
            if (page > 3) {
                str += `<li class="out-of-range"><a onclick="getReports(${(page - 2) - pageCorrection})">...</a></li>`;
            }
        }
        if (page === 1) {
            pageCutHigh += 2;
        } else if (page === 2) {
            pageCutHigh += 1;
        }
        if (page === pages) {
            pageCutLow -= 2;
        } else if (page === pages - 1) {
            pageCutLow -= 1;
        }
        for (let p = pageCutLow; p <= pageCutHigh; p++) {
            if (p === 0) {
                p += 1;
            }
            if (p > pages) {
                continue
            }
            active = page === p ? "active" : "no";
            str += `<li class="page-item ${active}"><a onclick="getReports(${p - pageCorrection})">${p}</a></li>`;
        }
        if (page < pages - 1) {
            if (page < pages - 2) {
                str += `<li class="out-of-range"><a onclick="getReports(${(page + 2) - pageCorrection})">...</a></li>`;
            }
            str += `<li class="page-item no"><a onclick="getReports(${pages - pageCorrection})">${pages}</a></li>`;
        }
    }
    if (page < pages) {
        str += `<li class="page-item next no"><a onclick="getReports(${(page + 1) - pageCorrection})">></a></li>`;
    }
    str += '</ul>';
    document.getElementById('pagination').innerHTML = str;
}