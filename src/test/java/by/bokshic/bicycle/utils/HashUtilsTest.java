package by.bokshic.bicycle.utils;

import org.junit.Assert;
import org.junit.Test;

public class HashUtilsTest {
	 @Test 
	 public void shouldReturnHashCodeMD5() {
		String expected = "098f6bcd4621d373cade4e832627b4f6"; 
		String actual = HashUtils.getHashMD5("test");
		Assert.assertEquals(expected, actual);
	 }
}
