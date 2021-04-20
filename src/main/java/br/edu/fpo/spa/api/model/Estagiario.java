package br.edu.fpo.spa.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estagiario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 150)
    private String nome;
    @Column(nullable = false, length = 150)
    private String matricula;
    @Column(nullable = false, length = 150)
    private String semestre;
    @Column(nullable = false, length = 11)
    private String telefone;
    @Column(nullable = false, length = 150)
    private String email;
    @ManyToOne
    @JoinColumn(name = "id_area_de_estagio")
    private AreaDeEstagio area;
    @ManyToOne
    @JoinColumn(name = "id_supervisor")
    private Supervisor supervisor;

}
