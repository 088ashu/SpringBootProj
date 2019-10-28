package com.example.exceptions;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.cache.CacheProperty;
import com.example.constant.Constant;

@Controller
@RestController
@ControllerAdvice
public class AppExceptionHandler {

	@Autowired
	private CacheProperty cache;

	@ExceptionHandler(value = NullPointerException.class)
	public String hndleNullPointerException(Model model) {
		model.addAttribute(Constant.RESPONSE_MESSAGE, cache.getMessages().get(Constant.NULL_MESSAGE_KEY));
		return Constant.RESPONSE_PAGE;
	}

	@ExceptionHandler(value = NumberFormatException.class)
	public String hndleNumberFormatException(Model model) {
		model.addAttribute(Constant.RESPONSE_MESSAGE, cache.getMessages().get(Constant.FORMAT_MESSAGE_KEY));
		return Constant.RESPONSE_PAGE;
	}

	@ExceptionHandler(value = IllegalInputException.class)
	public ResponseEntity<ApiError> handleIllegalInputException() {
		ApiError error = new ApiError(400, "No User Found", new Date());
		return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
	}

}
