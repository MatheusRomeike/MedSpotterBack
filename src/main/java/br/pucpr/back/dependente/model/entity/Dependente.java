package br.pucpr.back.dependente.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@Entity
@Table(name = "DEPENDENTE")
public class Dependente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "usuarioId")
    private Integer usuarioId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "dataNascimento")
    private Date dataNascimento;

    @Column(name = "grauParentesco")
    private String grauParentesco;

    @Column(name = "vigente")
    private Boolean vigente;
}