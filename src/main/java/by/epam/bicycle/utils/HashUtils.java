package by.epam.bicycle.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HashUtils {
	private static Logger logger = LogManager.getLogger(HashUtils.class);
	
	public static String getHashMD5(String str) {
		try {
			byte[] bytesOfMessage = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < thedigest.length; ++i) {
				sb.append(Integer.toHexString((thedigest[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
		}
		return "";
	}
}
