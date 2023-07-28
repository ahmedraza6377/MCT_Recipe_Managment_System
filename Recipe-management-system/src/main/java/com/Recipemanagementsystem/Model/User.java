package com.Recipemanagementsystem.Model;

import com.Recipemanagementsystem.Model.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
//    @Pattern(regexp = "\\b[A-Z][a-zA-Z']*\\b\n")
    private String userName;

    @NotNull
    @Email
    @Column(unique = true)
    private String userEmail;
    @NotBlank
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private Gender gender;





}
