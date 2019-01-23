package HelloDungeon.Game;

/**
 * A Character the user can add to his party for use in battle.
 * @author Valek
 * @version 1.0
 * @since 1.0
 */



/* TODO:
 *  Movement, Actions(?) [abilities that can be used without a menu], Stronger Level Ups/Luck Based,
 */



public class PlayableChar extends Character {
	public int[] expReq;
	private int exp;
	
	/**
	 * Constructs a Character of the given name, level, race type, and class type.
	 * @param n the name
	 * @param lvl the level
	 * @param rt the race type
	 */
	public PlayableChar(String n, int lvl, RaceType rt, ClassType c) {
		super(n, lvl, rt, c);
	} //PlayableChar()
	
	/**
	 * Constructs a Character of the given name, level, race type, class type, and stats. 
	 * Will use custom scalings (stats from 9-17) if provided. 
	 * @param n the name
	 * @param lvl the level
	 * @param rt the race type
	 * @param s the given stats of size 9 or 18
	 */
	public PlayableChar(String n, int lvl, RaceType rt, ClassType c, double[] s) {
		super(n, lvl, rt, c, s);
	} //PlayableChar()
	
	/**
	 * Gets the Character's current exp.
	 * @return
	 */
	public int getExp() {
		return exp;
	} //getExp()

	/**
	 * Adds the set amount of exp to the Character. If it would level up the Character,
	 * it does so
	 * @param newExp
	 */
	public void addExp(int newExp) {
		
	} //addExp()
} //PlayableChar
