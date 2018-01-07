package by.epam.bicycle.entity;

/**
 * 
 * It's an entity class that contains information about bicycle type.
 * @param name		the bicycle type name
 *   
 * @author 			khatkovskaya
 * 
 */

public class BicycleType extends Entity {
	private static final long serialVersionUID = 1L;
	public static final String BICYCLE_TYPE_ID_DB_FIELD = "bicycle_type_id";
	public static final String NAME_DB_FIELD = "name";
	
	private String name;
	
	public BicycleType() {
	}
	
	public BicycleType(long id, String name) {
		super(id);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		BicycleType other = (BicycleType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BicycleType [id=" + getId() + ", name=" + name + "]";
	}	
	
	
}
