package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Tarefa;
import application.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepo;

    @GetMapping
    public Iterable<Tarefa> list() {
        return tarefaRepo.findAll();
    }
 
    @PostMapping
    public Tarefa insert(@RequestBody Tarefa tarefa) {
        return tarefaRepo.save(tarefa);
    }
 
    @GetMapping("/{id}")
    public Tarefa details(@PathVariable long id) {
        Optional<Tarefa> tarefas = tarefaRepo.findById(id);
        if(tarefas.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Sem Tarefa"
            );
        }
        return tarefas.get();
    }
 
    @PutMapping("/{id}")
    public Tarefa put(
        @PathVariable long id,
        @RequestBody Tarefa dados) {
        Optional<Tarefa> tarefas = tarefaRepo.findById(id);
        if(tarefas.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Sem Tarefa"
            );
        }

        if(dados.getDecricao().isEmpty() ){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Descrição Invalida"
            );
        }
 
        tarefas.get().setDecricao(dados.getDecricao());
        tarefas.get().setConcluido(dados.isConcluido());
        return tarefaRepo.save(tarefas.get());
    }
 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if( tarefaRepo.existsById(id)){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Sem Tarefa"
            );
        }
     tarefaRepo.deleteById(id);
    }

}
