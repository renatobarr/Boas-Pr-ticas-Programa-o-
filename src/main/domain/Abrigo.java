package br.com.alura.domain;

import java.util.ArrayList;
import java.util.List;

public class Abrigo {

    private final long id; // Definido como final para evitar modificações após a criação
    private String nome;
    private String telefone;
    private String email;
    private List<Pet> pets;

    // Construtor padrão
    public Abrigo() {
        this.id = 0; // Para compatibilidade, mas o ideal é ser gerado por banco de dados
        this.pets = new ArrayList<>();
    }
    
    // Construtor completo
    public Abrigo(long id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.pets = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void adicionarPet(Pet pet) {
        this.pets.add(pet);
    }

    public void removerPet(Pet pet) {
        this.pets.remove(pet);
    }

    @Override
    public String toString() {
        return "Abrigo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", pets=" + pets +
                '}';
    }
}
