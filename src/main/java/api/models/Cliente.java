package api.models;

import java.util.UUID;

public class Cliente {
    private long id;
    private String nome;
    private String sobrenome;
    private String telefoneContato;
    private String email;
    private String CEP;
    private int numero;

    public Cliente(String nome, String sobrenome, String telefoneContato, String email, String CEP, int numero) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefoneContato = telefoneContato;
        this.email = email;
        this.CEP = CEP;
        this.numero = numero;
    }


}
