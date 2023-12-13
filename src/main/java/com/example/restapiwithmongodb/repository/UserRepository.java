package com.example.restapiwithmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.restapiwithmongodb.model.User;

@Repository
public interface UserRepository  extends MongoRepository<User,Integer>{
	
	User findByEmail(String email);
	
	User findByUsername(String username);

}
