package com.sistema_cadastro_api.service;

import com.sistema_cadastro_api.dtos.PerguntaRecordDto;
import com.sistema_cadastro_api.exception.DelecaoInvalida;
import com.sistema_cadastro_api.models.PerguntaModel;
import com.sistema_cadastro_api.repositories.PerguntaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepository;

    public PerguntaModel save(PerguntaRecordDto perguntaRecordDto) {
        PerguntaModel perguntaModel = new PerguntaModel();
        BeanUtils.copyProperties(perguntaRecordDto, perguntaModel);
        return perguntaRepository.save(perguntaModel);
    }

    public List<PerguntaModel> findAll() {
        List<PerguntaModel> perguntas = perguntaRepository.findAll();
        return perguntas;
    }

    public Optional<PerguntaModel> delete(long id) {
        Optional<PerguntaModel> pergunta = perguntaRepository.findById(id);
        if (pergunta.isPresent() & pergunta.get().getId() <= 4) {
            throw new DelecaoInvalida("Essa pergunta não pode ser deletada!");
        }
        perguntaRepository.delete(pergunta.get());
        return pergunta;
    }
}
