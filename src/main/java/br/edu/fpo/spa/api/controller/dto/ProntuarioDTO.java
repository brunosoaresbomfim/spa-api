package br.edu.fpo.spa.api.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProntuarioDTO {

    private Integer id;
    private String demanda;
    private String status;
    private String inicioAtendimento;
    private String terminoAtendimento;
    private Integer agendamento;
    private Integer estagiario;
}
