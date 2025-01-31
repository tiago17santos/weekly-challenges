package com.sistema_cadastro_api.repositories;


import com.sistema_cadastro_api.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {
    Optional<PessoaModel> findByEmail(String email);

    List<PessoaModel> findByNome(String name);

    List<PessoaModel> findByIdade(int idade);
}
