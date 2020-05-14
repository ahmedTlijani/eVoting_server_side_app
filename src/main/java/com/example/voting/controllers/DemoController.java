package com.example.voting.controllers;

import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//https://spring.io/guides/gs/rest-service/

@RestController
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/greeting/{name}")
	public String Hello(@PathVariable("name") String name)
	{
		String a = "helllo " + name;
		return a;
	}
	
	@RequestMapping("/hash/{key}")
	public String getHash(@PathVariable("key") String key)
	{
		String sha256hex = DigestUtils.sha256Hex(key);
		return sha256hex;
	}
	
	
}
