package com.social.productservice.InheritanceDemo.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mapped_ta")
public class Ta extends User {
    private Long requestSolved;
}
