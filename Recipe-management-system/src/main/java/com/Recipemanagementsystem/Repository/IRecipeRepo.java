package com.Recipemanagementsystem.Repository;

import com.Recipemanagementsystem.Model.Recipe;
import com.Recipemanagementsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecipeRepo extends JpaRepository<Recipe,Long> {
    Recipe getRecipeById(Long recipeId);

    List<Recipe> findByRecipeOwner(User recipeOwner);
}
