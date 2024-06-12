package com.avecircle.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgRestController {
	
	@Value("${msg}")
	private String txtMsg;
	
	@GetMapping("/welcome")
	public String getMsg()
	{
		return txtMsg;
	}

}
