package br.edu.fpo.spa.api.controller;

import br.edu.fpo.spa.api.controller.dto.EstagiarioDTO;
import br.edu.fpo.spa.api.model.AreaDeEstagio;
import br.edu.fpo.spa.api.model.Estagiario;
import br.edu.fpo.spa.api.repository.AreaDeEstagioRepository;
import br.edu.fpo.spa.api.repository.EstagiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/area-de-estagio")
public class AreaDeEstagioController implements Serializable {

    @Autowired
    private AreaDeEstagioRepository repository;
    @Autowired
    private EstagiarioRepository estagiarioRepository;

    @GetMapping
    public ResponseEntity<List<AreaDeEstagio>> todos(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public AreaDeEstagio obterPorId(@PathVariable Integer id){
        return repository.findById(id)
                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Área de Estágio não encontrada!"));
    }

    @GetMapping("/estagiarios/{id}")
    public List<Estagiario> obterEstagiariosPorId(@PathVariable Integer id){
        AreaDeEstagio a = repository.findById(id).get();
        return estagiarioRepository.getByArea(a);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AreaDeEstagio salvar(@RequestBody AreaDeEstagio area){
        return repository.save(area);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editar(@PathVariable Integer id, @RequestBody() AreaDeEstagio areaDeEstagio){
        repository.findById(id).map(area -> {
                area.setNome(areaDeEstagio.getNome());
                return repository.save(area);
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Área de Estágio não encontrada!"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        repository.findById(id).map(area -> {
            repository.delete(area);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Área de Estágio não encontrada!"));
    }
}
