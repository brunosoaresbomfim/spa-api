package br.edu.fpo.spa.api.controller;

import br.edu.fpo.spa.api.model.Cliente;
import br.edu.fpo.spa.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController implements Serializable {

    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public ResponseEntity<List<Cliente>> todos(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public Cliente obterPorId(@PathVariable Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrada!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editar(@PathVariable Integer id, @RequestBody() Cliente clienteUpdate){
        repository.findById(id).map(cliente -> {
            cliente.setNome(clienteUpdate.getNome());
            cliente.setBairro(clienteUpdate.getBairro());
            cliente.setCep(clienteUpdate.getCep());
            cliente.setCidade(clienteUpdate.getCidade());
            cliente.setComplemento(clienteUpdate.getComplemento());
            cliente.setDataNascimento(clienteUpdate.getDataNascimento());
            cliente.setEstado(clienteUpdate.getEstado());
            cliente.setEstadoCivil(clienteUpdate.getEstadoCivil());
            cliente.setGrauInstituicao(clienteUpdate.getGrauInstituicao());
            cliente.setIdade(clienteUpdate.getIdade());
            cliente.setLogradouro(clienteUpdate.getLogradouro());
            cliente.setNaturalidade(clienteUpdate.getNaturalidade());
            cliente.setNumero(clienteUpdate.getNumero());
            cliente.setPesquisa(clienteUpdate.getPesquisa());
            cliente.setSexo(clienteUpdate.getSexo());
            cliente.setTelefone(clienteUpdate.getTelefone());

            return repository.save(cliente);
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrada!"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        repository.findById(id).map(cliente -> {
            repository.delete(cliente);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrada!"));
    }
}
