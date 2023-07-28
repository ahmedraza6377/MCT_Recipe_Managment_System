package com.Recipemanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    @NotNull(message = "Ingredients cannot be null")
    private List<String> ingredients;

    @NotNull(message = "Instructions cannot be null")
    private List<String> instructions;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // hide this in json but not in database table column
    private LocalDateTime recipeCreatedTimeStamp;



    @ManyToOne
    @JoinColumn(name = "fk_recipePost_user_id")
    private User recipeOwner;

    // constructors, getters, setters, etc.
}
