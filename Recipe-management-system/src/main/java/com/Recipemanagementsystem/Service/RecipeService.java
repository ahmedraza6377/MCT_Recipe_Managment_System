package com.Recipemanagementsystem.Service;

import com.Recipemanagementsystem.Model.Recipe;
import com.Recipemanagementsystem.Model.User;
import com.Recipemanagementsystem.Repository.IRecipeRepo;
import com.Recipemanagementsystem.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipeService {
    @Autowired
    IRecipeRepo recipeRepo;

    @Autowired
    IUserRepo userRepo;

    public String createRecipe(Recipe recipe, String email) {
        User recipeOwner = userRepo.findFirstByUserEmail(email);
        recipe.setRecipeOwner(recipeOwner);
        recipe.setRecipeCreatedTimeStamp(LocalDateTime.now());
        recipeRepo.save(recipe);
        return "recipe added";
    }


    public String removeRecipe(Long recipeId, String email) {
        User user = userRepo.findFirstByUserEmail(email);
         //for checking recipe belong to this user or not
        Recipe recipe  = recipeRepo.findById(recipeId).orElse(null);
        if(recipe != null && recipe.getRecipeOwner().equals(user))
        {
            recipeRepo.deleteById(recipeId);
            return "Removed successfully";
        }
        else if (recipe == null)
        {
            return "Recipe to be deleted does not exist";
        }
        else{
            return "Un-Authorized delete detected....Not allowed";
        }
    }

    public Recipe updateRecipe(Long recipeId, Recipe recipe, String email, String token) {
        Recipe existingRecipe = recipeRepo.getRecipeById(recipeId);
        if (existingRecipe != null && existingRecipe.getRecipeOwner().getUserEmail().equals(email)) {
            existingRecipe.setName(recipe.getName());
            existingRecipe.setIngredients(recipe.getIngredients());
            existingRecipe.setInstructions(recipe.getInstructions());
            return recipeRepo.save(existingRecipe);
        }
        return null;
    }

    public Recipe getRecipeById(Long recipeId) {
        return recipeRepo.findById(recipeId).orElse(null);
    }

    public List<Recipe> getRecipesOfAOwner(String email) {
        User recipeOwner = userRepo.findFirstByUserEmail(email);

        return recipeRepo.findByRecipeOwner(recipeOwner);

    }

    public boolean validatePost(Recipe recipePost) {
        return (recipePost!=null && recipeRepo.existsById(recipePost.getId()));
    }
}
