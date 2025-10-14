package com.social.productservice.InheritanceDemo.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tab_tas")
public class Ta extends User {
    private Long requestSolved;
}
