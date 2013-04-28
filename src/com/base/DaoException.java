package com.base;

@SuppressWarnings("serial")
public class DaoException extends RuntimeException {
	private String code;

	private String[] params;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public DaoException(String code,String[] params,Exception e) {
		
		super(code,e);
		this.setCode(code);
		this.setParams(params);
	}
}
