package by.epam.bicycle.entity;

/**
 * 
 * It's an entity class that contains information about bicycle.
 * 
 * @param model		the bicycle model
 * @param point		rental point that contains this bicycle 
 *   
 * @author 			khatkovskaya
 * 
 */

public class Bicycle extends Entity {
	private static final long serialVersionUID = 1L;
	public static final String ID_DB_FIELD = "id";
	public static final String MODEL_ID_DB_FIELD = "bicycle_model_id";
	public static final String RENTAL_POINT_ID_DB_FIELD = "rental_point_id";
	public static final String TABLE_NAME = "bicycles";
	
	private BicycleModel model;
	private RentalPoint point;

	public Bicycle() {
	}
	
	public Bicycle(long id) {
		super(id);
	}	
	
	public Bicycle(long id, BicycleModel model, RentalPoint point) {
		super(id);
		this.model = model;
		this.point = point;
	}
	
	public BicycleModel getModel() {
		return model;
	}

	public RentalPoint getPoint() {
		return point;
	}

	public void setModel(BicycleModel model) {
		this.model = model;
	}

	public void setPoint(RentalPoint point) {
		this.point = point;
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
		Bicycle other = (Bicycle) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bicycle [id=" + getId() + ", model=" + model + ", point=" + point + "]";
	}
	
	
}
