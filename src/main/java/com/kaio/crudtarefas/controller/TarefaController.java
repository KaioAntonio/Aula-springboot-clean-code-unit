package com.kaio.crudtarefas.controller;

import com.kaio.crudtarefas.entity.Tarefa;
import com.kaio.crudtarefas.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.criarTarefa(tarefa);
    }

    @GetMapping
    public List<Tarefa> listarTarefas(){
        return tarefaService.listarTodasTarefas();
    }

    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable("id") Long id,
                                  @RequestBody Tarefa tarefa) throws Exception {
        return tarefaService.atualizarTarefa(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable("id") Long id) throws Exception {
        tarefaService.deletarTarefa(id);
    }
}
