package com.oauth.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.oauth.DTO.OauthDTO;

@RestController
@RequestMapping("/Oauth")
public class OauthController {

	@PostMapping("/client/login/userId")
	public OauthDTO getOauthLogin(@RequestParam String googleUserIdToken) throws Throwable {
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
				.setAudience(Collections
						.singletonList("Your client Id which is created by you in developer.google.account"))
				.build();
		GoogleIdToken idToken = verifier.verify(googleUserIdToken);

		if (idToken != null) {
			Payload payload = idToken.getPayload();
			String email = ((com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload) payload).getEmail();
			boolean emailVerified = Boolean.valueOf(((com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload) payload).getEmailVerified());
			String name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");
			String locale = (String) payload.get("locale");
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
			
			OauthDTO oauthDTO=new OauthDTO();
			oauthDTO.setEmail(email);
			oauthDTO.setEmailVerified(emailVerified);
			oauthDTO.setName(name);
			oauthDTO.setFamilyName(familyName);
			oauthDTO.setGivenName(givenName);
			oauthDTO.setPayload(payload);
			oauthDTO.setPictureUrl(pictureUrl);
			oauthDTO.setLocale(locale);
			return oauthDTO;
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Something not right");

	}

}
