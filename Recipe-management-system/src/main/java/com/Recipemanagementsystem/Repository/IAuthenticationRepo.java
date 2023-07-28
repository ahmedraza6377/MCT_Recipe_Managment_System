package com.Recipemanagementsystem.Repository;


import com.Recipemanagementsystem.Model.AuthenticationToken;
import com.Recipemanagementsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {


    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByUser(User user);
}
