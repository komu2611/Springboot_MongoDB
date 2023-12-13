package com.example.restapiwithmongodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.restapiwithmongodb.model.Hobbies;
import com.example.restapiwithmongodb.model.User;
import com.example.restapiwithmongodb.repository.HobbiesRepository;
import com.example.restapiwithmongodb.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private HobbiesRepository hobbiesRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

	@Override
	public void findByEmail(User user) throws Exception {
		User userDetails = userRepository.findByEmail(user.getEmail());
		if (userDetails == null || !userDetails.getPassword().equals(user.getPassword())) {
	        throw new Exception();
	    }
	}

	@Override
	public void findById(Integer id) throws Exception {
		Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
        	throw new Exception();
        }
		userRepository.deleteById(id);	
	}

	@Override
	public Page<User> pagination(int page, int size) {
		Pageable pageable = PageRequest.of(page,size);
        return userRepository.findAll(pageable);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public List<Hobbies> getUserHobbies(Integer id) {
		return hobbiesRepository.findHobbiesbyUsername(id);
	}

	@Override
	public void addUserHobbies(Hobbies hobbies) {
		hobbiesRepository.save(hobbies);
	}


	@Override
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void updateUser(User user) throws Exception {
		User userDetails = userRepository.findByUsername(user.getUsername());
        if (userDetails != null) {
        	userDetails.setEmail(user.getEmail());
        	String encodedPassword = passwordEncoder.encode(user.getPassword());
        	userDetails.setPassword(encodedPassword);
    		userRepository.save(userDetails);
        } else {
        	throw new Exception();	
        }
		
	}
	
}
