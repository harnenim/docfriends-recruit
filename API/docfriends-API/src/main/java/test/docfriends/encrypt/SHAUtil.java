package test.docfriends.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

public class SHAUtil {
	
	public static byte[] encode(String str, String salt1, String salt2, String type) {
		try {
			MessageDigest md = MessageDigest.getInstance(type);
			return md.digest((salt1 + str + salt2).getBytes("utf-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String encodeBase64(String str, String salt1, String salt2, String type) {
		return Base64.encodeBase64String(encode(str, salt1, salt2, type));
	}
	public static byte[] encode(String str, String salt) {
		return encode(str, salt, salt, "SHA-512");
	}
	public static String encodeBase64(String str, String salt) {
		return encodeBase64(str, salt, salt, "SHA-512");
	}

	public static String newSalt() {
		byte[] saltBytes = new byte[64];
		new SecureRandom().nextBytes(saltBytes);
		return Base64.encodeBase64String(saltBytes);
	}

	public static void main(String[] args) {
		System.out.println(SHAUtil.encodeBase64("asdf", "asdf"));

	}

}
