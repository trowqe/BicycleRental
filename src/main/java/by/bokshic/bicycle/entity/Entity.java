package by.bokshic.bicycle.entity;

import java.io.Serializable;

/**
 * 
 * It's the root class for all entity-classes. 
 * @param id	the unique identifier of an entity
 * 
 * @author 		khatkovskaya
 * 
 */

public abstract class Entity implements Serializable, Identifiable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	public Entity() {
	}

	public Entity(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}
