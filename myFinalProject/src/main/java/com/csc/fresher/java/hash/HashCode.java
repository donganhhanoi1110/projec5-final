package com.csc.fresher.java.hash;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashCode {
	public String getHashPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		System.out.println(hashedPassword);
		return hashedPassword;
	}

}
