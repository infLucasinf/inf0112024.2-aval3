package com.avalII.model.strategy;

import com.avalII.model.Evento;

public class WhatsAppFormatStrategy implements FormatStrategy {
    @Override
    public String format(Evento e) {
        return "WhatsApp: " + e.getDescricao();
    }
}
