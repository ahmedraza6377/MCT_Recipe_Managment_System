package com.Recipemanagementsystem.Controller;

import com.Recipemanagementsystem.Model.Comment;
import com.Recipemanagementsystem.Service.AuthenticationService;
import com.Recipemanagementsystem.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("comment")
    public String addComment(@RequestBody Comment comment, @RequestParam String commenterEmail, @RequestParam String commenterToken)
    {
        if(authenticationService.authenticate(commenterEmail,commenterToken)) {
            return commentService.addComment(comment,commenterEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }




    @DeleteMapping("comment")
    public String removeRecipeComment(@RequestParam Long commentId, @RequestParam String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return commentService.removeRecipeComment(commentId,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @GetMapping("/{recipeId}/comments")
    public List<Comment> getCommentsByRecipeId(@PathVariable Long recipeId,@RequestParam String email, @RequestParam String token) {
        if(authenticationService.authenticate(email,token)) {
            return commentService.getCommentsByRecipeId(recipeId);
        }
        else {
            return null;
        }
    }
}
