package by.epam.config;

import static org.junit.Assert.*;

import org.junit.Test;

import by.epam.bicycle.config.ConfigurationManager;

public class ConfigurationManagerTest {

	@Test
	public void shouldReturnIndexJSPPath() {
		String expected = "/index.jsp";
		String actual  = ConfigurationManager.getProperty("path.page.index");
		assertEquals(expected, actual);
	}

}
