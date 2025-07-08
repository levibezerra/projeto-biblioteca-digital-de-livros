package com.example.bd_nosql.projeto_biblioteca.service;

import com.example.bd_nosql.projeto_biblioteca.dto.UsuarioDto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private Firestore firestore = FirestoreClient.getFirestore();

    public String cadastrarUsuario(UsuarioDto usuarioDto) throws Exception {
        DocumentReference docRef = firestore
                .collection("usuarios")
                .document(usuarioDto.getEmail());
        DocumentSnapshot snapshot = docRef.get().get();
        if (snapshot.exists()) {
            throw new IllegalStateException("Usuário já cadastrado com este e-mail.");
        }
        ApiFuture<WriteResult> result = docRef.set(usuarioDto);
        return "Usuário cadastrado em: " + result.get().getUpdateTime();
    }

    public boolean login(String email, String senha) throws Exception {
        DocumentReference docRef = firestore
                .collection("usuarios")
                .document(email);
        DocumentSnapshot snapshot = docRef.get().get();
        if (!snapshot.exists()) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        UsuarioDto usuario = snapshot.toObject(UsuarioDto.class);
        return usuario != null && usuario.getSenha().equals(senha);
    }

    public UsuarioDto buscarUsuario(String email) throws Exception {
        DocumentReference docRef = firestore
                .collection("usuarios")
                .document(email);
        DocumentSnapshot snapshot = docRef.get().get();
        if (!snapshot.exists()) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        return snapshot.toObject(UsuarioDto.class);
    }
}