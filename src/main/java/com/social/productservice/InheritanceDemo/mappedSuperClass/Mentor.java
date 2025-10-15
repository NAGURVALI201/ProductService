package com.social.productservice.InheritanceDemo.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
/*
@Getter
@Setter
@Entity(name = "mapped_mentor")
public class Mentor extends User{
    private String companyName;
}

*/
public class Mentor extends User{
    private String companyName;
}