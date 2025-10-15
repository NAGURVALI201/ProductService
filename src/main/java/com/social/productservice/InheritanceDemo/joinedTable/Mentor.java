package com.social.productservice.InheritanceDemo.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;
/*
@Getter
@Setter
@Entity(name = "joined_mentors")
@PrimaryKeyJoinColumn(name = "user_id")
public class Mentor extends User {
    private String companyName;
}

*/

public class Mentor extends User {
    private String companyName;
}