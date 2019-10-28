package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cache.CacheProperty;
import com.example.constant.Constant;
import com.example.entity.UserEntity;
import com.example.pojo.User;
import com.example.repository.UserRepo;

@Controller
public class UserController {
	@Autowired
	private CacheProperty cache;

	@Autowired
	private UserRepo userRepo;

	/**
	 * this method is used to load user form
	 * 
	 * @param model
	 * @return String
	 */
	// @GetMapping
	@RequestMapping(value = "/register")
	public String loadForm(Model model) {
		User userObj = new User();
		if (userObj != null) {
			model.addAttribute(Constant.MODEL_KEY, userObj);
		}
		return Constant.FORM_LOGICAL_NAME;
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute(Constant.MODEL_KEY) User user, Model model) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		UserEntity s = userRepo.save(userEntity);
		if (s != null&&cache!=null) {
			return "redirect:dumGet";
		} else {
			model.addAttribute(Constant.RESPONSE_MESSAGE, cache.getMessages().get(Constant.FAILURE_MESSAGE_KEY));
			return Constant.FORM_LOGICAL_NAME;
		}
	}
	
	@RequestMapping("/dumGet")
	public String dummyGet(Model model) {
		model.addAttribute(Constant.RESPONSE_MESSAGE, cache.getMessages().get(Constant.SUCCESS_MESSAGE_KEY));
		return Constant.RESPONSE_PAGE;
	}

	/*
	 * config withot pagination
	 * 
	 * @RequestMapping(value = "/showUser") public String showUser(Model model) {
	 * Iterable<UserEntity> itr = userRepo.findAll(); List<User> listUser = new
	 * ArrayList<User>(); itr.forEach(itrs -> { User user = new User();
	 * BeanUtils.copyProperties(itrs, user); listUser.add(user); });
	 * model.addAttribute(Constant.USERS_DATA_KEY, listUser); return
	 * Constant.SHOW_USER_PAGE; }
	 */

	@RequestMapping(value = "/getEmails")
	public @ResponseBody List<String> getEmails() {
		return userRepo.findAllEmails();

	}

	@RequestMapping(value = "/getEmailById")
	public String getEmailById(@RequestParam("id") Integer id, Model model) {
		String res = userRepo.findEmailById(id);
		if (res != null) {
			model.addAttribute(Constant.RESPONSE_MESSAGE, res);
		} else {
			model.addAttribute(Constant.RESPONSE_MESSAGE, "Data Not Available");
		}
		return Constant.RESPONSE_PAGE;
	}

	@RequestMapping(value = "/showUser")
	public String showUser(Model model, @RequestParam("pn") Integer currPageNo) {
		PageRequest page = PageRequest.of(currPageNo - 1, 5);

		Page<UserEntity> pageData = userRepo.findAll(page);
		if(pageData!=null) {
		List<UserEntity> userEntities = pageData.getContent();
		int i = pageData.getTotalPages();
		List<User> listUser = new ArrayList<User>();
		userEntities.forEach(itrs -> {
			User user = new User();
			BeanUtils.copyProperties(itrs, user);
			listUser.add(user);
		});
		model.addAttribute(Constant.USERS_DATA_KEY, listUser);
		model.addAttribute(Constant.TOTAL_PAGE, i);
		model.addAttribute(Constant.CURRENT_PAGE_NO, currPageNo);
		}
		return Constant.SHOW_USER_PAGE;
	}

	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") Integer userid) {
		userRepo.deleteById(userid);
		return Constant.REDIRECT_PAGE;
	}

	@RequestMapping("/editUser")
	public String editUser(Model model, @RequestParam("userId") Integer userid) {
		Optional<UserEntity> opt = userRepo.findById(userid);
		if (opt.isPresent()) {
			UserEntity entity = opt.get();
			User use = new User();
			BeanUtils.copyProperties(entity, use);
			model.addAttribute("user", use);
		}
		return "updatePage";
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute(Constant.MODEL_KEY) User user, Model model,
			@RequestParam("userId") Integer userid) {
		UserEntity entity = new UserEntity();
		user.setUid(userid);
		BeanUtils.copyProperties(user, entity);
		UserEntity s = userRepo.save(entity);
		if (s != null) {
			model.addAttribute(Constant.RESPONSE_MESSAGE, cache.getMessages().get(Constant.UPDATE_MESSAGE_KEY));
		}
		return Constant.RESPONSE_PAGE;
	}

	/*
	 * @ExceptionHandler(value = NullPointerException.class) public String
	 * handleNullPointerExpction(Model model) { model.addAttribute("errMsg",
	 * "Some Problem Occured ,Please  Contact Adminstrator......"); return
	 * Constant.RESPONSE_PAGE;
	 * 
	 * }
	 */

}
