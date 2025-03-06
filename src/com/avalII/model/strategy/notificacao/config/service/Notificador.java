package com.avalII.model.strategy.notificacao.config.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.avalII.model.Evento;
import com.avalII.model.strategy.EmailFormatStrategy;
import com.avalII.model.strategy.WhatsAppFormatStrategy;
import com.avalII.model.strategy.notificacao.EmailNotificacao;
import com.avalII.model.strategy.notificacao.GoogleCalendarNotificacao;
import com.avalII.model.strategy.notificacao.WhatsAppNotificacao;
import com.avalII.model.strategy.notificacao.config.NotificationConfiguration;

public class Notificador {
    private List<NotificationConfiguration> configurations = new ArrayList<>();

    public Notificador() {
        addConfigurations();
    }
    
    private void addConfigurations() {
        configurations.add(createConfigRegra1());
        configurations.add(createConfigRegra2());
        configurations.add(createConfigRegra3());
    }

    private NotificationConfiguration createConfigRegra1() {
        return new NotificationConfiguration(
            e -> e.getPrioridade() == 10 && e.iniciaEm(LocalDate.now()),
            new WhatsAppFormatStrategy(),
            Arrays.asList(
                new GoogleCalendarNotificacao(),
                new EmailNotificacao(),
                new WhatsAppNotificacao()
            )
        );
    }

    private NotificationConfiguration createConfigRegra2() {
        return new NotificationConfiguration(
            e -> e.getPrioridade() >= 5 && e.iniciaEm(LocalDate.now()),
            new EmailFormatStrategy(),
            Arrays.asList(
                new GoogleCalendarNotificacao(),
                new EmailNotificacao()
            )
        );
    }

    private NotificationConfiguration createConfigRegra3() {
        return new NotificationConfiguration(
            e -> e.getPrioridade() >= 1 && e.getPrioridade() < 5 &&
                 e.iniciaEntre(LocalDateTime.now().minus(2, ChronoUnit.DAYS), LocalDateTime.now()),
            new EmailFormatStrategy(),
            Arrays.asList(new GoogleCalendarNotificacao())
        );
    }

    public void notificar(Evento e) {
        configurations.stream()
            .filter(config -> config.isApplicable(e))  // Filtra as configurações aplicáveis
            .findFirst()  // Pega a primeira configuração que se aplica
            .ifPresent(config -> {
                String mensagemFormatada = config.getFormatStrategy().format(e);
                config.getCanais().forEach(canal -> canal.notificar(mensagemFormatada)); // Envia a notificação nos canais
            });
    }

    public void addConfiguration(NotificationConfiguration config) {
        configurations.add(config);
    }

    public static void main(String[] args) {
        Notificador notificador = new Notificador();
        
        // Evento de prioridade 10 que inicia hoje
        Evento evento1 = new Evento(10, LocalDate.now(), LocalDateTime.now(), "Evento de alta prioridade");
        notificador.notificar(evento1);
        
        // Evento de prioridade 7 que inicia hoje
        Evento evento2 = new Evento(7, LocalDate.now(), LocalDateTime.now(), "Evento de média prioridade");
        notificador.notificar(evento2);
        
        // Evento de prioridade 3 que iniciou entre (now - 2 dias) e now
        Evento evento3 = new Evento(3, LocalDate.now().minusDays(1), LocalDateTime.now().minusHours(10), "Evento de baixa prioridade");
        notificador.notificar(evento3);
    }
}
