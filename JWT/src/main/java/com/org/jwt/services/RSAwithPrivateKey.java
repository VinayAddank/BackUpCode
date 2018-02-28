package com.org.jwt.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

import com.nimbusds.jose.JOSEException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class RSAwithPrivateKey {

	public static void main(String[] args) throws NoSuchAlgorithmException, JOSEException, KeyStoreException,
			CertificateException, IOException, UnrecoverableKeyException {

		/*ClassPathResource resource = new ClassPathResource("truststore.jks");
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		keystore.load(resource.getInputStream(), "manage".toCharArray());*/

	
		
        /*Enumeration enumeration = keystore.aliases();
        while(enumeration.hasMoreElements()) {
            String alias = (String)enumeration.nextElement();
            System.out.println("alias name: " + alias);
            Certificate certificate = keystore.getCertificate(alias);
            System.out.println(certificate.toString());

        }*/
		
		
		
		
    	/*Key key = keystore.getKey("kell", "manage".toCharArray());
		Certificate cert = keystore.getCertificate("kell");
		//PublicKey publicKey = cert.getPublicKey();
		Map<String, Object> claims = new HashMap<>();
		claims.put("user", "cope");
		Calendar expires = Calendar.getInstance();
		expires.roll(Calendar.HOUR, 2);
		String s = Jwts.builder().setClaims(claims).setIssuedAt(new Date()).setExpiration(expires.getTime())
				.signWith(SignatureAlgorithm.RS256, key).compact();
		System.out.println(s);*/
		
		
		String Password = "APRTA@123";
		
		
		KeyStore keystore = KeyStore.getInstance("jks");
		InputStream input = new FileInputStream("oupt.jks");
		try {
			char[] password=Password.toCharArray();
			keystore.load(input, password);
		} catch (IOException e) {
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (CertificateException e1) {
			e1.printStackTrace();
		} 
		Enumeration e = keystore.aliases();
		String alias = "";
		if(e!=null)
		{
			while (e.hasMoreElements())
			{
				String  n = (String)e.nextElement();
				if (keystore.isKeyEntry(n))
				{
					alias = n;
				}
			}
		}
		PrivateKey privateKey = null;
		try {
			privateKey = (PrivateKey) keystore.getKey(alias, Password.toCharArray());
		} catch (UnrecoverableKeyException | NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		
		System.out.println(privateKey);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
