package by.epam.bicycle.entity;

/**
 * 
 * It's an entity class that contains information about rental point.
 * 
 * @param address		the address 
 * @param phone			the phone 
 * @param workingHours	the information about working hours 
 *   
 * @author 				khatkovskaya
 * 
 */

public class RentalPoint extends Entity {
	private static final long serialVersionUID = 1L;
	public static final String RENTAL_POINT_ID_DB_FIELD = "rental_point_id";
	public static final String ADDRESS_DB_FIELD = "address";
	public static final String PHONE_DB_FIELD = "phone";
	public static final String WORKING_HOURS_DB_FIELD = "working_hours";

	
	private String address;
	private String phone;
	private String workingHours;
	
	public RentalPoint() {
	}

	public RentalPoint(long id, String address, String phone, String workingHours) {
		super();
		this.address = address;
		this.phone = phone;
		this.workingHours = workingHours;
	}

	public String getAddress() {
		return address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getWorkingHours() {
		return workingHours;
	}
		
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
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
		RentalPoint other = (RentalPoint) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (workingHours == null) {
			if (other.workingHours != null)
				return false;
		} else if (!workingHours.equals(other.workingHours))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RentalPoint [id=" + getId() + ", address=" + address + ", phone=" + phone + ", workingHours=" + workingHours + "]";
	}
	
	
	
}
