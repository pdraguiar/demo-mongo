package com.example.demomongo.service;

import com.example.demomongo.model.Favorite;
import com.example.demomongo.model.User;
import com.example.demomongo.repository.UserRepository;
import com.example.demomongo.repository.UserRepositoryCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryCustomImpl userRepositoryImpl;

    public User save(User user) {
        user.setId(UUID.randomUUID().toString());
        User save = userRepository.save(user);

        return save;
    }

    public Favorite deleteFavorite(String id, Favorite favorite) {
        userRepositoryImpl.pullFavorite(id, favorite.getFavoriteID(), favorite.getType());

        return favorite;
    }
}
