package com.example.demomongo.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private GridFsOperations gridFsOperations;

    @Override
    public void pullFavorite(String id, Long favoriteID, String type) {
        mongoTemplate.updateMulti(new Query(Criteria.where("_id").is(id)),new Update().pull("favorites",
                Query.query(Criteria.where("favoriteID").is(favoriteID)
                        .and("type").is(type))),"user");
    }

    public void gridfs(String toString) {
        DBObject metaData = new BasicDBObject();
        metaData.put("user", "alex");
        ByteArrayInputStream content = new ByteArrayInputStream(toString.getBytes(StandardCharsets.UTF_8));
        gridFsOperations.store(content, "teste.csv", "text/csv", metaData);
    }

    public GridFSFile getFile(String filename) {
        return gridFsOperations.findOne(
                new Query().addCriteria(GridFsCriteria.whereFilename().is(filename)));
    }
}
