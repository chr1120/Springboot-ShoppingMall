package com.chr.ourhome.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.chr.ourhome.handler.ex.OutOfStockException;
import com.chr.ourhome.util.Script;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(OutOfStockException.class)
	public String exception(OutOfStockException e) {
		return Script.back(e.getMessage());
	}
}
