package com.avalII.model.strategy.notificacao;
public class EmailNotificacao implements CanalNotificacao {
    @Override
    public void notificar(String mensagem) {
        System.out.println("Enviando email com a mensagem: " + mensagem);
    }
}
