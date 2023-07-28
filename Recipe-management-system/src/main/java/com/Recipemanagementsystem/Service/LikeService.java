package com.Recipemanagementsystem.Service;

import com.Recipemanagementsystem.Model.Like;
import com.Recipemanagementsystem.Model.Recipe;
import com.Recipemanagementsystem.Model.User;
import com.Recipemanagementsystem.Repository.ILikeRepo;
import com.Recipemanagementsystem.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    ILikeRepo likeRepo;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    RecipeService recipeService;


    public boolean isLikeAllowedOnThisPost(Recipe recipe, User liker) {

        List<Like> likeList = likeRepo.findByRecipePostAndLiker(recipe,liker);
        return likeList!=null && likeList.isEmpty();
    }

    public String addLike(Like like, String likeEmail) {
        Recipe recipe = like.getRecipePost();
        boolean postValid = recipeService.validatePost(recipe);

        if(postValid) {


            User liker = userRepo.findFirstByUserEmail(likeEmail);
            if(isLikeAllowedOnThisPost(recipe,liker))
            {
                like.setLiker(liker);
                likeRepo.save(like);
                return "liked";
            }
            else {
                return "Already Liked!!";
            }

        }
        else {
            return "Cannot like on Invalid Post!!";
        }
    }

    public String getLikeCountByRecipe(Long recipeId, String userEmail) {

        Recipe validPost = recipeService.getRecipeById(recipeId);

        if(validPost != null)
        {
            Integer likeCountForPost = getLikeCountForRecipePost(validPost);
            return String.valueOf(likeCountForPost);
        }
        else {
            return "Cannot like on Invalid Post!!";
        }
    }
    public Integer getLikeCountForRecipePost(Recipe validPost) {
        return likeRepo.findByRecipePost(validPost).size();
    }



    private boolean authorizeLikeRemover(String likerEmail, Like like) {
        String  commentOwnerEmail = like.getLiker().getUserEmail();
        String  postOwnerEmail  = like.getRecipePost().getRecipeOwner().getUserEmail();

        return postOwnerEmail.equals(likerEmail) || commentOwnerEmail.equals(likerEmail);
    }

    public String removeRecipeLike(Long likeId, String likerEmail) {
        Like like  = likeRepo.findById(likeId).orElse(null);
        if(like!=null)
        {
            if(authorizeLikeRemover(likerEmail,like))
            {
                likeRepo.delete(like);
                return "like deleted successfully";
            }
            else
            {
                return "Unauthorized delete detected...Not allowed!!!!";
            }

        }
        else
        {
            return "Invalid like";
        }
    }


}
