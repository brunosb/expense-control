package com.barbosa.dev.expensecontrol.service;

import com.barbosa.dev.expensecontrol.exceptions.DataIntegrityException;
import com.barbosa.dev.expensecontrol.exceptions.ObjectNotFoundException;
import com.barbosa.dev.expensecontrol.util.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class AbstractService<T extends BaseEntity<Long>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

    @Autowired
    private PagingAndSortingRepository<T, Long> repository;

    public T findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! id: " + id));
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public Iterable<T> saveAll(Iterable<T> entities) {
        return repository.saveAll(entities);
    }

    public void deleteById(Long id) {
        try {
            repository.delete(findById(id));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível excluir!");
        }
    }

    public Page<T> findAll(Pageable pageable, Object[]... params) {
        return repository.findAll(pageable);
    }
}
