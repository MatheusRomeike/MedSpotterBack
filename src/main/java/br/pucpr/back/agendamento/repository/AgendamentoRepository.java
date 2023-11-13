package br.pucpr.back.agendamento.repository;

import br.pucpr.back.agendamento.model.entity.Agendamento;
import br.pucpr.back.agendamento.model.entity.AgendamentoDto;
import br.pucpr.back.usuario.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

        boolean existsByDataAgendamento(LocalDateTime startDateTime);

}
