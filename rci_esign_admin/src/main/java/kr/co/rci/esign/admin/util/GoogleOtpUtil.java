package kr.co.rci.esign.admin.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleOtpUtil {
	// singleton

	private final static int SECRET_SIZE			= 5;
	private final static int NUM_OF_SCRATCH_CODES	= 5;
	private final static int SCRATCH_CODE_SIZE		= 5;
	/** Login User Log **/
	protected static final Logger login_log = LoggerFactory.getLogger("LOGIN_LOG");

	/**
	 * Generating Key the Secret Key.
	 *
	 * @param userId
	 * @param hostName
	 * @return
	 */
	public static String generateSecretKey(String userId, String hostName) {
		// Allocating the buffer
		byte[] buffer = new byte[SECRET_SIZE + NUM_OF_SCRATCH_CODES * SCRATCH_CODE_SIZE];

		new Random().nextBytes(buffer);

		// Getting the key and converting it to Base32
		byte[] secretKey = Arrays.copyOf(buffer, SECRET_SIZE);

//		String encodeKey = new Base32().encode(secretKey).toString();
		String encodeKey = new Base32().encodeToString(secretKey).toString();

		return encodeKey;
	}

	/**
	 * Generating Key the Secret Key and returns the QR Barcode URL
	 *
	 * @param userId
	 * @param hostName
	 * @return Map<String,String> key="encodeKey" and "url"
	 */
	public static Map<String,String> generateSecretKeyAndGetUrl(String userId, String hostName) {
		Map<String,String> result = new HashMap<String,String>();

		String encodeKey = generateSecretKey(userId, hostName);
		String url = getQRBarcodeURL(userId, hostName, encodeKey);

		//시크릿키 와 url 넘기기
		result.put("encodeKey", encodeKey);
		result.put("url", url);

		return result;
	}

	/**
	 * Gets the secret key barcode image URL
	 *
	 * @param userId
	 * @param hostName
	 * @param encodeKey
	 * @return
	 */
	private static String getQRBarcodeURL(String userId, String hostName, String secret) {
		login_log.info("GoogleOtpUtil.getQRBarcodeURL[secret] : '"+secret+"'" );
		//String format = "https://www.google.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s%%3Fsecret%%3D%s%%26issuer%%3D%s";
		String format ="https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s%%3Fsecret%%3D%s%%26issuer%%3D%s";
		return String.format(format, userId, secret, hostName);
	}



	public static boolean checkOTPCode(String secret, long code, long t) throws NoSuchAlgorithmException, InvalidKeyException {

		byte[] decodedKey = new Base32().decode(secret);

		  // Window is used to check codes generated in the near past.
		  // You can use this value to tune how far you're willing to go.
		  int window = 5;
//		  System.out.println("code : " + code);
		  for (int i = -window; i <= window; ++i) {
		    long hash = verify_code(decodedKey, t + i);
//		    System.out.println("hash : " + hash);
		    if (hash == code) {
//		    	System.out.println("matched hash : " + hash);
		    	return true;
		    }
		  }

		  // The validation code is invalid.
		  return false;
	}

	private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
	  byte[] data = new byte[8];
	  long value = t;
	  for (int i = 8; i-- > 0; value >>>= 8) {
	    data[i] = (byte) value;
	  }

	  SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
	  Mac mac = Mac.getInstance("HmacSHA1");
	  mac.init(signKey);
	  byte[] hash = mac.doFinal(data);

	  int offset = hash[20 - 1] & 0xF;

	  // We're using a long because Java hasn't got unsigned int.
	  long truncatedHash = 0;
	  for (int i = 0; i < 4; ++i) {
	    truncatedHash <<= 8;
	    // We are dealing with signed bytes:
	    // we just keep the first byte.
	    truncatedHash |= (hash[offset + i] & 0xFF);
	  }

	  truncatedHash &= 0x7FFFFFFF;
	  truncatedHash %= 1000000;

	  return (int) truncatedHash;
	}
}
