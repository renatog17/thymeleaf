package com.renato.crudthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.renato.crudthymeleaf.domain.Filme;
import com.renato.crudthymeleaf.service.FilmeService;

@Controller
public class FilmeController {

    private final FilmeService filmeService;

    @Autowired
    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    // Exibir a lista de filmes
    @GetMapping("/lista")
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeService.listarTodos());
        return "filmes/lista";
    }

    // Exibir o formulário de cadastro de novo filme
    @GetMapping("/novo")
    public String novoFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "/novoFilme";
    }

    // Salvar o filme
    @PostMapping("/filmes")
    public String salvarFilme(@ModelAttribute Filme filme) {
    	filmeService.salvar(filme);
        return "redirect:/filmes";
    }

    // Exibir o formulário para editar um filme existente
    @GetMapping("/editar/{id}")
    public String editarFilme(@PathVariable Long id, Model model) {
        Filme filme = filmeService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
        model.addAttribute("filme", filme);
        return "filmes/editar";
    }

    // Atualizar os dados de um filme existente
    @PostMapping("/editar/{id}")
    public String atualizarFilme(@PathVariable Long id, @ModelAttribute Filme filme) {
        filmeService.atualizar(id, filme);
        return "redirect:/filmes";
    }

    // Excluir um filme
    @GetMapping("/excluir/{id}")
    public String excluirFilme(@PathVariable Long id) {
        filmeService.excluir(id);
        return "redirect:/filmes";
    }
}
