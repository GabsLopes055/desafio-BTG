package com.gabs_desafio_btg.DTOs;

import java.math.BigDecimal;

public record OrderItemEvent(String produto,
                             Integer quantidade,
                             BigDecimal preco) {
}
