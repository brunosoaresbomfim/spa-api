package br.edu.fpo.spa.api.repository;

import br.edu.fpo.spa.api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    @Query("from Agendamento a join fetch a.cliente join fetch a.supervisor s")
    @Override
    List<Agendamento> findAll();

    @Query("from Agendamento a join fetch a.cliente join fetch a.supervisor s where a.id = :integer")
    @Override
    Optional<Agendamento> findById(@Param("integer") Integer integer);
}
