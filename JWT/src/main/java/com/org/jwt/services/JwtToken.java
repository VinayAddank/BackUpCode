package com.org.jwt.services;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;

public class JwtToken {

	public static void main(String[] args) {

		createJWT("1", "KELL", "Administrator", 12000);

	}

	// Sample method to construct a JWT
	private static String createJWT(String id, String issuer, String subject, long ttlMillis) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

	
		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(
				"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");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		// if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

}
