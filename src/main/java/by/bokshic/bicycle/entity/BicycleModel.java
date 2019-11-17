package by.bokshic.bicycle.entity;

/**
 * 
 * It's an entity class that contains information about bicycle model.
 * 
 * @param bicycleType	the bicycle type
 * @param firm			the bicycle firm name
 * @param model			the bicycle model name
 *   
 * @author 				khatkovskaya
 * 
 */
	
public class BicycleModel extends Entity {
	private static final long serialVersionUID = 1L;
	
	public static final String ID_DB_FIELD = "id";
	public static final String MODEL_DB_FIELD = "model";
	public static final String FIRM_DB_FIELD = "firm";
	public static final String NOTES_DB_FIELD = "notes";
	public static final String BICYCLE_TYPE_ID_DB_FIELD = "bicycle_type_id";
	public static final String TABLE_NAME = "bicycle_models";
	
	private BicycleType type;
	private String model;
	private String firm;
	private String notes;
	
	public BicycleModel() {
	}
	
	public BicycleModel(long id) {
		setId(id);
	}
	
	public BicycleModel(long id, BicycleType type, String model, String firm, String notes) {
		super(id);
		this.type = type;
		this.model = model;
		this.firm = firm;
		this.notes = notes;
	}
	
	public BicycleModel(long id, long bicycleTypeId, String bicycleTypeName, String model, String firm, String notes) {
		super(id);
		this.type = new BicycleType(bicycleTypeId, bicycleTypeName);
		this.model = model;
		this.firm = firm;
		this.notes = notes;
	}
	
	public BicycleType getBicycleType() {
		return type;
	}
	
	public void setBicycleType(BicycleType type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
		BicycleModel other = (BicycleModel) obj;
		if (firm == null) {
			if (other.firm != null)
				return false;
		} else if (!firm.equals(other.firm))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BicycleModel [id=" + getId() + ", type=" + type + ", model=" + model + ", firm=" + firm + ", notes=" + notes + "]";
	}

	
	
}
