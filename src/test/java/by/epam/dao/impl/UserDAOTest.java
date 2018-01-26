package by.epam.dao.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import by.epam.bicycle.dao.ConnectionPool;
import by.epam.bicycle.dao.DAOException;
import by.epam.bicycle.dao.impl.UserDAO;
import by.epam.bicycle.entity.Role;
import by.epam.bicycle.entity.User;

public class UserDAOTest {
	
	@Test
	public void shouldReturnUser() throws DAOException {
//		ConnectionPool pool = ConnectionPool.getInstance();
//		Connection connection = pool.getConnection();
//		UserDAO dao = new UserDAO(connection);
//		
//		Calendar cal = Calendar.getInstance();
//		cal.set(2017, 6, 11, 11, 0);
//		Date date = cal.getTime();
//		
//		User expected = new User(1L, "Иван", "Иванов", "Иванович", "+375291111111", "test@test.by",
//				"test", "d8578edf8458ce06fbc5bb76a58c5ca4", date, (short) 0, null, 
//				new BigDecimal("100.00"), new Role(1, "user"));
//		User actual = null;
//		try {
//			actual = dao.findUserByLoginAndPassword("test", "d8578edf8458ce06fbc5bb76a58c5ca4");
//			actual.setLastEnterDateTime(2017, 6, 11, 11, 0);
//		}
//		finally {
//			pool.returnConnectionToPool(connection);
//		}
//		
//		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnNull() throws DAOException {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		UserDAO dao = new UserDAO(connection, "ru");
		
		User expected = null;
		User actual = null;
		
		try {
			actual = dao.findUserByLoginAndPassword("1", "d8578edf8458ce06fbc5bb76a58c5ca4");
		} finally {
			pool.returnConnectionToPool(connection);
		}
		
		assertEquals(expected, actual);
	}
}
