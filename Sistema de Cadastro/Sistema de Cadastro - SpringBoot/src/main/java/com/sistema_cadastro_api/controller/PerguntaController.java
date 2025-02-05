package com.sistema_cadastro_api.controller;

import com.sistema_cadastro_api.dtos.PerguntaRecordDto;
import com.sistema_cadastro_api.models.PerguntaModel;
import com.sistema_cadastro_api.service.PerguntaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PerguntaController {

    @Autowired
    private PerguntaService perguntaService;

    @PostMapping("/perguntas")
    public ResponseEntity<String> savePergunta(@RequestBody @Valid PerguntaRecordDto perguntaRecordDto) {
        try {
            perguntaService.save(perguntaRecordDto);
            return ResponseEntity.ok("Pergunta cadastrada com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/perguntas")
    public ResponseEntity<Object> getAllPerguntas() {
        try {
            List<PerguntaModel> perguntas = perguntaService.findAll();
            if (perguntas.isEmpty()) {
                return ResponseEntity.badRequest().body("Pergunta não encontrada!");
            }
            return ResponseEntity.ok(perguntas);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/perguntas/{id}")
    public ResponseEntity<Object> deletePerguntas(@PathVariable(value = "id") Long id) {
        try {
            Optional<PerguntaModel> perguntas = perguntaService.delete(id);
            if (perguntas.isEmpty()) {
                return ResponseEntity.badRequest().body("Pergunta não encontrada!");
            }
            return ResponseEntity.ok("Pergunta deletada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
