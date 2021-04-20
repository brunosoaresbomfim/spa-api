package br.edu.fpo.spa.api.controller;
import br.edu.fpo.spa.api.controller.dto.EstagiarioDTO;
import br.edu.fpo.spa.api.model.AreaDeEstagio;
import br.edu.fpo.spa.api.model.Estagiario;
import br.edu.fpo.spa.api.model.Supervisor;
import br.edu.fpo.spa.api.repository.AreaDeEstagioRepository;
import br.edu.fpo.spa.api.repository.EstagiarioRepository;
import br.edu.fpo.spa.api.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/estagiario")
public class EstagiarioController implements Serializable {

    private final EstagiarioRepository estagiarioRepository;
    private final AreaDeEstagioRepository areaDeEstagioRepository;
    private final SupervisorRepository supervisorRepository;

    @Autowired
    public EstagiarioController(EstagiarioRepository estagiarioRepository, AreaDeEstagioRepository areaDeEstagioRepository, SupervisorRepository supervisorRepository){
         this.estagiarioRepository = estagiarioRepository;
         this.areaDeEstagioRepository = areaDeEstagioRepository;
         this.supervisorRepository = supervisorRepository;
    }

    @GetMapping
    public List<Estagiario> todos(){
        return estagiarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Estagiario obterPorId(@PathVariable Integer id){
        return estagiarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estagiário não encontrado!"));
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Estagiario salvar(@RequestBody EstagiarioDTO estagiarioDTO){
        AreaDeEstagio a = areaDeEstagioRepository.findById(estagiarioDTO.getArea()).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Área de Estágio inexistente."));

        Supervisor s = supervisorRepository.findById(estagiarioDTO.getSupervisor()).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Supervisor inexistente."));

        Estagiario e = new Estagiario();
        e.setNome(estagiarioDTO.getNome());
        e.setEmail(estagiarioDTO.getEmail());
        e.setMatricula(estagiarioDTO.getMatricula());
        e.setSemestre(estagiarioDTO.getSemestre());
        e.setTelefone(estagiarioDTO.getTelefone());
        e.setArea(a);
        e.setSupervisor(s);
        return estagiarioRepository.save(e);
    }
}
