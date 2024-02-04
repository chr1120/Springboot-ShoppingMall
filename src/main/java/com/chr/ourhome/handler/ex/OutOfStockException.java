package com.chr.ourhome.handler.ex;

public class OutOfStockException extends RuntimeException{
	
	// 객체를 구분할 때
	private static final long serialVersionUID=1L;
	
	public OutOfStockException(String message) {
		super(message);
	}
}
