package com.foretruff.spring.listener.entity;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class EntityEvent extends ApplicationEvent {
    @Getter
    private final AccessType accessType;

    public EntityEvent(Object entity,
                       AccessType accessType) {
        super(entity);
        this.accessType = accessType;
    }

}
