package com.renato.crudthymeleaf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.crudthymeleaf.domain.Filme;
import com.renato.crudthymeleaf.repository.FilmeRepository;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    @Autowired
    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    // Salvar ou atualizar um filme
    public Filme salvar(Filme filme) {
        return filmeRepository.save(filme);
    }

    // Listar todos os filmes
    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    // Buscar um filme por ID
    public Optional<Filme> buscarPorId(Long id) {
        return filmeRepository.findById(id);
    }

    // Atualizar um filme
    public Filme atualizar(Long id, Filme filmeAtualizado) {
        return filmeRepository.findById(id)
                .map(filme -> {
                    filme.setTitulo(filmeAtualizado.getTitulo());
                    filme.setGenero(filmeAtualizado.getGenero());
                    return filmeRepository.save(filme);
                })
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
    }

    // Excluir um filme por ID
    public void excluir(Long id) {
        filmeRepository.deleteById(id);
    }
}
