package com.foretruff.spring.listener.entity;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

    @EventListener
    public void accept(EntityEvent entityEvent) {
        System.out.println("Entity: " + entityEvent);
    }

}
