package com.example.demomongo.controller;

import com.example.demomongo.model.Favorite;
import com.example.demomongo.model.User;
import com.example.demomongo.service.UserService;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private GridFSBucket gridFSBucket;

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping(path = "/{id}")
    public Favorite deleteFavorite(@PathVariable("id") String id, @RequestBody Favorite favorite) {
        return userService.deleteFavorite(id, favorite);
    }

    @GetMapping(path = "/gridfs")
    public String gridfs() {
        userService.gridfs();

        return ""
;    }

    @GetMapping(path = "/gridfs/{filename}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> gridfs(@PathVariable("filename") String filename) throws IOException {
        GridFSFile file = userService.getfile(filename);

        File tempFile = File.createTempFile("teste", ".csv");

        FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
        gridFSBucket.downloadToStream(file.getId(), fileOutputStream);
        fileOutputStream.close();

        Resource resource = new PathResource(tempFile.getPath());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }
}
