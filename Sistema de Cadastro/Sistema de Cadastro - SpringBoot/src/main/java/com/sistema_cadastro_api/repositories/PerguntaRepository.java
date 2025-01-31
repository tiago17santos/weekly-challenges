package com.sistema_cadastro_api.repositories;


import com.sistema_cadastro_api.models.PerguntaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<PerguntaModel, Long> {

}
