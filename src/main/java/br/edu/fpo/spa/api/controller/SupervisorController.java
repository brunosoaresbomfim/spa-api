package br.edu.fpo.spa.api.controller;

import br.edu.fpo.spa.api.model.Supervisor;
import br.edu.fpo.spa.api.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/supervisor")
public class SupervisorController implements Serializable {

    @Autowired
    private SupervisorRepository repository;

    @GetMapping
    public ResponseEntity<List<Supervisor>> todos(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public Supervisor obterPorId(@PathVariable Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supervisor não encontrada!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Supervisor salvar(@RequestBody Supervisor supervisor){
        return repository.save(supervisor);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editar(@PathVariable Integer id, @RequestBody() Supervisor supervisorUpdate){
        repository.findById(id).map(supervisor -> {
            supervisor.setNome(supervisorUpdate.getNome());
            supervisor.setEmail(supervisorUpdate.getEmail());
            supervisor.setMatricula(supervisorUpdate.getMatricula());
            supervisor.setNumeroCRM(supervisorUpdate.getNumeroCRM());
            supervisor.setTelefone(supervisorUpdate.getTelefone());
            return repository.save(supervisor);
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supervisor não encontrada!"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        repository.findById(id).map(supervisor -> {
            repository.delete(supervisor);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supervisor não encontrada!"));
    }
}
