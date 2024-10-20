package com.sayan.enotes.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {

    private Boolean isActive;
    private Boolean isDeleted;
    private Integer createdBy;
    private Date createdAt;
    private Integer updatedBy;
    private Date updatedAt;
}
