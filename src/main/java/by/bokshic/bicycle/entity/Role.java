package by.bokshic.bicycle.entity;

/**
 * 
 * It's an entity class that contains information about role of user in system
 * @param name		the name of role
 *    
 * @author 			khatkovskaya
 * 
 */

public class Role extends Entity {
	private static final long serialVersionUID = 1L;
	public static final String ROLE_ID_DB_FIELD = "id";
	public static final String NAME_DB_FIELD = "rolename";
	public static final String TABLE_NAME = "roles";
	
	private static final String ADMIN_ROLE_NAME = "admin";
	private static final String USER_ROLE_NAME = "user";
	
	private String name;
	
	public Role() {
	}
	
	public Role(long id) {
		super(id);
	}

	
	public Role(long id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isAdmin() {
		return name.equalsIgnoreCase(ADMIN_ROLE_NAME);
	}
	
	public boolean isUser() {
		return name.equalsIgnoreCase(USER_ROLE_NAME);
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
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + getId() + ", name=" + name + "]";
	}	
	
	

}
