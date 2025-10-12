package com.social.productservice.models;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
/*
* when do we use mapped superclass the base model.
* Is a real entity which we don't want to store in database
* All the attributes of it should be inherited and persisted in child class.
*/
@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    private Long id;
    private Date createdAt;
    private Date lastModifiedAt;
}
