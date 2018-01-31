package by.epam.bicycle.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import by.epam.bicycle.config.ConfigurationManager;

/**
 * An entity class that contains information about user.
 *   
 * @author 						khatkovskaya
 * 
 */

public class User extends Entity {
	private static final long serialVersionUID = 1L;
	
	public static final String USER_ID_DB_FIELD = "id";
	public static final String NAME_DB_FIELD = "name";
	public static final String SURNAME_DB_FIELD = "surname";
	public static final String PATRONYMIC_DB_FIELD = "patronymic";
	public static final String MOBILE_PHONE_DB_FIELD = "mobile_phone";
	public static final String EMAIL_DB_FIELD = "email";
	public static final String LOGIN_DB_FIELD = "login";
	public static final String PASSWORD_DB_FIELD = "password";
	public static final String LAST_ENTER_DATETIME_DB_FIELD = "last_enter_datetime";
	public static final String STATUS_DB_FIELD = "status";
	public static final String BLOCK_DATETIME_DB_FIELD = "block_datetime";
	public static final String CREATE_DATETIME_DB_FIELD = "create_datetime";
	public static final String BALANCE_DB_FIELD = "balance";
	public static final String ROLE_ID_DB_FIELD = "role_id";
	public static final String TABLE_NAME = "users";
	
	/** name of user */
	private String name;
	/** surname of user */
	private String surname;
	/** patronymic of user */
	private String patronymic;
	/** mobile phone of user */
	private String mobilePhone;
	/** email of user */
	private String email;
	/** login of user */
	private String login;
	/** password(hash code) of user (MD5) */
	private String password;
	/** date and time of last entrance in system */
	private Date lastEnterDateTime;
	/** status of user (0 - is active, 1 - is blocked) */
	private short status;
	/** date and time of last blocking in system */
	private Date blockDateTime;
	/** password(hash code) of user (MD5) */
	private BigDecimal balance;
	/** role in system (User or Admin) */
	private Role role;	
	private Date createDateTime;
	
	
	public User() {
	}
	
	public User(long id) {
		super(id);
	}
	
	public User(long id, String name, String surname, String patronymic, String mobilePhone, String email, String login,
			String password, Date lastEnterDateTime, short status, Date blockDateTime, BigDecimal balance, Role role) {
		super(id);
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.login = login;
		this.password = password;
		this.lastEnterDateTime = lastEnterDateTime;
		this.status = status;
		this.blockDateTime = blockDateTime;
		this.balance = balance;
		this.role = role;
	}

	public User(String name, String surname, String patronymic, String mobilePhone, String email, String login, String password, Role role) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getPatronymic() {
		return patronymic;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Date getLastEnterDateTime() {
		return lastEnterDateTime;
	}
	
	public short getStatus() {
		return status;
	}
	
	public Date getBlockDateTime() {
		return blockDateTime;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public Role getRole() {
		return role;
	}
	
	public Date getCreateDateTime() {
		return createDateTime;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setLastEnterDateTime(Date lastEnterDateTime) {
		this.lastEnterDateTime = lastEnterDateTime;
	}
	
	public void setLastEnterDateTime(int year, int month, int day, int hours, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hours, minutes);
		Date date = cal.getTime();
		this.lastEnterDateTime = date;
	}
	
	public void setStatus(short status) {
		this.status = status;
	}
	
	public void setBlockDateTime(Date blockDateTime) {
		this.blockDateTime = blockDateTime;
	}
	
	public void setBlockDateTime(int year, int month, int day, int hours, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hours, minutes);
		Date date = cal.getTime();
		this.blockDateTime = date;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	
	public void setCreateDateTime(int year, int month, int day, int hours, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hours, minutes);
		Date date = cal.getTime();
		this.createDateTime = date;
	}
	
	public boolean isBlocked() {
		short blockedStatus = Short.parseShort(ConfigurationManager.getProperty(ConfigurationManager.USER_STATUS_BLOCKED));
		return (status == blockedStatus);
	}
	
	public boolean isActive() {
		short activeStatus = Short.parseShort(ConfigurationManager.getProperty(ConfigurationManager.USER_STATUS_ACTIVE));
		return (status == activeStatus);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (blockDateTime == null) {
			if (other.blockDateTime != null)
				return false;
		} else if (!blockDateTime.equals(other.blockDateTime))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (lastEnterDateTime == null) {
			if (other.lastEnterDateTime != null)
				return false;
		} else if (!lastEnterDateTime.equals(other.lastEnterDateTime))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (mobilePhone == null) {
			if (other.mobilePhone != null)
				return false;
		} else if (!mobilePhone.equals(other.mobilePhone))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (patronymic == null) {
			if (other.patronymic != null)
				return false;
		} else if (!patronymic.equals(other.patronymic))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (status != other.status)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + getId() + ", name=" + name + ", surname=" + surname + ", patronymic=" + patronymic + ", mobilePhone="
				+ mobilePhone + ", email=" + email + ", login=" + login + ", password=" + password
				+ ", lastEnterDateTime=" + lastEnterDateTime + ", status=" + status + ", blockDateTime=" + blockDateTime
				+ ", balance=" + balance + ", role=" + role + "]";
	}
	
	
}
