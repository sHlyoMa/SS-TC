package security;


import service.UserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaltSourceImpl  {
	
	@Autowired
	public UserService userService;
	
	public String getSaltedPassword (String userName, String password) {
		StringBuilder code = new StringBuilder();
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] bytes = (password + getSalt(userName)).getBytes();
			byte[] digest = messageDigest.digest(bytes);
			for (int i = 0; i < digest.length; ++i) {
				code.append(Integer.toHexString(0x0100 + (digest[i] & 0x00FF))
						.substring(1));
			}
		} catch (NoSuchAlgorithmException ignore) {
		}
		return code.toString();
	}
	
	public String getSalt(String userName) {
			if (userName != null && userName != "") {				
				String salt = userService.getDateByName(userName);
				return salt;
			}
			return null;
		}
	}
	
	
	


