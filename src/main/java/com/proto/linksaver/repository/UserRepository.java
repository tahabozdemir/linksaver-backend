package com.proto.linksaver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.proto.linksaver.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> { }
