package com.odiase.library_management_system.common.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtils {
    public static Integer toZeroBasedPage(Integer oneBasedPage) {
        return oneBasedPage < 1 ? 0 : oneBasedPage - 1;
    }

    public static Pageable createPageRequest(Integer page, Integer size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return PageRequest.of(page, size, sort);
    }
}
