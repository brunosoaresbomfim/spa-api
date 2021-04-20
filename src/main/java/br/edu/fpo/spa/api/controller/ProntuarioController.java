package br.edu.fpo.spa.api.controller;

import br.edu.fpo.spa.api.controller.dto.AgendamentoDTO;
import br.edu.fpo.spa.api.controller.dto.ProntuarioDTO;
import br.edu.fpo.spa.api.model.*;
import br.edu.fpo.spa.api.repository.AgendamentoRepository;
import br.edu.fpo.spa.api.repository.ClienteRepository;
import br.edu.fpo.spa.api.repository.EstagiarioRepository;
import br.edu.fpo.spa.api.repository.ProntuarioRepository;
import br.edu.fpo.spa.api.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioController implements Serializable {

    private ProntuarioRepository prontuarioRepository;
    private EstagiarioRepository estagiarioRepository;
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    public ProntuarioController(ProntuarioRepository prontuarioRepository, EstagiarioRepository estagiarioRepository, AgendamentoRepository agendamentoRepository) {
        this.prontuarioRepository = prontuarioRepository;
        this.estagiarioRepository = estagiarioRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    @GetMapping
    public List<Prontuario> todos(){
       return prontuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Prontuario obterPorId(@PathVariable Integer id){
        return prontuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prontuário não encontrado!"));
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Prontuario salvar(@RequestBody ProntuarioDTO prontuarioDTO){
        Agendamento a = agendamentoRepository.findById(prontuarioDTO.getAgendamento()).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Agendamento inexistente."));

        Estagiario e = estagiarioRepository.findById(prontuarioDTO.getEstagiario()).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Estagiário inexistente."));


        Prontuario p = new Prontuario();
        p.setDemanda(prontuarioDTO.getDemanda());
        p.setStatus(prontuarioDTO.getStatus());
        p.setInicioAtendimento(LocalDateTime.parse(prontuarioDTO.getInicioAtendimento(), DateTimeFormatter.ofPattern("HH:mm:ss")));
        //p.setTerminoAtendimento(LocalDateTime.parse(prontuarioDTO.getTerminoAtendimento(), DateTimeFormatter.ofPattern("HH:mm:ss")));
        p.setAgendamento(a);
        p.setEstagiario(e);
        return prontuarioRepository.save(p);
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
        prontuarioRepository.findById(id).map(prontuario -> {
            prontuarioRepository.delete(prontuario);
            return Void.TYPE;
        })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "Prontuário não encontrado!"));
    }
}
