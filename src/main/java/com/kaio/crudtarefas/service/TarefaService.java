package com.kaio.crudtarefas.service;

import com.kaio.crudtarefas.entity.Tarefa;
import com.kaio.crudtarefas.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    //Supondo que o seu gestor pediu para criar um servico que atuialize ou salve uma tarefa
//    public Tarefa saveOrUpdateTask(Tarefa task, boolean isUpdate) {
//        if (isUpdate) {
//            // Lógica de atualização não será discutida aqui, focaremos apenas na criação.
//        } else {
//            // A duplicação de código abaixo pode levar a erros, pois a tarefa é salva duas vezes.
//            // Isso pode resultar em efeitos colaterais indesejados, como duplicação de entradas no banco de dados.
//            tarefaRepository.save(task); // Primeira chamada para salvar a tarefa.
//            tarefaRepository.save(task); // Segunda chamada duplicada para salvar a mesma tarefa.
//
//            // A operação de salvar deveria ser realizada uma única vez para prevenir duplicidade
//            // e outros potenciais problemas de consistência de dados.
//            return task; // Retorna a tarefa, presumindo que ela foi salva corretamente.
//        }
//        return null; // Retorna nulo se for uma atualização, não é ideal tratar dois comportamentos diferentes no mesmo método.
//    }

    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) throws Exception {
        Tarefa tarefaExistente = buscarTarefaPorId(id);
        tarefaExistente.setDescription(tarefaAtualizada.getDescription());
        tarefaExistente.setTitle(tarefaAtualizada.getTitle());
        tarefaExistente.setDueDate(tarefaAtualizada.getDueDate());

        return tarefaRepository.save(tarefaExistente);
    }
    //Utilizando o método buscarTarefaPorId, podendo reutilizar no delete e no atualizar, evitando duplicação de código
    private Tarefa buscarTarefaPorId(Long id) throws Exception {
        return tarefaRepository.findById(id).orElseThrow(() -> new Exception("Tarefa não encontrada!"));
    }

    public List<Tarefa> listarTodasTarefas() {
        return tarefaRepository.findAll();
    }

    public void deletarTarefa(Long id) throws Exception {
        Tarefa tarefa = buscarTarefaPorId(id);
        tarefaRepository.delete(tarefa);
    }

}
