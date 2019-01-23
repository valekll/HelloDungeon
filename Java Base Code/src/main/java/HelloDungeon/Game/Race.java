package HelloDungeon.Game;

public class Race {
	private RaceType name;
	private double[] stats;
	private Ability[] abilities;
	//Stats for generic model
	static final double[] BASESTATS = {20.0, 10.0, 0.0, 1.0, 1.0, 0.0, 0.0, 40.0, 1.0, 
										3.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0};
	
	/**
	 * Creates a generic race with base stats
	 */
	public Race() {
		stats = new double[18];
		abilities = new Ability[0];
		setStats(BASESTATS);
	} //Race()
	
	/**
	 * Creates a Race of the given type.
	 * @param n the Race name
	 */
	public Race(RaceType n) {
		name = n;
		stats = new double[18];
		abilities = new Ability[0];
		setStats(ResourceManager.getRaceStats(n));
	} //Race()
	
	/**
	 * Returns the specified stat or -1.0 if an invalid stat type was specified
	 * @param s the specified stat type
	 * @return returns the specified stat as a double or -1.0 if an invalid stat 
	 * type was specified
	 */
	public double getStat(StatName n) {
		//[0: hp, 1: stamina, 2: mana, 3: attack, 4: defense, 5: magicAttack, 6: magicDefense, 7: speed, 8: luck
		// 9: hpS, 10: staminaS, 11: manaS, 12: attackS, 13: defenseS, 14: magicAttackS, 15: magicDefenseS,
		// 16: speedS, 17: luckS]
		switch(n) { 
        	case HP: 
        		return stats[0];
        	case Stamina: 
        		return stats[1];
        	case Mana: 
        		return stats[2];
        	case Attack: 
        		return stats[3];
        	case Defense: 
        		return stats[4];
        	case MagicAttack: 
        		return stats[5];
        	case MagicDefense: 
        		return stats[6];
        	case Speed: 
        		return stats[7];
        	case Luck: 
        		return stats[8];
        	case HPS: 
        		return stats[9];
        	case StaminaS: 
        		return stats[10];
        	case ManaS: 
        		return stats[11];
        	case AttackS: 
        		return stats[12];
        	case DefenseS: 
        		return stats[13];
        	case MagicAttackS: 
        		return stats[14];
        	case MagicDefenseS: 
        		return stats[15];
        	case SpeedS: 
        		return stats[16];
        	case LuckS: 
        		return stats[17];
        	default: 
        		return -1.0;
		} //switch
	} //getStat()
	
	/**
	 * Gets all stats of the Race
	 * @return the stats of the Class as an array
	 */
	public double[] getStats() {
		return stats;
	} //getStats()

	/**
	 * Sets all stats to specified values
	 * @param s the stats
	 */
	public void setStats(double[] s) {
		for(int i = 0; i < 18; i++) {
			//Min values for all stats
			if(s[i] < 0.0) {
				stats[i] = 0.0;
			} //else if
			//Set specified stats if they aren't out of range
			else {
				stats[i] = s[i];
			} //else
		} //for
	} //setStats()

	/**
	 * Gets the abilities of the Race.
	 * @return the abilities
	 */
	public Ability[] getAbilities() {
		return abilities;
	} //getAbilities()
	
	/**
	 * Gets the Race's ability at a given index.
	 * @param i the index
	 * @return the ability
	 */
	public Ability getAbility(int i) {
		return abilities[i];
	} //getAbility()
	
	/**
	 * Adds abilities to the Race's repertoire.
	 * @param abl the abilities to add
	 */
	public void addAbilities(Ability ... abl) {
		Ability[] temp = abilities;
		abilities = new Ability[temp.length + abl.length];
		System.arraycopy(temp, 0, abilities, 0, temp.length);
		System.arraycopy(abl, 0, abilities, temp.length, abl.length);
	} //addAbilities()
	
	/**
	 * Gets the race name.
	 * @return the name of the race
	 */
	public String getName() {
		if(name != null)
			return name.toString();
		return "BASE";
	} //getName()
	
	/**
	 * Overrides the toString method.
	 */
	@Override
	public String toString() {
		return getName();
	} //toString()
	
	/**
	 * Checks if another race is the same as this race. They must share the same name.
	 * @param r the other race
	 * @return if they are the same
	 */
	public boolean equals(Race r) {
		return r.getName().toUpperCase().equals(getName().toUpperCase());
	} //equals()
} //Race
