package com.example.bd_nosql.projeto_biblioteca.controller;

import com.example.bd_nosql.projeto_biblioteca.dto.LivroDto;
import com.example.bd_nosql.projeto_biblioteca.dto.LivroResponseDto;
import com.example.bd_nosql.projeto_biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @PostMapping("/{usuarioId}")
    public ResponseEntity<String> salvarLivro(@PathVariable String usuarioId, @RequestBody LivroDto livroDto) {
        try {
            String id = livroService.salvarLivro(usuarioId, livroDto);
            return ResponseEntity.ok("Livro salvo com ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar livro");
        }
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<LivroResponseDto>> listar(@PathVariable String usuarioId) {
        try {
            return ResponseEntity.ok(livroService.listarLivros(usuarioId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{usuarioId}/{livroId}")
    public ResponseEntity<String> editarLivro(@PathVariable String usuarioId,
                                              @PathVariable String livroId,
                                              @RequestBody LivroDto livroDto) {
        try {
            String time = livroService.editarLivro(usuarioId, livroId, livroDto);
            return ResponseEntity.ok("Atualizado em: " + time);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao editar livro");
        }
    }

    @DeleteMapping("/{usuarioId}/{livroId}")
    public ResponseEntity<String> deletarLivro(@PathVariable String usuarioId,
                                               @PathVariable String livroId) {
        try {
            livroService.deletarLivro(usuarioId, livroId);
            return ResponseEntity.ok("Livro deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar livro");
        }
    }
}