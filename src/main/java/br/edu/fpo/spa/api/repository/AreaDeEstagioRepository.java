package br.edu.fpo.spa.api.repository;

import br.edu.fpo.spa.api.model.AreaDeEstagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaDeEstagioRepository extends JpaRepository<AreaDeEstagio, Integer> {
}
