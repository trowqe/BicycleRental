package by.epam.bicycle.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * It's an entity class that contains information about rent.
 * 
 * @param user		user that create the rent
 * @param bicycle	bicycle that borrowed for rent
 *  
 * @author 			khatkovskaya
 * 
 */

public class Rent extends Entity {
	private static final long serialVersionUID = 1L;

	public static final String RENT_ID_DB_FIELD = "id";
	public static final String CREATE_DATETIME_DB_FIELD = "datetime_create";
	public static final String FINISH_DATETIME_DB_FIELD = "datetime_finish";
	public static final String AMOUNT_DB_FIELD = "amount";
	public static final String USER_ID_DB_FIELD = "user_id";
	public static final String BICYCLE_ID_DB_FIELD = "bicycle_id";
	public static final String TARIFF_ID_DB_FIELD = "tariff_id";
	public static final String TABLE_NAME = "rents";
	
	private User user;
	private Bicycle bicycle;
	private Tariff tariff;
	private Date createDateTime;
	private Date finishDateTime;
	private BigDecimal amount;
	
	public Rent() {
	}
	
	public Rent(User user, Bicycle bicycle, Tariff tariff) {
		this.user = user;
		this.bicycle = bicycle;
		this.tariff = tariff;
	}
	
	public Rent(long id, User user, Bicycle bicycle, Tariff tariff, Date createDateTime, Date finishDateTime, BigDecimal amount) {
		super(id);
		this.user = user;
		this.bicycle = bicycle;
		this.tariff = tariff;
		this.createDateTime = createDateTime;
		this.finishDateTime = finishDateTime;
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}
	
	public Bicycle getBicycle() {
		return bicycle;
	}
	
	public Date getCreateDateTime() {
		return createDateTime;
	}
	
	public Date getFinishDateTime() {
		return finishDateTime;
	}
	
	public Date getPlanFinishDateTime() {
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(createDateTime);
		float tariffTime = tariff.getRentalTime();
		int time = (int) (60 * tariffTime);
		cal.add(Calendar.MINUTE, time);		
		return cal.getTime();
	}
			
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
	}
	
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	
	public void setFinishDateTime(Date finishDateTime) {
		this.finishDateTime = finishDateTime;
	}
	
	public void setCreateDateTime(int year, int month, int day, int hours, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hours, minutes);
		Date date = cal.getTime();
		this.createDateTime = date;
	}
	
	public void setFinishDateTime(int year, int month, int day, int hours, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hours, minutes);
		Date date = cal.getTime();
		this.finishDateTime = date;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Tariff getTariff() {
		return tariff;
	}

	public void setTariff(Tariff tariff) {
		this.tariff = tariff;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rent other = (Rent) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (bicycle == null) {
			if (other.bicycle != null)
				return false;
		} else if (!bicycle.equals(other.bicycle))
			return false;
		if (createDateTime == null) {
			if (other.createDateTime != null)
				return false;
		} else if (!createDateTime.equals(other.createDateTime))
			return false;
		if (finishDateTime == null) {
			if (other.finishDateTime != null)
				return false;
		} else if (!finishDateTime.equals(other.finishDateTime))
			return false;
		if (tariff == null) {
			if (other.tariff != null)
				return false;
		} else if (!tariff.equals(other.tariff))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rent [id=" + getId() + ", user=" + user + ", bicycle=" + bicycle + ", tariff=" + tariff + ", createDateTime="
				+ createDateTime + ", finishDateTime=" + finishDateTime + ", amount=" + amount + "]";
	}
	
	
	
}
