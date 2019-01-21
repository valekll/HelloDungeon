package HelloDungeon.Game;

import java.util.ArrayList;

/**
 * A Place is a region in which Things can be placed and moved around within.
 * @author Valek
 * @version 1.0
 * @since 1.0
 */
public class Place extends Thing implements Moveable{
	private ArrayList<ArrayList<Thing>> grid;
	
	/**
	 * Constructs an empty place with size (0, 0)
	 */
	public Place() {
		//super();
		//call Things in grid via grid.get(Y-Axis-Point).get(X-Axis-Point)
		this.grid = new ArrayList<ArrayList<Thing>>(0);
	} //Place()
	
	/**
	 * Constructs an empty place with specified size
	 * @param height specifies the size of the y-axis
	 * @param width specifies the size of the x-axis
	 */
	public Place(int height, int width) {
		this();
		this.setSize(height, width);
	} //Place()
	
	///**
	// * Constructs an empty place with specified size at specified location
	// * @param x specifies the x-coordinate location
	// * @param y specifies the y-coordinate location
	// * @param height specifies the size of the y-axis
	// * @param width specifies the size of the x-axis
	// */
	//public Place(int x, int y, int height, int width) {
		//super(x, y);
		//this.setSize(height, width);
	//}
	
	/**
	 * Sets the size of the initial grid and fills it will null values
	 * @param height specifies the size of the y-axis
	 * @param width specifies the size of the x-axis
	 * @throws IndexOutOfBoundsException if the x or y coordinate are less than zero
	 */
	private void setSize(int height, int width) throws IndexOutOfBoundsException {
		//throw exceptions
		if(height < 0 || width < 0) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		} //if
		//iterate through height
		for(int i = 0; i < height; i++) {
			//create an ArrayList of ArrayLists of Things for the height
			grid.add(new ArrayList<Thing>(0));
			//iterate through width
			for(int j = 0; j < width; j++) {
				//add null elements in every position for the width in current ArrayList of Things
				grid.get(i).add(null);
			} //for
		} //for
	} //setSize()
	
	/**
	 * Puts a thing in the specified location in the grid
	 * @param x specifies the x-coordinate location
	 * @param y specifies the y-coordinate location
	 * @param t the thing being put in the grid
	 * @throws IndexOutOfBoundsException if the x or y coordinate are out of range relative
	 * to the grid size
	 * @throws NullPointerException if elem is null
	 */
	public void put(int x, int y, Thing t) throws IndexOutOfBoundsException, NullPointerException {
		//throw exceptions
		if(y > (grid.size() - 1) || y < 0) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		} //if
		if(x > (grid.get(y).size() - 1) || x < 0) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		} //if
		if(t == null) {
			throw new NullPointerException("Param cannot be null");
		} //if
		//put thing into place
		grid.get(y).set(x, t);
	} //put()
	
	/**
	 * Removes a thing in the specified location from the grid by setting it to <code>null</code>
	 * @param x specifies the x-coordinate location
	 * @param y specifies the y-coordinate location
	 * @throws IndexOutOfBoundsException if the x or y coordinate are out of range relative
	 * to the grid size
	 */
	public void remove(int x, int y) throws IndexOutOfBoundsException {
		//throw exceptions
		if(y > (grid.size() - 1) || y < 0) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		} //if
		if(x > (grid.get(y).size() - 1) || x < 0) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		} //if
		//put thing into place
		grid.get(y).set(x, null);
	} //remove()

	/**
	 * {@inheritDoc}
	 */
	public void moveUp(int units) {
		for(int i = 0; i < units; i++) {
			moveUp();
		} //for
	} //moveUp

	/**
	 * {@inheritDoc}
	 */
	public void moveDown(int units) {
		for(int i = 0; i < units; i++) {
			moveDown();
		} //for
	} //moveDown()

	/**
	 * {@inheritDoc}
	 */
	public void moveRight(int units) {
		for(int i = 0; i < units; i++) {
			moveRight();
		} //for
	} //moveRight()

	/**
	 * {@inheritDoc}
	 */
	public void moveLeft(int units) {
		for(int i = 0; i < units; i++) {
			moveLeft();
		} //for
	} //moveLeft()

	/**
	 * {@inheritDoc}
	 */
	public void moveUp() {
		// TODO Auto-generated method stub
		
	} //moveUp()

	/**
	 * {@inheritDoc}
	 */
	public void moveDown() {
		// TODO Auto-generated method stub
		
	} //moveDown()
	
	/**
	 * {@inheritDoc}
	 */
	public void moveRight() {
		// TODO Auto-generated method stub
		
	} //moveRight()
	
	/**
	 * {@inheritDoc}
	 */
	public void moveLeft() {
		// TODO Auto-generated method stub
		
	} //moveLeft()
} //Place
