package com.example.restapiwithmongodb.service;


import java.util.List;
import org.springframework.data.domain.Page;
import com.example.restapiwithmongodb.model.Hobbies;
import com.example.restapiwithmongodb.model.User;

public interface UserService {
	
	User saveUser(User user);

	void findByEmail(User user) throws Exception;

	void findById(Integer id) throws Exception;

	Page<User> pagination(int page, int size);

	List<Hobbies> getUserHobbies(Integer id);

	void addUserHobbies(Hobbies hobbies);

	List<User> getUsers();

	User getUser(String Username);

	void updateUser(User user) throws Exception;

}
