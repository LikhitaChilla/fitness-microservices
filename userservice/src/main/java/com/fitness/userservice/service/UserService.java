package com.fitness.userservice.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	private ModelMapper map=new ModelMapper();
	public UserResponse register(RegisterRequest request) {
		
		if(repo.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already exisits");
		}
		System.out.println(request);
		User user=new User();
		user.setEmail(request.getEmail());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setPassword(request.getPassword());
		User saveduser=repo.save(user);
		UserResponse res=new UserResponse();
		res=map.map(saveduser,UserResponse.class);
		return res;
	}
	public UserResponse getUserProfile(String userId) {
		User user=repo.findById(userId)
				.orElseThrow(()-> new RuntimeException("User Not Found"));
		UserResponse res=map.map(user,UserResponse.class);
		return res;
	}

}
