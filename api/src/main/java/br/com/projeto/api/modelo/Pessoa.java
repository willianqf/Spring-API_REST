package br.com.projeto.api.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Precisa adicionar a dependencia 
// mysql -> mysql-connector-java (Permite conectar a base de dados MYSQL)
// data-jpa -> spring-boot-starter-data-jpa (Permite definir os tipos de dados do SQL nas classes JAVA)
@Entity // Específica a criação da tabela na base de Dados
@Table(name = "Pessoas")
public class Pessoa {

    // Atributos
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENTY
    private int codigo;
    private String nome;
    private int idade;

    public Pessoa() {

    }

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    // Métodos de acesso
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

}
