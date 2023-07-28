package com.Recipemanagementsystem.Service;

import com.Recipemanagementsystem.Model.AuthenticationToken;
import com.Recipemanagementsystem.Model.Comment;
import com.Recipemanagementsystem.Model.User;
import com.Recipemanagementsystem.Model.dto.SignInInput;
import com.Recipemanagementsystem.Model.dto.SignUpOutput;
import com.Recipemanagementsystem.Repository.IUserRepo;
import com.Recipemanagementsystem.Service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    public SignUpOutput SignUpUser(User user) {
        boolean SignUpStatus=true;
        String SignUpStatusMessage=null;
        String newEmail=user.getUserEmail();
        //check if email is null
        if(newEmail==null){
            SignUpStatusMessage="Invalid Email";
            SignUpStatus=false;
            return new SignUpOutput(SignUpStatus,SignUpStatusMessage);
        }
        //Check if user already exists??
        User ExistingUser=userRepo.findFirstByUserEmail(newEmail);
        if(ExistingUser!=null){
            SignUpStatusMessage="Email Already registered";
            SignUpStatus=false;
            return new SignUpOutput(SignUpStatus,SignUpStatusMessage);
        }
        //hash the password:encrypt the password
        try{
            String encryptedPassword= PasswordEncrypter.encryptPassword(user.getUserPassword());
            user.setUserPassword(encryptedPassword);
            userRepo.save(user);
            return new SignUpOutput(SignUpStatus,"User Registered Successfully.");
        }catch(Exception e){
            SignUpStatusMessage="Internal error occurred durring sign up";
            SignUpStatus=false;
            return new SignUpOutput(SignUpStatus,SignUpStatusMessage);

        }

    }

    public String signInUser(SignInInput signInInput) {
        String SignInStatusMessage = null;
        String SignInEmail=signInInput.getEmail();
        //check if email is null
        if(SignInEmail==null){
            SignInStatusMessage="Invalid Email ";
            return SignInStatusMessage;
        }
        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(SignInEmail);

        if(existingUser == null)
        {
            SignInStatusMessage = "Email not registered!!!";
            return SignInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingUser);

                authenticationService.saveAuthToken(authToken);


                return "Token sent to your email";
            }
            else {
                SignInStatusMessage = "Invalid credentials!!!";
                return SignInStatusMessage;
            }
        }
        catch(Exception e)
        {
            SignInStatusMessage = "Internal error occurred during sign in";
            return SignInStatusMessage;
        }

    }


    public String sigOutUser(String email) {
        User user = userRepo.findFirstByUserEmail(email);
        AuthenticationToken token = authenticationService.findFirstByUser(user);
        authenticationService.removeToken(token);
        return "User Signed out successfully";
    }

}
