package com.proto.linksaver.repository;

import com.proto.linksaver.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends MongoRepository<Link, String> {
    List<Link> findByUserIdAndIsFavorite(String userId, Boolean isFavorite);
    List<Link> findByUserIdAndTitleLikeIgnoreCase(String userId, String title);
    List<Link> findByUserIdAndIsFavoriteAndTitleLikeIgnoreCase(String userId, Boolean isFavorite, String title);
    List<Link> findByUserId(String userId);
}
