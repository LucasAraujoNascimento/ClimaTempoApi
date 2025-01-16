package com.nascimento.api_clima.repositories;

import com.nascimento.api_clima.models.HistoricoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoModel, Integer> {
}
