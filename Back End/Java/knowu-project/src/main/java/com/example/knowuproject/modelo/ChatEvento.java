package com.example.knowuproject.modelo;

import org.springframework.format.annotation.DateTimeFormat;

public class ChatEvento extends Chat {
    private String mensagem;
    private String usuarioRemetente;

    public ChatEvento(Integer idChat, DateTimeFormat dataEnvioMensagem, String mensagem, String usuarioRemetente) {
        super(idChat, dataEnvioMensagem);
        this.mensagem = mensagem;
        this.usuarioRemetente = usuarioRemetente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getUsuarioRemetente() {
        return usuarioRemetente;
    }

    public void setUsuarioRemetente(String usuarioRemetente) {
        this.usuarioRemetente = usuarioRemetente;
    }

    @Override
    public void enviarMensagem() {
        System.out.println("Mensagem enviada com sucesso");
    }

    @Override
    public String toString() {
        return String.format("Chat Evento\n" +
                             "Mensagem: %s\n" +
                             "Usuário Remetente: %s\n" +
                             super.toString());
    }
}
