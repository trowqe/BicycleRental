package by.bokshic.bicycle.dao.creator.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.bokshic.bicycle.entity.Role;
import by.bokshic.bicycle.entity.User;
import by.bokshic.bicycle.entity.Role;
import by.bokshic.bicycle.entity.User;

public class UserCreator extends AbstractCreator<User> {
	public UserCreator() {
	}
	
	public UserCreator(String language) {
		super(language);
	}

	public User execute(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getLong(User.USER_ID_DB_FIELD));
		user.setName(resultSet.getString(User.NAME_DB_FIELD));
		user.setSurname(resultSet.getString(User.SURNAME_DB_FIELD));
		user.setPatronymic(resultSet.getString(User.PATRONYMIC_DB_FIELD));
		user.setLogin(resultSet.getString(User.LOGIN_DB_FIELD));
		user.setPassword(resultSet.getString(User.PASSWORD_DB_FIELD));
		user.setEmail(resultSet.getString(User.EMAIL_DB_FIELD));
		user.setMobilePhone(resultSet.getString(User.MOBILE_PHONE_DB_FIELD));
		user.setStatus(resultSet.getShort(User.STATUS_DB_FIELD));
		user.setBlockDateTime(resultSet.getDate(User.BLOCK_DATETIME_DB_FIELD));
		user.setLastEnterDateTime(resultSet.getDate(User.LAST_ENTER_DATETIME_DB_FIELD));
		user.setBalance(resultSet.getBigDecimal(User.BALANCE_DB_FIELD));
		user.setCreateDateTime(resultSet.getDate(User.CREATE_DATETIME_DB_FIELD));
		
		Role role = new Role();
		role.setId(resultSet.getLong(User.ROLE_ID_DB_FIELD));
		role.setName(resultSet.getString(Role.NAME_DB_FIELD));
		user.setRole(role);
		
		return user;
	}
	
	public User execute(long id, ResultSet resultSet) throws SQLException {
		User user = execute(resultSet);
		user.setId(id);
		return user;
	}

}
