package com.renato.crudthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renato.crudthymeleaf.domain.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long>{

}
