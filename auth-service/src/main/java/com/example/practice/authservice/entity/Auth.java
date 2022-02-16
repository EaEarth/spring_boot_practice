package com.example.practice.authservice.entity;

import com.example.practice.authservice.enums.ERole;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.EnumSet;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="auths", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private EnumSet<ERole> role;

    public Auth(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
