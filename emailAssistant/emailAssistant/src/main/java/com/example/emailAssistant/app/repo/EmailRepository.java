package com.example.emailAssistant.app.repo;

import com.example.emailAssistant.app.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
    List<EmailEntity> findByRepliedFalseAndIntent(String intent);
    List<EmailEntity> findByFollowUpSentFalseAndPriority(String priority);
}

