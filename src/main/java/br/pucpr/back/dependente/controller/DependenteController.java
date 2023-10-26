package br.pucpr.back.dependente.controller;

import br.pucpr.back.dependente.model.entity.Dependente;
import br.pucpr.back.dependente.service.DependenteService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dependente")
public class DependenteController {

    @Autowired
    private DependenteService dependenteService;

    private ModelMapper modelMapper = new ModelMapper();;

    @PostMapping("/salvar")
    public ResponseEntity<Dependente> salvar(@RequestBody Dependente dependente) {
        try {
            Dependente dependenteSalvo = dependenteService.salvar(dependente);
            return new ResponseEntity(dependenteSalvo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Dependente>> listar(Integer usuarioId) {
        try {
            return new ResponseEntity(dependenteService.buscarPorUsuarioId(usuarioId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/alterarVigencia")
    public ResponseEntity<Dependente> alterarVigencia(Integer id) {
        try {
            return new ResponseEntity(dependenteService.alterarVigencia(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
