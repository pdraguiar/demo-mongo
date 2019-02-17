package com.example.demomongo.service;

import com.example.demomongo.model.Favorite;
import com.example.demomongo.model.User;
import com.example.demomongo.repository.UserRepository;
import com.example.demomongo.repository.UserRepositoryCustomImpl;
import com.mongodb.client.gridfs.model.GridFSFile;
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

    public void gridfs() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 1000; i++) {
            builder.append(String.format("coluna1%d;coluna2%d;coluna3%d;coluna4%d;",i,i,i,i));
            builder.append("\n");
        }

        userRepositoryImpl.gridfs(builder.toString());
    }

    public GridFSFile getfile(String filename) {
        return userRepositoryImpl.getFile(filename);
    }
}
