package com.tledu.zyf.util;

public class AjaxObj {
	/**
	 * 1 表示成功 , 0 表示失败
	 */
	private int result;

	/**
	 * 处理完成的提示信息
	 */
	private String msg;

	/**
	 * 附加对象
	 */
	private Object object;

	public String toJSON() {
		String jsonStr = "{" + "\"result\":\"" + result + "\",\"msg\":\"" + msg + "\",\"object\":" + object + "}";
		return jsonStr;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public AjaxObj(int result, String msg, Object object) {
		super();
		this.result = result;
		this.msg = msg;
		this.object = object;
	}

	public AjaxObj(int result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}

	public AjaxObj() {
		super();
	}
}
