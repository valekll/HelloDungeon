package HelloDungeon.Game;

/**
 * An environment with effects that interfere with normal battle operations.
 * @author Valek
 * @version 1.0
 * @since 1.0
 */
public class Environment {
	private EnvironmentType name;
	private double[] caRestrictions; //combat ability restrictions
	private Effect[] effects;
	
	/**
	 * Constructs an environment of a given type.
	 * @param n the given type
	 */
	public Environment(EnvironmentType n) {
		name = n;
	} //Environment()
	/**
	 * Constructs an environment of a given type. Then assigns it effects, and restrictions.
	 * @param n the given type
	 * @param r the restrictions
	 * @param e the effects
	 */
	public Environment(EnvironmentType n, double[] r, Effect[] e) {
		this(n);
		caRestrictions = r;
		effects = e;
	} //Environment()
	
	/**
	 * Gets the Effects of the ability.
	 * @return the effects
	 */
	public Effect[] getEffects() {
		return effects;
	} //getEffects()
	
	/**
	 * Gets the combat ability restrictions.
	 * @return
	 */
	public double[] getCaRestrictions() {
		return caRestrictions;
	} //getCaRestrictions()
	
	/**
	 * Overrides toString and just provides a name.
	 */
	public String toString() {
		return name.toString();
	} //toString()
} //Environment
