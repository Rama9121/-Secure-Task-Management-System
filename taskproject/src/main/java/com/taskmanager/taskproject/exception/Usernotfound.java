package com.taskmanager.taskproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)//404

public class Usernotfound extends RuntimeException {
	
    private  String message;
  public Usernotfound(String message) {
	  super(message);
	  this.message=message;
  }
}
