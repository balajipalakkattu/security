package io.egs.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import io.egs.security.util.SecurityUtil;
import io.jsonwebtoken.Header;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

	@Autowired
	private SecurityUtil security;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization = request.getHeader("Authorization");
		String userName = null;
		if(authorization != null && authorization.startsWith("Bearer ")) {
			String jwt = authorization.substring(7);
			userName = security.extractUserName(jwt);
		}
		if(userName != null) {
			//security.
		}
		//security.generatetoken(user);
	}

}
