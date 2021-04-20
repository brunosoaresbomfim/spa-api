package br.edu.fpo.spa.api.controller;

import br.edu.fpo.spa.api.controller.dto.AgendamentoDTO;
import br.edu.fpo.spa.api.model.Agendamento;
import br.edu.fpo.spa.api.model.Cliente;
import br.edu.fpo.spa.api.model.Supervisor;
import br.edu.fpo.spa.api.repository.AgendamentoRepository;
import br.edu.fpo.spa.api.repository.ClienteRepository;
import br.edu.fpo.spa.api.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController implements Serializable {

    private ClienteRepository clienteRepository;
    private SupervisorRepository supervisorRepository;
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    public AgendamentoController(ClienteRepository clienteRepository, SupervisorRepository supervisorRepository, AgendamentoRepository agendamentoRepository) {
        this.clienteRepository = clienteRepository;
        this.supervisorRepository = supervisorRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    @GetMapping
    public List<AgendamentoDTO> todos(){
        List<AgendamentoDTO> lista = new ArrayList<>();

        for (Agendamento a : agendamentoRepository.findAll()){
            AgendamentoDTO dto = new AgendamentoDTO();
            dto.setDataTriagem(a.getDataTriagem().toString());
            dto.setHoraTriagem(a.getHoraTriagem().toString());
            dto.setComparecimento(a.getComparecimento());
            dto.setJustificativa(a.getJustificativa());
            dto.setIdCliente(a.getCliente().getId());
            dto.setIdSupervisor(a.getSupervisor().getId());
            lista.add(dto);
        }

        return lista;
    }

    @GetMapping("/{id}")
    public Agendamento obterPorId(@PathVariable Integer id){
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado!"));
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Agendamento salvar(@RequestBody AgendamentoDTO dto){
        Cliente c = clienteRepository.findById(dto.getIdCliente()).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Cliente inexistente."));

        Supervisor s = supervisorRepository.findById(dto.getIdSupervisor()).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Supervisor inexistente."));

        Agendamento a = new Agendamento();
        a.setDataTriagem(LocalDate.parse(dto.getDataTriagem(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        a.setHoraTriagem(LocalTime.parse(dto.getHoraTriagem(), DateTimeFormatter.ofPattern("HH:mm:ss")));
        a.setCliente(c);
        a.setComparecimento(dto.getComparecimento());
        a.setJustificativa(dto.getJustificativa());
        a.setSupervisor(s);
        return agendamentoRepository.save(a);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editar(@PathVariable Integer id, @RequestBody Agendamento agendamentoUpdate){
        agendamentoRepository.findById(id).map(agendamento -> {
            agendamento.setJustificativa(agendamentoUpdate.getJustificativa());
            agendamento.setComparecimento(agendamentoUpdate.getComparecimento());

            return agendamentoRepository.save(agendamento);
        }).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Agendamento inexistente."));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        agendamentoRepository.findById(id).map(supervisor -> {
            agendamentoRepository.delete(supervisor);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrada!"));
    }
}
