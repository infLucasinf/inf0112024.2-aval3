package com.avalII.model.strategy;
import com.avalII.model.Evento;

public class EmailFormatStrategy implements FormatStrategy {
    @Override
    public String format(Evento e) {
        return "Email: " + e.getDescricao();
    }
}
