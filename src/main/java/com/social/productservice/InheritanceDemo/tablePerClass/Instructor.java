package com.social.productservice.InheritanceDemo.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tab_instructors")
public class Instructor extends User {
    private String subject;
    private Double rating;
}
