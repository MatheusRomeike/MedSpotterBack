package br.pucpr.back.agendamento.model.entity;

import java.time.LocalDateTime;

public class AgendamentoDto {
    private Integer id;
    private LocalDateTime dataAgendamento;
    private short statusAgendamento;
    private Integer medicoId;
    private Integer pacienteId;
}
