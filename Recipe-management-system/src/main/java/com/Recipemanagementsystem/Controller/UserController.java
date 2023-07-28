package com.Recipemanagementsystem.Controller;

import com.Recipemanagementsystem.Model.User;
import com.Recipemanagementsystem.Model.dto.SignInInput;
import com.Recipemanagementsystem.Model.dto.SignUpOutput;
import com.Recipemanagementsystem.Service.AuthenticationService;
import com.Recipemanagementsystem.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signup")
    public SignUpOutput signUpUser(@RequestBody User user){

           return userService.SignUpUser(user);
    }

    @PostMapping("/signIn")
    public String signInUser(@RequestBody @Valid SignInInput signInInput){

        return userService.signInUser(signInInput);
    }



    @DeleteMapping("user/signOut")
    public String sigOutUser(@RequestParam  String email,@RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.sigOutUser(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }

    }





}
