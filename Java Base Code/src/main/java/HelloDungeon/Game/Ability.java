package HelloDungeon.Game;

/**
 * An intangible aspect of a Character.
 * @author Valek
 * @version 1.0
 * @since 1.0
 */
public abstract class Ability {
	private String name;
	private String description;
	
	/**
	 * Constructs a basic Ability with a name and a description.
	 * @param n the name
	 * @param d the description
	 */
	public Ability(String n, String d) {
		name = n;
		description = d;
	} //Ability()
	
	/**
	 * Gets the name of the Ability
	 * @return the name
	 */
	public String getName() {
		return name;
	} //getName()

	/**
	 * Sets the name of the Ability to a specified name.
	 * @param name the specified name
	 */
	public void setName(String name) {
		this.name = name;
	} //setName()

	/**
	 * Gets the description of the Ability.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the Ability to the given description.
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Override for the toString method, sending the ability name back instead. 
	 */
	@Override
	public String toString() {
		return name;
	} //toString()
	
	/**
	 * Consumes resources and iterates ability from a source onto select targets.
	 * @return True if the ability iterates.
	 */
	public abstract boolean iterate();
} //Ability()
