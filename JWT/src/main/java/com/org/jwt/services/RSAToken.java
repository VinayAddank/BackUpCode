package com.org.jwt.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.util.Enumeration;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;

public class RSAToken {

	public static void main(String[] args)
			throws NoSuchAlgorithmException, JOSEException, KeyStoreException, FileNotFoundException {

		//properties 
		//certficate password
		String Password = "APRTA@123";
		
		//Cliam key values
		String issCliamName = "RSA";
		String subCliamName = "Administrator";
		String[] audCliamNames = { "localhost:1010" };

		//jks File must be placed in classpath location
		KeyStore keystore = KeyStore.getInstance("jks");
		InputStream input = new FileInputStream("oupt.jks");
		
		try {
			char[] password = Password.toCharArray();
			keystore.load(input, password);
		} catch (IOException e) {
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (CertificateException e1) {
			e1.printStackTrace();
		}
		
		//Get the Cert Alais name
		Enumeration<?> e = keystore.aliases();
		String alias = "";
		if (e != null) {
			while (e.hasMoreElements()) {
				String n = (String) e.nextElement();
				if (keystore.isKeyEntry(n)) {
					alias = n;
				}
			}
		}

		//Based on PrivateKey it generates JWToken
		RSAPrivateKey privateKey = null;
		try {
			privateKey = (RSAPrivateKey) keystore.getKey(alias, Password.toCharArray());
		} catch (UnrecoverableKeyException | NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		
		//Key Instance or Algorithm is RSA256 and 1024 bits
		KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
		keyGenerator.initialize(1024);
		
		//Sign with RSA PrivateKey
		JWSSigner signer = new RSASSASigner(privateKey);
		
		//Set the Claims key-values
		ClaimsSet compactJws = new ClaimsSet();
		compactJws.setIssuerClaim(issCliamName);
		compactJws.setSubjectClaim(subCliamName);
		compactJws.setAudienceClaim(audCliamNames);
		
		//Build JWT token
		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), compactJws);
		signedJWT.sign(signer);
		String jwtToken = signedJWT.serialize();

		//Print the JWT Token
		System.out.println("jwtToken: " + jwtToken);
	}

}
