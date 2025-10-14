package com.social.productservice.InheritanceDemo.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_tas")
@DiscriminatorValue(value = "1")
public class Ta extends User {
    private Long requestSolved;
}
