package br.edu.fpo.spa.api.repository;

import br.edu.fpo.spa.api.model.AreaDeEstagio;
import br.edu.fpo.spa.api.model.Estagiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstagiarioRepository extends JpaRepository<Estagiario, Integer> {

    List<Estagiario> getByArea(AreaDeEstagio area);
}
