package HelloDungeon.Game;

public class Party {
	private Character[] characters;
	
	/**
	 * Constructs a party with the given roster.
	 * @param c the Character roster
	 */
	public Party(Character ... c) {
		characters = c;
	} //Party()
	
	/**
	 * Gets the number of characters in the party.
	 * @return the party length
	 */
	public int length() {
		return characters.length;
	} //length()
	
	/**
	 * Gets the characters in the party.
	 * @return the characters
	 */
	public Character[] getCharacters() {
		return characters;
	} //getCharacters()
	
	/**
	 * Adds a set of characters to the end of the party.
	 * @param c the characters
	 */
	public void addToParty(Character ... c) {
		Character[] temp = characters;
		characters = new Character[c.length + temp.length];
		System.arraycopy(temp, 0, characters, 0, temp.length);
		System.arraycopy(c, 0, characters, temp.length, c.length);
	} //addToParty()
	
	/**
	 * Gets the character at the specified index.
	 * @param i the index
	 * @return the Character
	 */
	public Character get(int i) {
		return characters[i];
	} //get()
	
	/**
	 * Gets the first character in the party with a given name. Returns null if a character with the name is not found.
	 * @param name the name
	 * @return the Character
	 */
	public Character get(String name) {
		for(int i = 0; i < characters.length; i++) {
			if(characters[i].getName().toUpperCase().equals(name.toUpperCase())) {
				return characters[i];
			} //if
		} //for
		return null;
	} //get()
	
	/**
	 * Removes the character at the specified index.
	 * @param i the index
	 * @return the Character
	 */
	public boolean remove(int i) {
		Character[] temp = characters;
		characters = new Character[temp.length - 1];
		if(i == 0) { //beginning of the party
			System.arraycopy(temp, 1, characters, 0, temp.length - 1);
		} //if
		else if(i == temp.length - 1) { //end of the party
			System.arraycopy(temp, 0, characters, 0, temp.length - 1);
		} //else if
		else { //middle of the party
			int after = temp.length - (i + 1); //num elems to copy after index
			int prior = temp.length - (after + 1);  //num elems to copy prior to index
			System.arraycopy(temp, 0, characters, 0, prior);
			System.arraycopy(temp, i, characters, i, after);
		} //else
		return true;
	} //remove()
	
	/**
	 * Removes the first character in the party with a given name. Returns false if a character with the name is not found.
	 * @param name the name
	 * @return the Character
	 */
	public boolean remove(String name) {
		for(int i = 0; i < characters.length; i++) {
			if(characters[i].getName().toUpperCase().equals(name.toUpperCase())) {
				return remove(i);
			} //if
		} //for
		return false;
	} //remove()
	
	/**
	 * Swaps the Characters at two given indexes.
	 * @param i1 index 1
	 * @param i2 index 2
	 * @return if the Characters were swapped
	 */
	public boolean swap(int i1, int i2) {
		Character temp = characters[i1];
		characters[i1] = characters[i2];
		characters[i2] = temp;
		return true;
	} //swap()

	/**
	 * Checks if a character is a member of the party.
	 * @param c the Character
	 * @return party membership
	 */
	public boolean contains(Character c) {
		for(Character ch : characters) {
			if(c.equals(ch)) {
				return true;
			} //if
		} //for
		return false;
	} //contains()
	
	/**
	 * Checks if anyone in the party is alive.
	 * @return if there are living party members
	 */
	public boolean checkAlive() {
		for(int i = 0; i < characters.length; i++) {
			if(characters[i].checkAlive()) {
				return true;
			} //if
		} //for
		return false;
	} //checkAlive()
} //Party
