package com.bhochhi.shriyan;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("")
public class HelloWorld {

	@GET
	public String test(){
		return "Hello World!!";
	}
}
