package com.sistema_cadastro_api.controller;

import com.sistema_cadastro_api.dtos.PessoaRecordDto;
import com.sistema_cadastro_api.exception.EmailInvalidoException;
import com.sistema_cadastro_api.exception.EmailJaCadastradoException;
import com.sistema_cadastro_api.exception.IdadeInvalidaException;
import com.sistema_cadastro_api.exception.NomeInvalidoException;
import com.sistema_cadastro_api.models.PessoaModel;
import com.sistema_cadastro_api.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @PostMapping("/pessoas")
    public ResponseEntity<String> savePessoa(@RequestBody @Valid PessoaRecordDto pessoaRecordDto) {
        try {
            pessoaService.save(pessoaRecordDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa cadastrada com sucesso!");
        } catch (EmailJaCadastradoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (NomeInvalidoException | IdadeInvalidaException | EmailInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/pessoas")
    public ResponseEntity<Object> getAllPessoas() {
        try {
            List<PessoaModel> pessoaList = pessoaService.getAll();
            if (pessoaList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
            }
            return ResponseEntity.ok(pessoaList);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/pessoas/nome/{name}")
    public ResponseEntity<Object> getByName(@PathVariable(value = "name") String name) {
        try {
            List<PessoaModel> pessoa = pessoaService.getByName(name);
            if (pessoa.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
            }
            return ResponseEntity.ok(pessoa);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/pessoas/idade/{idade}")
    public ResponseEntity<Object> getByAge(@PathVariable(value = "idade") int idade) {
        try {
            List<PessoaModel> pessoas = pessoaService.getByAge(idade);
            if (pessoas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
            }
            return ResponseEntity.ok(pessoas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/pessoas/email/{email}")
    public ResponseEntity<Object> getByEmail(@PathVariable(value = "email") String email) {
        try {
            Optional<PessoaModel> pessoa = pessoaService.getByEmail(email);
            if (pessoa.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
            }
            return ResponseEntity.ok(pessoa.get());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
