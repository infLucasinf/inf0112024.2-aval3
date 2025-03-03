package com.avalII.model.strategy.notificacao;

public class WhatsAppNotificacao implements CanalNotificacao {
    @Override
    public void notificar(String mensagem) {
        System.out.println("Enviando WhatsApp com a mensagem: " + mensagem);
    }
}
