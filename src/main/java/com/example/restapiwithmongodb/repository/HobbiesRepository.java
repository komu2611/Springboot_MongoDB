package com.example.restapiwithmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.restapiwithmongodb.model.Hobbies;

@Repository
public interface HobbiesRepository   extends MongoRepository<Hobbies,Integer> {
	
	@Query(value = "{$lookup: { from: 'hobbies', pipeline: [{ $match: { id: ?0} },{ $project: { 'userId': $userId , 'hobby' : $hobby} }] ,as: 'userHobbies'}}")
	List<Hobbies> findHobbiesbyUsername(Integer id);

}
