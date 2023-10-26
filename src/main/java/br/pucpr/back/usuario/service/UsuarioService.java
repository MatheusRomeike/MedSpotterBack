package br.pucpr.back.usuario.service;

import br.pucpr.back.usuario.model.entity.Usuario;
import br.pucpr.back.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        if (usuario.getTipo().equals("paciente")) {
            if (usuarioRepository.existsByCpf(usuario.getCpf()))
                throw new RuntimeException("CPF já cadastrado");
        }
        else {
            if (usuarioRepository.existsByCrm(usuario.getCrm()))
                throw new RuntimeException("CRM já cadastrado");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail()))
            throw new RuntimeException("Email já cadastrado");
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findByEmailAndPassword(String email, String password) {
        Optional<Usuario> usuario =  usuarioRepository.findByEmailAndSenha(email, password);
        if (usuario.isEmpty())
            throw new RuntimeException("Usuário não encontrado");
        return usuario;

    }

    public Optional<Usuario> findById(int id) {
        Optional<Usuario> usuario =  usuarioRepository.findById(id);
        if (usuario.isEmpty())
            throw new RuntimeException("Usuário não encontrado");
        return usuario;

    }

    public void atualizarTelefone(int usuarioId, String telefone) {
        Optional<Usuario> usuario =  usuarioRepository.findById(usuarioId);
        if (usuario.isEmpty())
            throw new RuntimeException("Usuário não encontrado");
        usuario.get().setTelefone(telefone);
        usuarioRepository.save(usuario.get());
    }

}
