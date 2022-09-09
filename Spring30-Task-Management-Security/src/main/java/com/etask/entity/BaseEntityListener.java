package com.etask.entity;

import com.etask.entity.common.UserPrinciple;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.nio.file.attribute.UserPrincipal;
import java.time.LocalDateTime;

@Component
public class BaseEntityListener extends AuditingEntityListener {

    @PrePersist
    private void onPrePersist(BaseEntity baseEntity){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        baseEntity.setInsertDateTime(LocalDateTime.now());
        baseEntity.lastUpdatedDateTime = LocalDateTime.now();
        baseEntity.insertUserId=1L;
        baseEntity.lastUpdatedUserId=1L;

        if(authentication !=null && !authentication.getName().equals("anonymousUser")){
            Object principle = authentication.getPrincipal();
            baseEntity.insertUserId = ((UserPrinciple) principle).getId();
            baseEntity.lastUpdatedUserId = ((UserPrinciple) principle).getId();
        }
    }

    @PreUpdate
    private void onPreUpdate(BaseEntity baseEntity){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        baseEntity.setInsertDateTime(LocalDateTime.now());
        baseEntity.lastUpdatedDateTime = LocalDateTime.now();
        baseEntity.lastUpdatedUserId=1L;

        if(authentication !=null && !authentication.getName().equals("anonymousUser")){
            Object principle = authentication.getPrincipal();
            baseEntity.lastUpdatedUserId = ((UserPrinciple) principle).getId();
        }
    }


}
