package com.proto.linksaver.repository;

import com.proto.linksaver.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends MongoRepository<Link, String> {
    void deleteAllByCategoryId(String categoryId);
}
