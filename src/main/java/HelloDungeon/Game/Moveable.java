package HelloDungeon.Game;

/**
 * Provides movement functions for moveable objects
 * @author Valek
 * @version 1.0
 * @since 1.0
 */
public interface Moveable {
	/**
	 * Moves object up a unit
	 */
	void moveUp();
	
	/**
	 * Moves object up by specified amount of units
	 * @param units the amount of space by which to move
	 */
	void moveUp(int units);
	
	/**
	 * Moves object down a unit
	 */
	void moveDown();
	
	/**
	 * Moves object down by specified amount of units
	 * @param units the amount of space by which to move
	 */
	void moveDown(int units);
	
	/**
	 * Moves object right a unit
	 */
	void moveRight();
	
	/**
	 * Moves object right by specified amount of units
	 * @param units the amount of space by which to move
	 */
	void moveRight(int units);
	
	/**
	 * Moves object left a unit
	 */
	void moveLeft();
	
	/**
	 * Moves object left by specified amount of units
	 * @param units the amount of space by which to move
	 */
	void moveLeft(int units);
} //Moveable
