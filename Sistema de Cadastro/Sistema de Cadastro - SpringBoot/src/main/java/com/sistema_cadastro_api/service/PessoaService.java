package com.sistema_cadastro_api.service;

import com.sistema_cadastro_api.dtos.PessoaRecordDto;
import com.sistema_cadastro_api.exception.EmailInvalidoException;
import com.sistema_cadastro_api.exception.EmailJaCadastradoException;
import com.sistema_cadastro_api.exception.IdadeInvalidaException;
import com.sistema_cadastro_api.exception.NomeInvalidoException;
import com.sistema_cadastro_api.models.PessoaModel;
import com.sistema_cadastro_api.repositories.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaModel save(PessoaRecordDto pessoaRecordDto) {
        PessoaModel pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(pessoaRecordDto, pessoaModel);

        Optional<PessoaModel> pessoaExistente = pessoaRepository.findByEmail(pessoaModel.getEmail());

        if (pessoaExistente.isPresent()) {
            throw new EmailJaCadastradoException("O email " + pessoaModel.getEmail() + " já está em uso, inclua outro.");
        }

        if (pessoaModel.getNome().length() < 10) {
            throw new NomeInvalidoException("Nome deve ser maior que 10 caracteres");
        }

        if (!pessoaModel.getEmail().contains("@")) {
            throw new EmailInvalidoException("Digite o e-mail novamente, está faltando  '@..'");
        }

        if (pessoaModel.getIdade() < 18) {
            throw new IdadeInvalidaException("Você deve ser maior que 18 anos!");
        }

        return pessoaRepository.save(pessoaModel);
    }

    public List<PessoaModel> getAll() {
        List<PessoaModel> pessoaModel = pessoaRepository.findAll();
        return pessoaModel;
    }

    public List<PessoaModel> getByName(String name) {
        List<PessoaModel> listPessoas = pessoaRepository.findByNome(name);
        return listPessoas;
    }

    public List<PessoaModel> getByAge(int idade) {
        List<PessoaModel> listPessoas = pessoaRepository.findByIdade(idade);
        return listPessoas;
    }

    public Optional<PessoaModel> getByEmail(String email) {
        Optional<PessoaModel> listPessoas = pessoaRepository.findByEmail(email);
        return listPessoas;
    }
}
