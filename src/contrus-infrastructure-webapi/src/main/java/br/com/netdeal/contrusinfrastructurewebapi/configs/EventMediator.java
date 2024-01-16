package br.com.netdeal.contrusinfrastructurewebapi.configs;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventMediator {
    private final ApplicationEventPublisher eventPublisher;

    public EventMediator(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishEvent(Object event){
        eventPublisher.publishEvent(event);
    }
}