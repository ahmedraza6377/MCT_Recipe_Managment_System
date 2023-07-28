package com.Recipemanagementsystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="recipe_like")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "fk_recipe_id")
    private Recipe recipePost;

    @ManyToOne
    @JoinColumn(name = "fk_liker_id")
    private User liker;
}