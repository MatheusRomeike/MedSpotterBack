package br.pucpr.back.agendamento.model.entity;

import java.time.LocalDateTime;

public class AgendamentoDto {
    public Integer id;
    public LocalDateTime dataAgendamento;
    public short statusAgendamento;
    public Integer medicoId;
    public Integer pacienteId;
}
