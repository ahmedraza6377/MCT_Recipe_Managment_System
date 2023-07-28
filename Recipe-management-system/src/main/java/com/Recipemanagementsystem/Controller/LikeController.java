package com.Recipemanagementsystem.Controller;

import com.Recipemanagementsystem.Model.Like;
import com.Recipemanagementsystem.Service.AuthenticationService;
import com.Recipemanagementsystem.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController {

    @Autowired
    LikeService likeService;

    @Autowired
    AuthenticationService authenticationService;



    @PostMapping("like")
    public String addLike(@RequestBody Like like, @RequestParam String likeEmail, @RequestParam String likerToken)
    {
        if(authenticationService.authenticate(likeEmail,likerToken)) {
            return likeService.addLike(like,likeEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @GetMapping("like/count/recipe/{recipeId}")
    public String getLikeCountByRecipe(@PathVariable Long recipeId, @RequestParam String userEmail, @RequestParam String userToken)
    {
        if(authenticationService.authenticate(userEmail,userToken)) {
            return likeService.getLikeCountByRecipe(recipeId,userEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @DeleteMapping("like")
    public String removeRecipeLike(@RequestParam Long likeId, @RequestParam String likerEmail, @RequestParam String likerToken)
    {
        if(authenticationService.authenticate(likerEmail,likerToken)) {
            return likeService.removeRecipeLike(likeId,likerEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

}
