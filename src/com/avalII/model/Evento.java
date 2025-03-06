package com.avalII.model;
import java.time.*;

public class Evento {
    private int prioridade;
    private LocalDate dataInicio;
    private LocalDateTime dataHoraInicio;
    private String descricao;
    
    public Evento(int prioridade, LocalDate dataInicio, LocalDateTime dataHoraInicio, String descricao) {
        this.prioridade = prioridade;
        this.dataInicio = dataInicio;
        this.dataHoraInicio = dataHoraInicio;
        this.descricao = descricao;
    }
    
    public int getPrioridade() {
        return prioridade;
    }
    
    public boolean iniciaEm(LocalDate data) {
        return dataInicio.equals(data);
    }
    
    public boolean iniciaEntre(LocalDateTime inicio, LocalDateTime fim) {
        return !dataHoraInicio.isBefore(inicio) && !dataHoraInicio.isAfter(fim);
    }
    
    public String getDescricao() {
        return descricao;
    }
}
