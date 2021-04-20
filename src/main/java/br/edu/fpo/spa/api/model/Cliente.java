package br.edu.fpo.spa.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 150, nullable = false)
    private String nome;
    @Column(length = 3, nullable = false)
    private String idade;
    @Column(length = 15, nullable = false)
    private String sexo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @Column(length = 150, nullable = false)
    private String naturalidade;
    @Column(length = 150, nullable = false)
    private String estadoCivil;
    @Column(length = 150, nullable = false)
    private String grauInstituicao;
    @Column(length = 11, nullable = false)
    private String telefone;
    @Column(length = 150, nullable = false)
    private String logradouro;
    @Column(length = 150, nullable = false)
    private String numero;
    @Column(length = 8, nullable = false)
    private String cep;
    @Column(length = 150, nullable = false)
    private String bairro;
    @Column(length = 150, nullable = false)
    private String cidade;
    @Column(length = 150, nullable = false)
    private String estado;
    @Column(length = 150)
    private String complemento;
    @Column(length = 150, nullable = false)
    private String pesquisa;
}
