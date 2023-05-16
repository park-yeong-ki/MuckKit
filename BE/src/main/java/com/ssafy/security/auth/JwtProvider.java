package com.ssafy.security.auth;

import java.time.Duration;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.redis.RedisDao;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	private final ObjectMapper objectMapper;
	private final RedisDao redisDao;
	
	public JwtProvider(ObjectMapper objectMapper, RedisDao redisDao) {
		this.objectMapper = objectMapper;
		this.redisDao = redisDao;
	}

	@Value("${spring.jwt.secret-key}")
	private String secretKey;
	
	@Value("${spring.jwt.live-atk}")
    private Long atkLive;
	
	@Value("${spring.jwt.live-rtk}")
	private Long rtkLive;
	
	 @PostConstruct
	 protected void init() {
		 secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	 }
	 
	 public TokenResponse createTokensByLogin(MemberDto mDto) throws JsonProcessingException {
		 Subject atkSubject = Subject.atk(mDto.getMemberId(), mDto.getMemberName());
		 String atk = createToken(atkSubject, atkLive);
		 
		 Subject rtkSubject = Subject.rtk(mDto.getMemberId(), mDto.getMemberName());
		 String rtk = createToken(rtkSubject, rtkLive);
		 redisDao.setValues(mDto.getMemberId(), rtk, Duration.ofMillis(rtkLive));
		 
		 return new TokenResponse(atk, rtk);
	 }
	 
	 private String createToken(Subject subject, Long tokenLive) throws JsonProcessingException {
		 String subjectStr = objectMapper.writeValueAsString(subject);
		 Claims claims = Jwts.claims().setSubject(subjectStr);
		 Date date = new Date();
		 return Jwts.builder()
				 .setClaims(claims)
				 .setIssuedAt(date)
				 .setExpiration(new Date(date.getTime() + tokenLive))
				 .signWith(SignatureAlgorithm.HS256, secretKey)
				 .compact();
	 }
	 
	 public Subject getSubject(String token) throws JsonMappingException, JsonProcessingException {
		 String subjectStr = Jwts.parser()
				 .setSigningKey(secretKey)
				 .parseClaimsJws(token)
				 .getBody()
				 .getSubject();
		 return objectMapper.readValue(subjectStr, Subject.class);
	 }
	 
	 public TokenResponse reissueAtk(MemberDto mDto) throws Exception {
		 String rtkInRedis = redisDao.getValues(mDto.getMemberId());
		 if (Objects.isNull(rtkInRedis)) {
			throw new Exception();
		}
		 
		Subject atkSubject = Subject.atk(mDto.getMemberId(), mDto.getMemberName());
		String atk = createToken(atkSubject, atkLive);
		return new TokenResponse(atk, null);
	 }
	 
	 public void deleteRtk(MemberDto member) {
	        redisDao.deleteValues(member.getMemberId());
	 }
	 
	 public void setBlackListAtk(String bearerAtk) {
	        String atk = bearerAtk.substring(7);
	        long expiration = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(atk).getBody().getExpiration().getTime();
	        long now = Calendar.getInstance().getTime().getTime();
	        
	        redisDao.setValues(atk, "logout", Duration.ofMillis(expiration - now));
	 }
	 
	 public boolean isBlackList(String atk) {
	        return StringUtils.hasText(redisDao.getValues(atk));
	    }
}
