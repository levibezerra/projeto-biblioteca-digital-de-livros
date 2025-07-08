package com.example.bd_nosql.projeto_biblioteca.controller;

import com.example.bd_nosql.projeto_biblioteca.dto.UsuarioDto;
import com.example.bd_nosql.projeto_biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrar(@RequestBody UsuarioDto usuarioDto) {
        try {
            String msg = usuarioService.cadastrarUsuario(usuarioDto);
            return ResponseEntity.ok(msg);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDto usuarioDto) {
        try {
            boolean sucesso = usuarioService.login(usuarioDto.getEmail(), usuarioDto.getSenha());
            return sucesso
                    ? ResponseEntity.ok("Login realizado com sucesso!")
                    : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no login");
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<UsuarioDto> buscar(@PathVariable String email) {
        try {
            UsuarioDto usuario = usuarioService.buscarUsuario(email);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}