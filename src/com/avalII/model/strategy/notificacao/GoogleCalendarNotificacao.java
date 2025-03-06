package com.avalII.model.strategy.notificacao;
public class GoogleCalendarNotificacao implements CanalNotificacao {
    @Override
    public void notificar(String mensagem) {
        System.out.println("Adicionando evento ao Google Calendar com a mensagem: " + mensagem);
    }
}
