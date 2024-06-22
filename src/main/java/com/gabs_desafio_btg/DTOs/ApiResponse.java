package com.gabs_desafio_btg.DTOs;

import java.util.List;

public record ApiResponse<T>(List<T> data, PaginationResponse paginationResponse) {
}
