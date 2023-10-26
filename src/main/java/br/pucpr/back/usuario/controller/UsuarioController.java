package br.pucpr.back.usuario.controller;
import br.pucpr.back.usuario.model.entity.Usuario;
import br.pucpr.back.usuario.model.entity.TokenDto;
import br.pucpr.back.usuario.model.entity.UsuarioDto;
import br.pucpr.back.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private ModelMapper modelMapper = new ModelMapper();;

    @PostMapping("/salvar")
    public ResponseEntity<TokenDto> salvar(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario usuarioSalvo = usuarioService.salvar(usuario);
            var token = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTcwMzM3MDQ2OSwiaWF0IjoxNjkyODI5NjY5fQ.eTtSoeUUNkyORAJ-_vnutgQJx-ifRvcUjhHPHTY3N7Q";
            TokenDto tokenDto = new TokenDto(usuarioSalvo.getId(), usuarioSalvo.getTipo(), token);
            return new ResponseEntity(tokenDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody UsuarioDto usuarioDto) {
        try {
            Optional<Usuario> usuario = usuarioService.findByEmailAndPassword(usuarioDto.getEmail(), usuarioDto.getSenha());
            var token = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTcwMzM3MDQ2OSwiaWF0IjoxNjkyODI5NjY5fQ.eTtSoeUUNkyORAJ-_vnutgQJx-ifRvcUjhHPHTY3N7Q";

            TokenDto tokenDto = new TokenDto(usuario.get().getId(), usuario.get().getTipo(), token);
            return new ResponseEntity(tokenDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("obterDados")
    public ResponseEntity<Usuario> obterDados(int usuarioId) {
        try {
            return new ResponseEntity(usuarioService.findById(usuarioId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/atualizarTelefone")
    public ResponseEntity<Boolean> atualizarTelefone(int usuarioId, String telefone) {
        try {
            usuarioService.atualizarTelefone(usuarioId, telefone);
            return new ResponseEntity(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
