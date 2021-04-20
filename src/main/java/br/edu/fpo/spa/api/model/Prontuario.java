package br.edu.fpo.spa.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prontuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String demanda;
    @Column(nullable = false)
    private String status;
    //private
    @Column(name = "inicio_atendimento", nullable = false)
    private LocalDateTime inicioAtendimento;

    @Column(name = "termino_atendimento")
    private LocalDateTime terminoAtendimento;

    @JoinColumn(name = "id_agendamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Agendamento agendamento;

    @JoinColumn(name = "id_estagiario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estagiario estagiario;


}
