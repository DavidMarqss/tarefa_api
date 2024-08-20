package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return tarefas.get();
    }
 
    @PutMapping("/{id}")
    public Tarefa put(
        @PathVariable long id,
        @RequestBody Tarefa dados) {
        Optional<Tarefa> tarefas = tarefaRepo.findById(id);
 
        tarefas.get().setDescricao(dados.getDescricao());
        tarefas.get().setConcluido(dados.isConcluido());
        return tarefaRepo.save(tarefas.get());
    }
 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
     tarefaRepo.deleteById(id);
    }

}
