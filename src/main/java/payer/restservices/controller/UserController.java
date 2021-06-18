package payer.restservices.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import payer.restservices.dto.User;

@RestController
@Api(value = "Merchant",  description = "REST API for Merchant Validation", tags = { "Merchant" })
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/merchant")
	public User authenticateUser(@RequestParam("merchantid") String merchant, @RequestParam("secretkey") String pwd) {

		User user = new User();
		user.setMerchant(merchant);
		//user.setPwd(pwd);

		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(merchant, pwd));
			String token = getJWTToken(merchant, pwd);
			user.setToken(token);
			user.setResponse("Merchant Request successfully accessed");

			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (AuthenticationException e) {
			user.setErrorMsg(e.getLocalizedMessage());
			user.setToken(null);
		}

		return user;
	}

	private String getJWTToken(String username, String msecretKey) {


		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		Map<String, Object> headerMap = new HashMap<String, Object>();
		headerMap.put("alg", "HS256");
		headerMap.put("typ", "JWT");
		String token = Jwts.builder().setHeaderParams(headerMap).setId("merchantJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 60000000))
				.signWith(SignatureAlgorithm.HS256, msecretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}