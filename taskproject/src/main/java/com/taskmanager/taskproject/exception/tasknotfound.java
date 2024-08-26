package com.taskmanager.taskproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class tasknotfound extends RuntimeException {
	 public String message;
	 public tasknotfound(String message) {
		super(message);
		this.message =message;
	}
}
