package br.pucpr.back.agendamento.service;

import br.pucpr.back.agendamento.model.entity.Agendamento;
import br.pucpr.back.agendamento.model.entity.AgendamentoDto;
import br.pucpr.back.agendamento.repository.AgendamentoRepository;
import br.pucpr.back.usuario.model.entity.Usuario;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {


    @Autowired
    private AgendamentoRepository agendamentoRepository;


    public List<AgendamentoDto> getAllAgendamentos() {
        return agendamentoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AgendamentoDto getAgendamentoById(Integer id) {
        Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(id);
        return agendamentoOptional.map(this::convertToDto).orElse(null);
    }

    public AgendamentoDto createAgendamento(AgendamentoDto AgendamentoDto) {
        Agendamento agendamento = convertToEntity(AgendamentoDto);
        agendamento = agendamentoRepository.save(agendamento);
        return convertToDto(agendamento);
    }

    public AgendamentoDto updateAgendamento(Integer id, AgendamentoDto AgendamentoDto) {
        Optional<Agendamento> existingAgendamentoOptional = agendamentoRepository.findById(id);
        if (existingAgendamentoOptional.isPresent()) {
            Agendamento existingAgendamento = existingAgendamentoOptional.get();
            BeanUtils.copyProperties(AgendamentoDto, existingAgendamento);
            existingAgendamento.setId(id);
            existingAgendamento = agendamentoRepository.save(existingAgendamento);
            return convertToDto(existingAgendamento);
        }
        return null;
    }

    public void deleteAgendamento(Integer id) {
        agendamentoRepository.deleteById(id);
    }

    private AgendamentoDto convertToDto(Agendamento agendamento) {
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        BeanUtils.copyProperties(agendamento, agendamentoDto);
        agendamentoDto.medicoId = agendamento.getMedico();
        agendamentoDto.pacienteId= agendamento.getPaciente();
        return agendamentoDto;
    }

    private Agendamento convertToEntity(AgendamentoDto AgendamentoDto) {
        Agendamento agendamento = new Agendamento();
        BeanUtils.copyProperties(AgendamentoDto, agendamento);
        // Carregue o médico e o paciente do banco de dados, se necessário
        // Exemplo: agendamento.setMedico(usuarioRepository.findById(AgendamentoDto.getMedicoId()).orElse(null));
        // Exemplo: agendamento.setPaciente(usuarioRepository.findById(AgendamentoDto.getPacienteId()).orElse(null));
        return agendamento;
    }

    public List<AgendamentoDto> getAgendamentosByData(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<Agendamento> agendamentos = agendamentoRepository.getAgendamentosByData(startDateTime, endDateTime);
        return agendamentos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
