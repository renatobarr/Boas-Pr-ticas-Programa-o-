package br.com.alura.domain;

import java.util.Objects;

public class Pet {
    private final long id; 
    private String tipo;
    private String nome;
    private String raca;
    private int idade;
    private String cor;
    private float peso;

    public Pet(long id, String tipo, String nome, String raca, int idade, String cor, float peso) {
        if (id < 0) throw new IllegalArgumentException("ID não pode ser negativo.");
        if (idade < 0) throw new IllegalArgumentException("Idade não pode ser negativa.");
        if (peso < 0) throw new IllegalArgumentException("Peso não pode ser negativo.");
        if (tipo == null || tipo.isBlank()) throw new IllegalArgumentException("Tipo não pode ser vazio.");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome não pode ser vazio.");
        if (raca == null || raca.isBlank()) throw new IllegalArgumentException("Raça não pode ser vazia.");
        if (cor == null || cor.isBlank()) throw new IllegalArgumentException("Cor não pode ser vazia.");

        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.cor = cor;
        this.peso = peso;
    }

    public long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }

    public String getCor() {
        return cor;
    }

    public float getPeso() {
        return peso;
    }

    public void setTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) throw new IllegalArgumentException("Tipo não pode ser vazio.");
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome não pode ser vazio.");
        this.nome = nome;
    }

    public void setRaca(String raca) {
        if (raca == null || raca.isBlank()) throw new IllegalArgumentException("Raça não pode ser vazia.");
        this.raca = raca;
    }

    public void setIdade(int idade) {
        if (idade < 0) throw new IllegalArgumentException("Idade não pode ser negativa.");
        this.idade = idade;
    }

    public void setCor(String cor) {
        if (cor == null || cor.isBlank()) throw new IllegalArgumentException("Cor não pode ser vazia.");
        this.cor = cor;
    }

    public void setPeso(float peso) {
        if (peso < 0) throw new IllegalArgumentException("Peso não pode ser negativo.");
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", idade=" + idade +
                ", cor='" + cor + '\'' +
                ", peso=" + peso +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
