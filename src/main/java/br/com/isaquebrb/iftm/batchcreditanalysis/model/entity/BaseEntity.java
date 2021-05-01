package br.com.isaquebrb.iftm.batchcreditanalysis.model.entity;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class BaseEntity {

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void persist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void update() {
        this.createdAt = LocalDateTime.now();
    }

}
