package br.com.projeto.api.controle;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Cliente;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.repositorio;
import br.com.projeto.api.servico.Servico;
import jakarta.validation.Valid;

// Esse classe servirá como rotas
// @RestController <- Anotação de rotas no modelo REST
@RestController
public class Controle {

    // INVOCA OBJETO SEM A NECESSIDADE DE INSTÂNCIAR @Autowired (INJENÇÃO DE
    // DEPENDÊNCIA)
    @Autowired // <AutoWired inicia a intância do objeto sem usar o operador
    private repositorio acao;
    @Autowired
    private Servico servico;

    // Gerar dados aleatório //
    @GetMapping("/gerarDados")
    public void GerarDados() {
        Random aleatorio = new Random();
        String[] nomes = { "Ana", "Sara", "Gabriela", "Rodrigo", "Fernando", "Carlos", "Pamela", "Daene" };
        String[] sobrenome = { " Adiniz", " de Souza", " Beltrano", " da Silva", " de Menezes", " Gabriel", " Martins",
                " Marinho" };
        String[] terceironome = { " Muniz", " Suzam", " Fernandes", " Albetri", " Jr", "" };
        for (int x = 0; x < 50; x++) {
            String novoNome = nomes[aleatorio.nextInt(nomes.length)] + sobrenome[aleatorio.nextInt(sobrenome.length)]
                    + terceironome[aleatorio.nextInt(terceironome.length)];
            int idade = aleatorio.nextInt(100);
            Pessoa novaPessoa = new Pessoa(novoNome, idade);
            cadastrar(novaPessoa);
        }

    }

    ///////////// Define uma rota do tipo GET de acesso web /////////////////////
    @GetMapping("/mensagem")
    public String mensagem() {
        return "<h1>Hello, world<h1>";
    }

    // Usando variáveis em HTML -GET
    @GetMapping("/mensagem2/{nome}")
    public String mensagem2(@PathVariable String nome) { // PathVariable String nome <- Pega variáveis no modelo Get do
                                                         // Navegador
        return "<h1>Bem vindo, " + nome + "!<h1>";
    }

    @GetMapping("/mensagem2/")
    public String mensagem2() {
        return "<h1>Bem vindo, anônimo!<h1>";
    }

    ///////////// Define uma rota do tipo POST de acesso web ////////////////////
    // Apenas retornar um pessoa
    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p) {
        return p;
    }

    // Cadastra uma pessoa usando Post (public 'nome da classe' funcao(@RequestBody
    // 'nome da clase' variavel))
    // Cadastra usando o objeto acao na base de dados - INSERT INTO
    @PostMapping("/cadastrar")
    public Pessoa cadastrar(@RequestBody Pessoa obj) {
        return acao.save(obj);
    }

    ///////////// RETORNAR A LISTA DE DADOS CADASTRADO EM PESSOA - SELECT
    ///////////// ///////////////////////////
    @GetMapping("/tabela")
    public List<Pessoa> selecionar() {
        return acao.findAll();
    }

    // Retorna dados cadastrados por nome
    @GetMapping("/nome/{valor}")
    public Pessoa selecionarNome(@PathVariable String valor) {
        return acao.findByNome(valor);
    }

    // Retorna dados cadastrados por código
    @GetMapping("/codigo/{valor}")
    public Pessoa selecionarCodigo(@PathVariable int valor) {
        return acao.findByCodigo(valor);
    }

    // Retorna dados cadastrado por idade
    @GetMapping("idade/{valor}") // Pode retornar várias pessoas
    public List<Pessoa> selecionarIdade(@PathVariable int valor) {
        return acao.findByIdade(valor);
    }

    //////////// alterações de registro // - UPDATE
    @PutMapping("/alterar") // PASSAR OBJETO COMPLETO
    public Pessoa alterar(@RequestBody Pessoa valor) {
        return acao.save(valor);
    }

    //////// Deletando registro // - DELETE
    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable int id) {
        Pessoa valor = selecionarCodigo(id);
        acao.delete(valor); // acao.delete precisa de um objeto Pessoa para ser deletado
    }

    // Método para retornar valor de dados cadastrado // COUNT
    @GetMapping("/contador")
    public long contador() {
        return acao.count(); // Retorna a quantidade de dados registrados (count)
    }

    // Ordenar valores de uma tabela // ORDER BY
    @GetMapping("/OrdernarPorNome")
    public List<Pessoa> OrderPessoa() {
        return acao.findByOrderByNomeDesc();
    }

    // Ordernar por nome decrescente - ORDER BY '' Desc
    @GetMapping("/OrdernarPorNomeDesc/{nome}")
    public List<Pessoa> OrderPessoa2(@PathVariable String nome) {
        return acao.findByNomeOrderByIdadeDesc(nome);
    }

    @GetMapping("/OrdernarPorNomeAsc/{nome}") // - ORDER BY '' ASC
    public List<Pessoa> OrderPessoa3(@PathVariable String nome) {
        return acao.findByNomeOrderByIdadeAsc(nome);
    }

    // BUSCAR ALGO RELACIONADO - MODEL %LIKE% SQL
    @GetMapping("/buscar/{valor}")
    public List<Pessoa> buscaralgo(@PathVariable String valor) {
        return acao.findByNomeContaining(valor);
    }

    // VERIFICAR SE UM NOME INICIA COM...
    @GetMapping("/buscarcominicio/{valor}")
    public List<Pessoa> buscarcominicio(@PathVariable String valor) {
        return acao.findByNomeStartsWith(valor);
    }

    // VERIFICAR SE UM NOME ACABA COM...
    @GetMapping("/buscarcomfinal/{valor}")
    public List<Pessoa> buscarcomfinal(@PathVariable String valor) {
        return acao.findByNomeEndsWith(valor);
    }

    // Chamando função criada no repositorio
    @GetMapping("/soma")
    public String somadasidades() {
        return String.valueOf(acao.somaIdades());
    }

    // idade >=
    @GetMapping("/idades/{idade}")
    public List<Pessoa> idadeMaiorIgual(@PathVariable int idade) {
        return acao.idadeMaiorIgual(idade);
    }

    // CONFIGURANDO STATUS USANDO RESPONSEENTITY E CADASTRANDO
    @GetMapping("/status")
    public ResponseEntity<?> status() { // ResponseEntity<?>
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // Realiza cadastro usando response do Servico
    @PostMapping("/cadastrarResponse")
    public ResponseEntity<?> cadastrarResp(@RequestBody Pessoa Obj) {
        return servico.cadastrar(Obj);
    }

    // Retorna a tabela usando response do Servico
    @GetMapping("/tabelaResponse")
    public ResponseEntity<?> tabelaResponse() {
        return servico.selecionar();
    }

    @GetMapping("/tabelaResponse/{id}")
    public ResponseEntity<?> tabelaResponseId(@PathVariable int id) {
        return servico.tabelaResponseId(id);
    }

    // Fazer update usando a tabela servico
    @PutMapping("/atualizarResponse/{valor}")
    public ResponseEntity<?> atualizarResponse(@RequestBody Pessoa req) {
        return servico.atualizar(req);
    }

    // Realizar Delete usando tabela servico
    @DeleteMapping("/deletarResponse/{valor}")
    public ResponseEntity<?> DeletarResponse(@PathVariable int valor) {
        return servico.deletar(valor);
    }

    /// TRABALHANDO COM A TABELA CLIENTE

    // Rota Cliente para Testes
    @PostMapping("/cliente")
    public void Cliente(@Valid @RequestBody Cliente obj) { // @Valid -> Valida a requisição

    }
}
