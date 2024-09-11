package org.example.springmvcdemo.ds;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "FirstName cannot be blank!")
    @NotEmpty(message = "FirstName cannot be empty!")
    private String firstName;
    @NotBlank(message = "LastName cannot be blank!")
    @NotEmpty(message = "LastName cannot be empty!")
    private String lastName;
    @Email(message = "Invalid Email Format!")
    private String email;
    @Past(message = "Date of Birth must be past!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOFBirth;
}
