package com.Recipemanagementsystem.Service;

import com.Recipemanagementsystem.Model.Recipe;
import com.Recipemanagementsystem.Model.User;
import com.Recipemanagementsystem.Model.Comment;
import com.Recipemanagementsystem.Repository.ICommentRepo;
import com.Recipemanagementsystem.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CommentService {
    @Autowired
    ICommentRepo commentRepo;

    @Autowired
    IUserRepo userRepo;
    @Autowired
    RecipeService recipeService;

    public String addComment(Comment comment, String commenterEmail) {
        boolean postValid = recipeService.validatePost(comment.getRecipePost());
        if(postValid) {
            User commenter = userRepo.findFirstByUserEmail(commenterEmail);
            comment.setCommenter(commenter);
            comment.setCommentCreationTimeStamp(LocalDateTime.now());
            commentRepo.save(comment);
            return "Comment has been added!!!";
        }
        else {
            return "Cannot comment on Invalid Post!!";
        }
    }


    private boolean authorizeCommentRemover(String email, Comment comment) {
        String  commentOwnerEmail = comment.getCommenter().getUserEmail();
        String  postOwnerEmail  = comment.getRecipePost().getRecipeOwner().getUserEmail();

        return postOwnerEmail.equals(email) || commentOwnerEmail.equals(email);
    }
    public String removeRecipeComment(Long commentId, String email) {

        Comment comment  = commentRepo.findById(commentId).orElse(null);
        if(comment!=null)
        {
            if(authorizeCommentRemover(email,comment))
            {
                commentRepo.delete(comment);
                return "comment deleted successfully";
            }
            else
            {
                return "Unauthorized delete detected...Not allowed!!!!";
            }

        }
        else
        {
            return "Invalid Comment";
        }
    }


    public List<Comment> getCommentsByRecipeId(Long recipeId) {
        Recipe recipe=recipeService.getRecipeById(recipeId);
        return commentRepo.findByRecipePost(recipe);
    }
}
