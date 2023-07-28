package com.Recipemanagementsystem.Repository;

import com.Recipemanagementsystem.Model.Like;
import com.Recipemanagementsystem.Model.Recipe;
import com.Recipemanagementsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@Repository
public interface ILikeRepo extends JpaRepository<Like,Long> {
    List<Like> findByRecipePostAndLiker(Recipe recipe, User liker);


    List<Like> findByRecipePost(Recipe validPost);
}
