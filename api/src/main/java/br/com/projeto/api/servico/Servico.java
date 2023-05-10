package br.com.projeto.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.repositorio;

// PERMITE CRIAR CLASSES INSTÂNCIADAS PELO AUTOWIRED
@Service
public class Servico {

    // Crie um Objeto do tipo mensagem
    @Autowired
    private Mensagem msg;

    // Crie um Objeto do tipo repositório para realizar ação
    @Autowired
    private repositorio acao;

    // Verificando se o cadastro é válido usando o ResponseEntity
    public ResponseEntity<?> cadastrar(Pessoa obj) {
        // Recebe um objeto
        if (obj.getNome().equals("")) {
            msg.setMensagem("Houve uma tentativa errada de inserção de nome");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        if (obj.getIdade() < 0) {
            msg.setMensagem("A sua idade está inválida!");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        // Realiza o cadastro <acao.save, resultado do http>
        return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
    }

    // Método para selecionar pessoas
    public ResponseEntity<?> selecionar() {
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    // Método para selecionar pessoa específica
    public ResponseEntity<?> tabelaResponseId(int id) {
        Pessoa obj = acao.findByCodigo(id);
        if (obj == null) {
            msg.setMensagem("Não foi encontrado nenhum nome com essa ID!");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // Método para realizar a atualização
    public ResponseEntity<?> atualizar(Pessoa obj) {
        if (obj.getNome().equals("")) {
            msg.setMensagem("Você não pode inserir um nome vazio na atualização!");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        if (obj.getIdade() < 0) {
            msg.setMensagem("A idade não pode ser negativa!");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
    }

    // Método para realizar o delete
    public ResponseEntity<?> deletar(int posi) {
        Pessoa obj = acao.findByCodigo(posi);
        if (obj == null) {
            msg.setMensagem("Não foi encontrado nenhuma pessoa com essa ID!");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        acao.delete(obj);
        msg.setMensagem("A pessoa foi deletada com sucesso!");
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
