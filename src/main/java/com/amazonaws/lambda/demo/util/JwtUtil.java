package com.amazonaws.lambda.demo.util;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.lambda.demo.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	public static SecretKey generalKey() {
		String stringKey = Constant.JWT_SECRET;
		byte[] encodeKey = Base64.getDecoder().decode(stringKey);
		SecretKey key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
		return key;
	}

	public static String createJwt(String id, String subject, long ttlMillis) throws Exception {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey key = generalKey();
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).signWith(signatureAlgorithm,
				key);
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		return builder.compact();
	}

	public static Claims parseJwt(String jwt) throws Exception {
		SecretKey key = generalKey();
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
		return claims;
	}

	public static String generalSubject(User user) {
		JSONObject json = new JSONObject();
		json.put("userId", user.getUserId());
		json.put("userName", user.getUserName());
		return json.toString();
	}
}
