package br.edu.fpo.spa.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;

public class Responsavel implements Serializable {

    private Integer id;
    private String mae;
    private String pai;
}
