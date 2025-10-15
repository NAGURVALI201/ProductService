package com.social.productservice.InheritanceDemo.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
/*
@Getter
@Setter
@Entity(name ="mapped_Instructor")
public class Instructor extends User{
    private String subject;
    private Double rating;
}
*/

public class Instructor extends User{
    private String subject;
    private Double rating;
}