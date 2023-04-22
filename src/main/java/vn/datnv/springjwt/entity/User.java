package vn.datnv.springjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.datnv.springjwt.enums.Gender;
import vn.datnv.springjwt.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(unique = true)
    private String username;
    
    @NotBlank
    private String password;
    
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Role userRole;
    
    private String fullName;
    
    private Instant dob;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    private String phone;
    
    private String address;
}
