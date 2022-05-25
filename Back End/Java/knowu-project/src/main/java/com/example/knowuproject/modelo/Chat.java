package com.example.knowuproject.modelo;

import org.springframework.format.annotation.DateTimeFormat;

public abstract class Chat {
    private Integer idChat;
    private DateTimeFormat dataEnvioMensagem;

    public Chat(Integer idChat, DateTimeFormat dataEnvioMensagem) {
        this.idChat = idChat;
        this.dataEnvioMensagem = dataEnvioMensagem;
    }

    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    public DateTimeFormat getDataEnvioMensagem() {
        return dataEnvioMensagem;
    }

    public void setDataEnvioMensagem(DateTimeFormat dataEnvioMensagem) {
        this.dataEnvioMensagem = dataEnvioMensagem;
    }

    public abstract void enviarMensagem();

    @Override
    public String toString() {
        return String.format("Chat\n" +
                             "idChat: %d\n" +
                             "Data envio da mensagem: " + dataEnvioMensagem);
    }
}
