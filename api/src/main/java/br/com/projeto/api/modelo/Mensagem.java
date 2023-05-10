package br.com.projeto.api.modelo;

import org.springframework.stereotype.Component;

// Permite inicializar os elementos 
@Component // Em execução varre os elementos da class atribuida assim que executado
public class Mensagem {
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
