package br.edu.fpo.spa.api.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EstagiarioDTO {

    private String nome;
    private String matricula;
    private String semestre;
    private String telefone;
    private String email;
    private Integer area;
    private Integer supervisor;
}
