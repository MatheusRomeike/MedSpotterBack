package br.pucpr.back.agendamento.model.entity;

import br.pucpr.back.usuario.model.entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity
@Table(name = "AGENDAMENTO")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "DTAGENDAMENTO")
    private LocalDateTime dataAgendamento;

    @Column(name = "TPSTATUSAGENDAMENTO")
    private short statusAgendamento;

    @ManyToOne
    @JoinColumn(name = "MEDICOID")
    private Usuario medico;

    @ManyToOne
    @JoinColumn(name = "PACIENTEID")
    private Usuario paciente;

}
