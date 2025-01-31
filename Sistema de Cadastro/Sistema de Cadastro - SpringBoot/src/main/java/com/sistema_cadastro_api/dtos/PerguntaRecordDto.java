package com.sistema_cadastro_api.dtos;

import jakarta.validation.constraints.NotBlank;

public record PerguntaRecordDto(@NotBlank String descricao) {
}
