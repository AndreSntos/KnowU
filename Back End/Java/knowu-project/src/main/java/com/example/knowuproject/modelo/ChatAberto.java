package com.example.knowuproject.modelo;

import org.springframework.format.annotation.DateTimeFormat;

public class ChatAberto extends Chat {
    private String usuarioRemetente;
    private String usuarioDestinatario;
    private String mensagem;

    public ChatAberto(Integer idChat, DateTimeFormat dataEnvioMensagem, String usuarioRemetente, String usuarioDestinatario, String mensagem) {
        super(idChat, dataEnvioMensagem);
        this.usuarioRemetente = usuarioRemetente;
        this.usuarioDestinatario = usuarioDestinatario;
        this.mensagem = mensagem;
    }

    public String getUsuarioRemetente() {
        return usuarioRemetente;
    }

    public void setUsuarioRemetente(String usuarioRemetente) {
        this.usuarioRemetente = usuarioRemetente;
    }

    public String getUsuarioDestinatario() {
        return usuarioDestinatario;
    }

    public void setUsuarioDestinatario(String usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public void enviarMensagem() {
        System.out.println("menasgem enviada com sucesso");
    }

    @Override
    public String toString() {
        return String.format("Chat aberto\n" +
                             "Usuário remetente: %s\n" +
                             "Usuário Destinatário: %s\n" +
                             "Mensagem: %s",
                             this.usuarioRemetente,
                             this.usuarioDestinatario,
                             this.mensagem);
    }
}
