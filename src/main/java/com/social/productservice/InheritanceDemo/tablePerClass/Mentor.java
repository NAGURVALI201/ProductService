package com.social.productservice.InheritanceDemo.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
/*
@Getter
@Setter
@Entity(name = "tab_mentors")
public class Mentor extends User {
    private String companyName;
}
*/

public class Mentor extends User {
    private String companyName;
}
