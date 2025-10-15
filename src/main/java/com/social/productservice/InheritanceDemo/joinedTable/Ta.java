package com.social.productservice.InheritanceDemo.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;
/*
@Getter
@Setter
@Entity(name = "joined_tas")
@PrimaryKeyJoinColumn(name = "user_id")
public class Ta extends User {
    private Long requestSolved;
}

*/
public class Ta extends User {
    private Long requestSolved;
}