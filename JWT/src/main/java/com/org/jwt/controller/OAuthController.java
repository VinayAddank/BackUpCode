package com.org.jwt.controller;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String index() {

		return "Hello";

	}

}
