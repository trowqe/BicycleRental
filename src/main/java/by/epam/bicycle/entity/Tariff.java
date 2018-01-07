package by.epam.bicycle.entity;

import java.math.BigDecimal;

/**
 * 
 * It's an entity class that contains information about payment tariff of rent,
 * that depends on bicycle type and duration of rent.
 * 
 * @param rentalTime	the duration (in hours) of rent
 * @param price			the price of tariff
 * @param bicycleType	the type of bicycle
 *   
 * @author 				khatkovskaya
 * 
 */

public class Tariff extends Entity {
	private static final long serialVersionUID = 1L;
	
	public static final String TARIFF_ID_DB_FIELD = "id";
	public static final String RENTAL_TIME_DB_FIELD = "rental_time";
	public static final String PRICE_DB_FIELD = "price";
	public static final String BICYCLE_TYPE_ID_DB_FIELD = "bicycle_type_id";
	public static final String DESCRIPTION_FIELD = "description";
	public static final String TABLE_NAME = "tariffs";
	
	private float rentalTime;
	private BigDecimal price;
	private BicycleType bicycleType;
	private String description;
	
	public Tariff() {
	}
	
	public Tariff(long id) {
		super(id);
	}
	
	public Tariff(long id, BicycleType bicycleType, float rentalTime, BigDecimal price, String description) {
		super(id);
		this.bicycleType = bicycleType;
		this.price = price;
		this.rentalTime = rentalTime;
		this.description = description;
	}
	
	public Tariff(long id, long bicycleTypeId, String bicycleTypeName, float rentalTime, BigDecimal price, String description) {
		super(id);
		this.bicycleType = new BicycleType(bicycleTypeId, bicycleTypeName);
		this.price = price;
		this.rentalTime = rentalTime;
		this.description = description;
	}

	public float getRentalTime() {
		return rentalTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BicycleType getBicycleType() {
		return bicycleType;
	}

	public void setRentalTime(float rentalTime) {
		this.rentalTime = rentalTime;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setBicycleType(BicycleType bicycleType) {
		this.bicycleType = bicycleType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		Tariff other = (Tariff) obj;
		if (bicycleType == null) {
			if (other.bicycleType != null)
				return false;
		} else if (!bicycleType.equals(other.bicycleType))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (Float.floatToIntBits(rentalTime) != Float.floatToIntBits(other.rentalTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tariff [id=" + getId() + ", rentalTime=" + rentalTime + ", price=" + price + ", bicycleType=" + bicycleType
				+ ", description=" + description + "]";
	}
	
	
}
