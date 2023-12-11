package org.example.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class GetPageRequest {
    public static Pageable of(int page, int size, String sort, int direction) {
        Pageable pageRequest;
        if (direction == 1) {
            pageRequest = PageRequest.of(page, size, Sort.by(sort).ascending());
        } else {
            pageRequest = PageRequest.of(page, size, Sort.by(sort).descending());
        }
        return pageRequest;
    }
}
