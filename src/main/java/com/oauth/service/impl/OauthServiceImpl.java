package com.oauth.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.oauth.DTO.OauthDTO;
import com.oauth.service.OauthService;

@Service
@Transactional
public class OauthServiceImpl implements OauthService {
	
	@Override
	public OauthDTO oauthLogin() {
		
		return null;
	}

}
