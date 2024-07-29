package com.proto.linksaver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.proto.linksaver.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
