package br.pucpr.back.agendamento.controller;

import br.pucpr.back.agendamento.model.entity.Agendamento;
import br.pucpr.back.agendamento.model.entity.AgendamentoDto;
import br.pucpr.back.agendamento.service.AgendamentoService;
import br.pucpr.back.usuario.model.entity.TokenDto;
import br.pucpr.back.usuario.model.entity.Usuario;
import br.pucpr.back.usuario.model.entity.UsuarioDto;
import br.pucpr.back.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    private ModelMapper modelMapper = new ModelMapper();;


    @GetMapping
    public ResponseEntity<List<AgendamentoDto>> getAllAgendamentos() {
        try {
            List<AgendamentoDto> agendamentos = agendamentoService.getAllAgendamentos();
            return new ResponseEntity<>(agendamentos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDto> getAgendamentoById(@PathVariable Integer id) {
        try {
            AgendamentoDto AgendamentoDto = agendamentoService.getAgendamentoById(id);
            if (AgendamentoDto != null) {
                return new ResponseEntity<>(AgendamentoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<AgendamentoDto> createAgendamento(@RequestBody AgendamentoDto AgendamentoDto) {
        try {
            AgendamentoDto createdAgendamento = agendamentoService.createAgendamento(AgendamentoDto);
            return new ResponseEntity<>(createdAgendamento, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoDto> updateAgendamento(@PathVariable Integer id, @RequestBody AgendamentoDto AgendamentoDto) {
        try {
            AgendamentoDto updatedAgendamento = agendamentoService.updateAgendamento(id, AgendamentoDto);
            if (updatedAgendamento != null) {
                return new ResponseEntity<>(updatedAgendamento, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Integer id) {
        try {
            agendamentoService.deleteAgendamento(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/obterPorId")
    public ResponseEntity<AgendamentoDto> obterPorId(@RequestParam("agendamentoId") int agendamentoId) {
        try {
            AgendamentoDto AgendamentoDto = agendamentoService.getAgendamentoById(agendamentoId);
            return new ResponseEntity<>(AgendamentoDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

