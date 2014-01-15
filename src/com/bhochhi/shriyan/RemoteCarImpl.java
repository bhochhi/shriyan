package com.bhochhi.shriyan;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="remoteCar")
@SessionScoped
public class RemoteCarImpl implements RemoteCar, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String response;
	
	
	@Override
	public void moveLeft() {
		System.out.println("Moved Left");
		setResponse("Moved Left");
	}

	@Override
	public void moveRight() {
		System.out.println("Moved Right");
		setResponse("Moved Right");
	}

	@Override
	public void moveForward() {
		System.out.println("Moved Forward");
		setResponse("Moved Forward");
	}

	@Override
	public void moveReverse() {
		System.out.println("Move Reverse");
		setResponse("Moved Reverse");
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
