package com.social.productservice.InheritanceDemo.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

/*
* if we add the @PrimaryKeyJoinColumn(name="user_id") the PK in the child class
* will have name as user_id instead of id.
*/
@Getter
@Setter
@Entity(name = "joined_instructor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User {
    private String subject;
    private Double rating;
}
