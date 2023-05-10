package br.com.projeto.api.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

// mysql -> mysql-connector-java (Permite conectar a base de dados MYSQL)
// data-jpa -> spring-boot-starter-data-jpa (Permite definir os tipos de dados do SQL nas classes JAVA)
// spring-boot-validation -> spring-boot-starter-validation
@Entity // Define sendo um entidade
@Table(name = "clientes") // Define nome da tabela no SQL
public class Cliente {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENTY
    private int codigo;

    @NotEmpty(message = "Você não informou nome, por favor, informe um nome") // NotEmpty(message = //mensagem//) ->
                                                                              // Obriga a passar um nome
    private String nome;

    // @Email(message = "Informe um email válido") // NotEmpty(message =
    // //mensagem//) -> Obriga a passar um nome
    @Email(message = "Informe um email válido, porfavor!")
    @NotEmpty(message = "Não pode informar um Email Vazio!") // Não pode ser NULO
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
