package br.pucpr.back.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.pucpr.back.usuario.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByCpf(String cpf);
    boolean existsByCrm(String crm);
    boolean existsByEmail(String email);

    Optional<Usuario> findByEmailAndSenha(String email, String senha);

}
