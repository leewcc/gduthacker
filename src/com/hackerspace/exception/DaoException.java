package com.hackerspace.exception;

/**
 * Dao层异常;
 * @author tianx
 *
 */
public final class DaoException extends Exception{
	
	public DaoException() {
		super("Dao Exception");
	}
	
	public DaoException(Throwable throwable) {
		super(throwable);
	}
	
	public DaoException(String msg) {
		super(msg);
	}
	
}
