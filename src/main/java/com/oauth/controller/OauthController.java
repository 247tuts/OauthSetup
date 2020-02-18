package com.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.DTO.OauthDTO;
import com.oauth.service.OauthService;

@RestController
@RequestMapping("/Oauth")
public class OauthController {

	@Autowired
	private OauthService oauthSerive;
	
	@PostMapping("/client/login/userId")
	public OauthDTO getOauthLogin(@RequestParam String userId) {
		return oauthSerive.oauthLogin() ;
	}
}
