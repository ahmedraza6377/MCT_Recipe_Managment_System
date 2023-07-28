package com.Recipemanagementsystem.Controller;

import com.Recipemanagementsystem.Model.Recipe;
import com.Recipemanagementsystem.Model.User;
import com.Recipemanagementsystem.Service.AuthenticationService;
import com.Recipemanagementsystem.Service.RecipeService;
import com.Recipemanagementsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {
    @Autowired
    RecipeService recipeService;



    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("recipe")
    public String createRecipe(@RequestBody Recipe recipe, @RequestParam String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return recipeService.createRecipe(recipe,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @DeleteMapping("recipe")
    public String removeRecipe(@RequestParam Long recipeId, @RequestParam String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return recipeService.removeRecipe(recipeId,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }


    @PutMapping("recipe/{recipeId}")
    public String updateRecipe(@PathVariable Long recipeId, @RequestBody Recipe recipe, @RequestParam String email, @RequestParam String token) {
        if(authenticationService.authenticate(email,token)) {
             Recipe rec=recipeService.updateRecipe(recipeId,recipe,email,token);
             return rec!=null?"recipe updated":"you are not owner of this recipe,if you want to give suggestion please use comments.";
        }
        else {
            return null;
        }
    }

    @GetMapping("recipe/{recipeId}")
    public Recipe getRecipeById(@PathVariable Long recipeId){
        return recipeService.getRecipeById(recipeId);
    }

    @GetMapping("Recipes/byOwner")
    public List<Recipe> getRecipesOfAOwner(@RequestParam String email, @RequestParam String token) {
        if(authenticationService.authenticate(email,token)) {

            return recipeService.getRecipesOfAOwner(email);
        }
        else {
            return null;
        }
    }
}
