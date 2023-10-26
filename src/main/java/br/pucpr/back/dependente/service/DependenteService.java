package br.pucpr.back.dependente.service;

import br.pucpr.back.dependente.model.entity.Dependente;
import br.pucpr.back.dependente.repository.DependenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DependenteService {

    @Autowired
    private DependenteRepository dependenteRepository;

    public Dependente salvar(Dependente dependente) {
        return dependenteRepository.save(dependente);
    }

    public Optional<List<Dependente>> buscarPorUsuarioId(int id) {
        return dependenteRepository.findByUsuarioId(id);
    }

    public Dependente alterarVigencia(int id) {
        Optional<Dependente> dependente = dependenteRepository.findById(id);
        if (dependente.isPresent()) {
            Dependente dependenteAlterado = dependente.get();
            dependenteAlterado.setVigente(!dependenteAlterado.getVigente());
            return dependenteRepository.save(dependenteAlterado);
        }
        return null;
    }

}
