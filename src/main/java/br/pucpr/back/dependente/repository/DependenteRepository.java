package br.pucpr.back.dependente.repository;

import br.pucpr.back.dependente.model.entity.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DependenteRepository extends JpaRepository<Dependente, Integer> {
    Optional<List<Dependente>> findByUsuarioId(int id);



}
