package com.ssafy.security.auth;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtProvider jwtProvider;
	private final MemberDetailsService memberDetailsService;
	
	public JwtAuthenticationFilter(JwtProvider jwtProvider, MemberDetailsService memberDetailsService) {
		this.jwtProvider = jwtProvider;
		this.memberDetailsService = memberDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization = request.getHeader("Authorization");
		if (!Objects.isNull(authorization)) {
			String atk = authorization.substring(7);
			try {
				Subject subject = jwtProvider.getSubject(atk);
				String requestURI = request.getRequestURI();
				if ((subject.getType().equals("RTK") && !requestURI.equals("/enjoytrip/member/reissue")) ||
						jwtProvider.isBlackList(atk)) {
					throw new Exception();
				}
				UserDetails userDetails = memberDetailsService.loadUserByUsername(subject.getMemberId());
				Authentication token = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(token);
			} catch (Exception e) {
				e.printStackTrace();
				throw new JwtException("유효하지 않은 토큰입니다.");
			}
		}
		filterChain.doFilter(request, response);
	}
	
	
}
