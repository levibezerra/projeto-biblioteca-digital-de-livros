package com.example.bd_nosql.projeto_biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDto {

    private String id;
    private String titulo;
    private String autor;
    private String categoria;
    private String status;
    private Integer avaliacao;
}