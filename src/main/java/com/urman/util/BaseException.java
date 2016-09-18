package com.urman.util;

public class BaseException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String str;
	
    public BaseException(String str) {
       this.str=str;
    }
    @Override
    public String toString(){ 
       return (str) ;
    }

}
