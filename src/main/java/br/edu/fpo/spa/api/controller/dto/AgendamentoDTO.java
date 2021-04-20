package br.edu.fpo.spa.api.controller.dto;

import br.edu.fpo.spa.api.model.Cliente;
import br.edu.fpo.spa.api.model.Supervisor;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class AgendamentoDTO {

    private Integer id;
    private String dataTriagem;
    private String horaTriagem;
    private String comparecimento;
    private String justificativa;

    private Integer idCliente;
    private Integer idSupervisor;

}
