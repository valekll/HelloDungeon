package HelloDungeon.Game;

/**
 * A Thing is an Object that can take up space.
 * @author Valek
 * @version 1.0
 * @since 1.0
 */
public class Thing {
	private int height;
	private int width;
	
	/**
	 * Constructor for a Thing. Sets height and width
	 * to a default value of 1.
	 */
	public Thing() {
		height = 1;
		width = 1;
	}
	/**
	 * Constructor for a Thing. Sets height and width
	 * to specified values.
	 * @param h specifies the height
	 * @param w specifies the width
	 */
	public Thing(int h, int w) {
		height = h;
		width = w;
	}
	
	/**
	 * Returns the height of the Thing
	 * @return height of the Thing
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Returns the width of the Thing
	 * @return width of the Thing
	 */
	public int getWidth() {
		return width;
	}
	
	/** 
	 * Returns the area of the Thing
	 * @return area of the Thing
	 */
	public int area() {
		return height * width;
	}
	
	/**
	 * Sets the height to the specified value
	 * @param h specifies the modified height
	 */
	public void setHeight(int h) {
		height = h;
	}
	
	/**
	 * Sets the width to the specified value
	 * @param w specifies the modified width
	 */
	public void setWidth(int w) {
		width = w;
	}
} //Thing
