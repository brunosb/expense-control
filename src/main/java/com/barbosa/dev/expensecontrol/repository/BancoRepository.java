package com.barbosa.dev.expensecontrol.repository;

import com.barbosa.dev.expensecontrol.model.Banco;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends PagingAndSortingRepository<Banco,Long> {
}
