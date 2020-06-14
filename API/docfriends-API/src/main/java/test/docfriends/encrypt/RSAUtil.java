package test.docfriends.encrypt;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

// 키생성 ----------> x : 랜덤생성
// 암호화용 생성 ---> byte[],암호화용
// 복호화용 생성 ---> byte[],복호화용
public class RSAUtil {
	public enum RSAType {ENCRYPT, DECRYPT};
	
	private final String ALGORITHM = "RSA";
	private final int KEY_SIZE = 1024;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private Cipher cipher= Cipher.getInstance("RSA");

	public String getPrivateKeyBytesBase64() {
		return Base64.encodeBase64String(privateKey.getEncoded());
	}
	
	public byte[] getPublicKeyBytes(){
		return publicKey.getEncoded();
	}
	
	public String getPublicKeyBytesBase64(){
		return Base64.encodeBase64String(publicKey.getEncoded());
	}
	
	public byte[] getPrivateKeyBytes(){
		return privateKey.getEncoded();
	}

	// 둘중의 값을 선택하도록 하기 
	public RSAUtil() throws Exception{
		// key Random 생성
		generateKeyPair();
	}
	public RSAUtil(byte[] keyBytes, RSAType rsaType) throws Exception{
		if(rsaType==RSAType.DECRYPT){        // 복호화
			generatePrivate(keyBytes);
		}else if(rsaType==RSAType.ENCRYPT){  // 암호화
			generatePublic(keyBytes);
		}
	}
	public RSAUtil(byte[] privateKeyBytes, byte[] publicKeyBytes) throws Exception{
		generatePrivate(privateKeyBytes);
		generatePublic (publicKeyBytes );
	}
	private void generatePublic(byte[] keyBytes) throws Exception{
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
		publicKey = factory.generatePublic(keySpec);
	}
	private void generatePrivate(byte[] keyBytes) throws Exception{
		PKCS8EncodedKeySpec keySpec 
			= new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
		privateKey = factory.generatePrivate(keySpec);
	}
	
	private void generateKeyPair() throws Exception{
		KeyPairGenerator keyGene 
		= KeyPairGenerator.getInstance(ALGORITHM);
		keyGene.initialize(KEY_SIZE);
		KeyPair keyPair = keyGene.generateKeyPair();
		
		publicKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();
	}
	
	// byte[] -> byte[]
	public byte[] encrypt(byte[] plain) throws Exception{
		if(publicKey==null) return null;
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(plain);
	}
	
	// String -> byte[] -> byte[]
	public byte[] encryptString(String plainText, String encoding) throws Exception{
		byte[] bytes = plainText.getBytes(encoding);
		return encrypt(bytes);
	}
	
	// byte[] -> byte[] -> Base64
	public String encryptToBase64(byte[] plain) throws Exception {
		byte[] encrypt = encrypt(plain);
		return Base64.encodeBase64String(encrypt);
	}
	
	// String -> byte[] -> byte[] -> Base64
	public String encryptStringToBase64(String plainText,String encoding) throws Exception{
		byte[] encryptString = encryptString(plainText,encoding);
		return Base64.encodeBase64String(encryptString);
	}
	
	// byte[] -> byte[]
	public byte[] decrypt(byte[] encrypted) throws Exception{
		if(privateKey==null) return null;
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(encrypted);
	}
	
	// byte[] -> byte[] -> String
	public String decryptString(byte[] encrypted, String encoding) throws Exception{
		byte[] decrypt = decrypt(encrypted);
		return new String(decrypt,encoding);
	}
	
	// Base64 -> byte[] -> byte[]
	public byte[] decryptFromBase64(String encryptedBase64) throws Exception{
		byte[] decodeBase64 = Base64.decodeBase64(encryptedBase64);
		return decrypt(decodeBase64);
	}
	
	// Base64 -> byte[] -> byte[] -> String
	public String decryptStringFromBase64(String encryptedBase64,String encoding) throws Exception{
		byte[] decryptFromBase64 = decryptFromBase64(encryptedBase64);
		return new String(decryptFromBase64,encoding);
	}
	
	public static void main(String[] args) throws Exception {
		
		// 서버
		RSAUtil rsa = new RSAUtil(); // random
		
		String pubilcKeyBase64 = rsa.getPublicKeyBytesBase64();
		System.out.println(pubilcKeyBase64);
		
		byte[] publicKeyBytes = rsa.getPublicKeyBytes();
		//-------------------------------------
		
		RSAUtil rsa2 = new RSAUtil(publicKeyBytes,RSAType.ENCRYPT);
		String encrypt = rsa2.encryptStringToBase64("세나바보", "utf-8");
		System.out.println(encrypt);
		
		//--------------------------------------
		String decrypt = rsa.decryptStringFromBase64(encrypt, "utf-8");
		System.out.println(decrypt);
		
	}
	
}
