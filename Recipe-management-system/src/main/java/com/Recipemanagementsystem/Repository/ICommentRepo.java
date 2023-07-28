package com.Recipemanagementsystem.Repository;

import com.Recipemanagementsystem.Model.Comment;
import com.Recipemanagementsystem.Model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findByRecipePost(Recipe recipe);
}
