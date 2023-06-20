package org.itstep.studentservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
    @Column(name = "first_name", nullable = false)
    @NotBlank
    @Length(min = 3, max = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NotBlank
    @Length(min = 3, max = 50)
    private String lastName;
    @Column(name = "birthday")
    @Past
    private Date birthday;
    @Pattern(regexp = "^\\+380\\d{9}$")
    private String phone;
    @Email
    private String email;
}
