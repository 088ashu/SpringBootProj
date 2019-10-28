package com.example.rest;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.UserEntity;
import com.example.exceptions.IllegalInputException;
import com.example.pojo.User;
import com.example.repository.UserRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest")
@Api(value="MY API")
public class UserRestController {

	/**
	 * This is for storing Repository class object
	 */
	@Autowired
	private UserRepo repo;

	/**
	 * This method return user record based on given userId
	 * 
	 * @param uid
	 * @return String
	 * @throws IllegalInputException
	 */
	@ApiOperation(value="Get User Record Based On given Id" ,response=String.class,tags= "getUser")
	@GetMapping(value = "/getUser", produces = "application/json")
	public User getUserById(@RequestParam("id") Integer uid) throws IllegalInputException {
		// create user object
		User user = new User();
		Integer[] itr = repo.getAllId(uid);
		for (int i = 0; i < itr.length; i++) {
			if (uid == itr[i]) {
				Optional<UserEntity> opt = repo.findById(uid);
				// get data
				UserEntity entity = opt.get();
				BeanUtils.copyProperties(entity, user);
				return user;
			}
		}
		throw new IllegalInputException();
	}
}
