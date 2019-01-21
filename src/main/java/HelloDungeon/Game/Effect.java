package HelloDungeon.Game;

public class Effect extends Ability {

	/**
	 * Constructs an Effect with a name and a description.
	 * @param n the name
	 * @param d the description
	 */
	public Effect(String n, String d) {
		super(n, d);
	} //Effect

	@Override
	public boolean iterate() {
		return true;
	} //iterate
} //Effect
