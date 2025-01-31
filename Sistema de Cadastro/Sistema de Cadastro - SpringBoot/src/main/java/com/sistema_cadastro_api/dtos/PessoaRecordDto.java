package com.sistema_cadastro_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaRecordDto(@NotBlank String nome, @NotBlank String email, @NotNull int idade,
                              @NotNull double altura) {
}
