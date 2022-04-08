package com.example.demo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.stereotype.Component;

@Component
public class Database {

    private final MongoDatabaseFactory mongo;

    public Database(MongoDatabaseFactory mongo) {
        this.mongo = mongo;
    }

    public MongoCollection<Document> getCollection() {
        MongoDatabase db = this.mongo.getMongoDatabase();
        return db.getCollection("people");
    }
}