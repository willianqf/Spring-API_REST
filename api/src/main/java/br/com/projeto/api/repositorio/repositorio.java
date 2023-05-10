package br.com.projeto.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.modelo.Pessoa;

// CRIAR AÇÕES NO BANCO DE DADOS -> Herdar CrudRepository<//Modelo de base de dados//, //tipo de dados da Primary Key da base//>
@Repository // INDICA A INTERFACE COMO REPOSITORIO
public interface repositorio extends CrudRepository<Pessoa, Integer> { // INDICAR MODELO TRABALHADO , TIPO DE DADO DA
                                                                       // CHAVE PRIMÁRIA
    List<Pessoa> findAll();

    // Encontrar pessoa com nome específico
    Pessoa findByNome(String nome);

    // Encontrar pessoa com código específico
    Pessoa findByCodigo(int codigo);

    // Encontrar pessoa com idade específica
    List<Pessoa> findByIdade(int codigo);

    // Criar método de ordenação
    List<Pessoa> findByOrderByNomeDesc(); // ASC -> Crescente / Desc -> Decrescente

    // Cria método de ordenação que procura por nome e organiza em decrescente
    List<Pessoa> findByNomeOrderByIdadeDesc(String parametro);

    List<Pessoa> findByNomeOrderByIdadeAsc(String parametro);

    // Usa o método LIKE do banco de dados (Busca algum termo referente)
    List<Pessoa> findByNomeContaining(String valor);

    // Verificar algum termo dentro de uma string
    // (Inicia com..;)
    List<Pessoa> findByNomeStartsWith(String termo);

    // (Finalisa com...)
    List<Pessoa> findByNomeEndsWith(String termo);

    /// CRIAR MÉTODOS DE SQL USANDO SQL //////
    @Query(value = "SELECT SUM(idade) FROM pessoas", nativeQuery = true)
    int somaIdades();

    @Query(value = "SELECT * FROM pessoas WHERE idade >= :idades", nativeQuery = true)
    List<Pessoa> idadeMaiorIgual(int idades);
}
