package com.example.demomongo.controller;

import com.example.demomongo.model.Favorite;
import com.example.demomongo.model.User;
import com.example.demomongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping(path = "/{id}")
    public Favorite deleteFavorite(@PathVariable("id") String id, @RequestBody Favorite favorite) {
        return userService.deleteFavorite(id, favorite);
    }
}
