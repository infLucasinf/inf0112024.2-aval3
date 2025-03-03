package com.avalII.model.strategy.notificacao.config;
import java.util.List;
import java.util.function.Predicate;

import com.avalII.model.Evento;
import com.avalII.model.strategy.FormatStrategy;
import com.avalII.model.strategy.notificacao.CanalNotificacao;

public class NotificationConfiguration {
    private Predicate<Evento> condition;
    private FormatStrategy formatStrategy;
    private List<CanalNotificacao> canais;
    
    public NotificationConfiguration(Predicate<Evento> condition, FormatStrategy formatStrategy, List<CanalNotificacao> canais) {
        this.condition = condition;
        this.formatStrategy = formatStrategy;
        this.canais = canais;
    }
    
    public boolean isApplicable(Evento e) {
        return condition.test(e);
    }
    
    public FormatStrategy getFormatStrategy() {
        return formatStrategy;
    }
    
    public List<CanalNotificacao> getCanais() {
        return canais;
    }
}
