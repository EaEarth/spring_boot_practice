package com.practice.userservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstName;
    private String lastName;
    private int age;

    @Override
    public String toString(){
        return "id : " + this.userId + ", firstName: "+ this.firstName + ", lastName: " + this.lastName;
    }
}
