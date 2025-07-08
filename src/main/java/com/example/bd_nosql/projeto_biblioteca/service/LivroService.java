package com.example.bd_nosql.projeto_biblioteca.service;

import com.example.bd_nosql.projeto_biblioteca.dto.LivroDto;
import com.example.bd_nosql.projeto_biblioteca.dto.LivroResponseDto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private Firestore firestore = FirestoreClient.getFirestore();

    public String salvarLivro(String usuarioId, LivroDto livroDto) throws Exception {
        DocumentReference docRef = firestore
                .collection("usuarios")
                .document(usuarioId)
                .collection("livros")
                .document();
        ApiFuture<WriteResult> result = docRef.set(livroDto);
        return docRef.getId();
    }

    public List<LivroResponseDto> listarLivros(String usuarioId) throws Exception {
        ApiFuture<QuerySnapshot> future = firestore
                .collection("usuarios")
                .document(usuarioId)
                .collection("livros")
                .get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.stream()
                .map(doc -> {
                    LivroDto dto = doc.toObject(LivroDto.class);
                    return new LivroResponseDto(
                            doc.getId(),
                            dto.getTitulo(),
                            dto.getAutor(),
                            dto.getCategoria(),
                            dto.getStatus(),
                            dto.getAvaliacao()
                    );
                })
                .collect(Collectors.toList());
    }

    public String editarLivro(String usuarioId, String livroId, LivroDto livroDto) throws Exception {
        ApiFuture<WriteResult> result = firestore
                .collection("usuarios")
                .document(usuarioId)
                .collection("livros")
                .document(livroId)
                .set(livroDto);
        return result.get().getUpdateTime().toString();
    }

    public void deletarLivro(String usuarioId, String livroId) throws Exception {
        firestore
                .collection("usuarios")
                .document(usuarioId)
                .collection("livros")
                .document(livroId)
                .delete()
                .get();
    }
}