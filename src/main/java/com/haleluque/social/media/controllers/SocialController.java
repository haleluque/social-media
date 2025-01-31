package com.haleluque.social.media.controllers;

import com.haleluque.social.media.models.SocialUser;
import com.haleluque.social.media.services.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocialController {
    @Autowired
    private SocialService socialService;


    /**
     * Default fetch types:
     * OneToMany: Lazy
     * ManyToOne: Eager
     * ManyToMany: Lazy
     * OneToOne: Eager
     */
    @GetMapping("/social/users")
    public ResponseEntity<List<SocialUser>> getUsers(){
        return new ResponseEntity<>(socialService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/social/users")
    public ResponseEntity<SocialUser> saveUser(@RequestBody SocialUser socialUser){
        return new ResponseEntity<>(socialService.saveUser(socialUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/social/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        socialService.delete(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
