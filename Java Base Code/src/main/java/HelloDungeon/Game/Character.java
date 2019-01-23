package HelloDungeon.Game;

/**
 * A Character is a Thing that is alive that can interact 
 * with other Things and move within the confines of a place.
 * @author Valek
 * @version 1.0
 * @since 1.0
 */
public class Character extends Thing {
	private String name;
	private int level;
	private Race race;
	private Class cl;
	//[0: hp, 1: stamina, 2: mana, 3: attack, 4: defense, 5: magicAttack, 6: magicDefense, 7: speed, 8: luck,
	// 9: hpS, 10: staminaS, 11: manaS, 12: attackS, 13: defenseS, 14: magicAttackS, 15: magicDefenseS,
	// 16: speedS, 17: luckS]
	private double[] stats;
	//Current hp, stamina, and mana
	private double[] dynaStats;
	private Ability[] abilities;
	
	/**
	 * Constructs a Character with baseline stats
	 */
	public Character() {
		super();
		race = new Race();
		cl = new Class();
		setName();
		level = 1;
		stats = new double[18];
		dynaStats = new double[9];
		abilities = new Ability[0];
		setStats(Race.BASESTATS, Class.BASESTATS);
		setScalingStats(Race.BASESTATS, Class.BASESTATS);
	} //Character()
	
	/**
	 * Constructs a Character of a given race.
	 * @param r the given race
	 */
	public Character(Race r) {
		this();
		race = r;
		setName();
		setStats(race.getStats(), Class.BASESTATS);
	} //Character()
	
	/**
	 * Constructs a Character of a given race type.
	 * @param rt the given race type
	 */
	public Character(RaceType rt) {
		this();
		race = new Race(rt);
		setName();
		setStats(race.getStats(), Class.BASESTATS);
		if(race.getStats().length == 18) {
			setScalingStats(race.getStats(), Class.BASESTATS);
		} //else if
		else {
			setScalingStats(Race.BASESTATS, Class.BASESTATS);
		} //else
	} //Character()
	
	/**
	 * Constructs a Character of a given class type.
	 * @param ct the given class type
	 */
	public Character(ClassType ct) {
		this();
		cl = new Class(ct);
		setName();
		setStats(Race.BASESTATS, cl.getStats());
		if(cl.getStats().length == 18) {
			setScalingStats(Race.BASESTATS, cl.getStats());
		} //else if
		else {
			setScalingStats(Race.BASESTATS, Class.BASESTATS);
		} //else
	} //Character()
	
	/**
	 * Constructs a Character with given stats. Will use custom scalings (stats from 9-17)
	 * if provided. 
	 * @param s the given stats of size 9 or 18
	 */
	public Character(double[] s) {
		this();
		setStats(Race.BASESTATS, Class.BASESTATS, s);
		if(s.length == 18)  {
			setScalingStats(Race.BASESTATS, Class.BASESTATS, s);
		} //if
		else {
			setScalingStats(Race.BASESTATS, Class.BASESTATS);
		} //else
	} //Character()
	
	/**
	 * Constructs a Character of the given name, level, race type, and class type.
	 * @param n the name
	 * @param lvl the level
	 * @param rt the race type
	 * @param c the class type
	 */
	public Character(String n, int lvl, RaceType rt, ClassType c) {
		super();
		name = n;
		level = lvl;
		race = new Race(rt);
		cl = new Class(c);
		stats = new double[18];
		dynaStats = new double[9];
		setStats(race.getStats(), cl.getStats());
		if(race.getStats().length == 18 && cl.getStats().length == 18)  {
			setScalingStats(race.getStats(), cl.getStats());
		} //if
		else if(race.getStats().length != 18 && cl.getStats().length == 18) {
			setScalingStats(Race.BASESTATS, cl.getStats());
		} //else if
		else if(race.getStats().length == 18 && cl.getStats().length != 18) {
			setScalingStats(race.getStats(), Class.BASESTATS);
		} //else if
		else {
			setScalingStats(Race.BASESTATS, Class.BASESTATS);
		} //else
	} //Character()
	
	/**
	 * Constructs a Character of the given name, level, race type, and stats. Will use custom scalings (stats from 9-17)
	 * if provided. 
	 * @param n the name
	 * @param lvl the level
	 * @param rt the race type
	 * @param s the given stats of size 9 or 18
	 */
	public Character(String n, int lvl, RaceType rt, ClassType c, double[] s) {
		super();
		name = n;
		level = lvl;
		race = new Race(rt);
		cl = new Class(c);
		stats = new double[18];
		dynaStats = new double[9];
		setStats(race.getStats(), cl.getStats(), s);
		if(race.getStats().length == 18 && cl.getStats().length == 18 && s.length == 18)  {
			setScalingStats(race.getStats(), cl.getStats(), s);
		} //if
		else if(race.getStats().length != 18 && cl.getStats().length == 18 && s.length == 18) {
			setScalingStats(Race.BASESTATS, cl.getStats(), s);
		} //else if
		else if(race.getStats().length == 18 && cl.getStats().length != 18 && s.length == 18) {
			setScalingStats(race.getStats(), Class.BASESTATS, s);
		} //else if
		else if(race.getStats().length != 18 && cl.getStats().length != 18 && s.length == 18) {
			setScalingStats(Race.BASESTATS, Class.BASESTATS, s);
		} //else if
		else if(race.getStats().length == 18 && cl.getStats().length == 18 && s.length != 18) {
			setScalingStats(race.getStats(), cl.getStats());
		} //else if
		else if(race.getStats().length != 18 && cl.getStats().length == 18 && s.length != 18) {
			setScalingStats(Race.BASESTATS, cl.getStats());
		} //else if
		else if(race.getStats().length == 18 && cl.getStats().length != 18 && s.length != 18) {
			setScalingStats(race.getStats(), Class.BASESTATS);
		} //else if
		else {
			setScalingStats(Race.BASESTATS, Class.BASESTATS);
		} //else
	} //Character()
	
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
	}//getStat()
	
	/**
	 * Gets all non scaling stats of the Character
	 * @return the stats of the Character as an array
	 */
	public double[] getStats() {
		double[] s = new double[9];
		System.arraycopy(stats, 0, s, 0, 9);
		return s;
	} //getStats()

	/**
	 * Sets all non scaling stats to specified values
	 * @param rs race stats 
	 * @param cs class stats
	 */
	public void setStats(double[] rs, double[] cs) {
		for(int i = 0; i < 9; i++) {
			//Min values for hp, stamina, attack, defense, speed and luck
			if(((i == 0) || (i == 1) || (i == 3) || (i == 4) || (i == 7) || (i == 8)) && (rs[i] + cs[i]) < 1.0) {
				stats[i] = 1.0;
			} //if
			//Min values for all other stats
			else if((rs[i] +  cs[i]) < 0.0) {
				stats[i] = 0.0;
			} //else if
			//Set specified stats if they aren't out of range
			else {
				stats[i] = (rs[i] + cs[i]);
			} //else
			dynaStats[i] = stats[i];
		} //for
	} //setStats()
	
	/**
	 * Sets all non scaling stats to specified values
	 * @param rs race stats 
	 * @param cs class stats
	 * @param ss special stats
	 */
	public void setStats(double[] rs, double[] cs, double[] ss) {
		for(int i = 0; i < 9; i++) {
			//Min values for hp, stamina, attack, defense, speed and luck
			if(((i == 0) || (i == 1) || (i == 3) || (i == 4) || (i == 7) || (i == 8)) && (rs[i] + cs[i] + ss[i]) < 1.0) {
				stats[i] = 1.0;
			} //if
			//Min values for all other stats
			else if((rs[i] +  cs[i] + ss[i]) < 0.0) {
				stats[i] = 0.0;
			} //else if
			//Set specified stats if they aren't out of range
			else {
				stats[i] = (rs[i] + cs[i] + ss[i]);
			} //else
			dynaStats[i] = stats[i];
		} //for
	} //setStats()
	
	/**
	 * Sets all scaling stats to specified values. Only stats from 9 > 18.
	 * @param rs racial stats 
	 * @param cs class stats
	 */
	public void setScalingStats(double[] rs, double[] cs) {
		for(int i = 9; i < 18; i++) {
			//Min values for all scalings
			if((rs[i] + cs[i]) < 0.0) {
				stats[i] = 0.0;
			} //else if
			//Set specified scalings if they aren't out of range
			else {
				stats[i] = (rs[i] + cs[i]);
			} //else
		} //for
	} //setScalingStats()

	/**
	 * Sets all scaling stats to specified values. Only stats from 9 > 18.
	 * @param rs racial stats 
	 * @param cs class stats
	 * @param ss special stats
	 */
	public void setScalingStats(double[] rs, double[] cs, double[] ss) {
		for(int i = 9; i < 18; i++) {
			//Min values for all scalings
			if((rs[i] + cs[i] + ss[i]) < 0.0) {
				stats[i] = 0.0;
			} //else if
			//Set specified scalings if they aren't out of range
			else {
				stats[i] = (rs[i] + cs[i] + ss[i]);
			} //else
		} //for
	} //setScalingStats()

	
	/**
	 * Gets the race of the Character.
	 * @return the race
	 */
	public Race getRace() {
		return race;
	} //getRace()
	
	/**
	 * Gets the class of the Character.
	 * @return the class
	 */
	public Class getCl() {
		return cl;
	}
	
	/**
	 * Gets the Character's level.
	 * @return the level
	 */
	public int getLevel() {
		return level;
	} //getLevel()
	
	/**
	 * Sets the Character's level to the given value. Does not adjust stats based on level.
	 * @param level the given level
	 */
	public void setLevel(int level) {
		this.level = level;
	} //setLevel()

	/**
	 * Sets the Character's level to the given value, and then sets all stat values accordingly.
	 * @param level the given level
	 */
	public void setLevelThenStats(int level) {
		this.level = level;
		for(int i = 0, j = 9; i < 9; i++, j++) {
			//base stats + (number of total level ups * char scaling)
			this.stats[i] = this.stats[i] + ((level - 1) * this.stats[j]);
		} //for
	} //setLevelThenStats()
	
	/**
	 * Increments the Character's level and stats accordingly.
	 */
	public void incLevel() {
		this.level++;
		for(int i = 0, j = 9; i < 9; i++, j++) {
			this.stats[i] += this.stats[j];
			//restore 10% hp, stamina, and mana upon lvl up
			if(i < 3) {
				dynaStats[i] += stats[i] * 0.1;
				//don't overcap
				if(dynaStats[i] > stats[i]) {
					dynaStats[i] = stats[i];
				} //if
			} //if
		} //for
	} //incLevel()
	
	/**
	 * Gets the abilities of the Character.
	 * @return the abilities
	 */
	public Ability[] getAbilities() {
		return abilities;
	} //getAbilities()
	
	/**
	 * Gets the Character's ability at a given index.
	 * @param i the index
	 * @return the ability
	 */
	public Ability getAbility(int i) {
		return abilities[i];
	} //getAbility()
	
	/**
	 * Adds abilities to the Character's repertoire.
	 * @param abl the abilities to add
	 */
	public void addAbilities(Ability ... abl) {
		Ability[] temp = abilities;
		abilities = new Ability[temp.length + abl.length];
		System.arraycopy(temp, 0, abilities, 0, temp.length);
		System.arraycopy(abl, 0, abilities, temp.length, abl.length);
	} //addAbilities()

	/**
	 * Modifies the value of a current dynamic stat by a given value.
	 * @param stat the stat to modify
	 * @param val the change value
	 */
	public void modifyDynaStatsByValue(StatName stat, double val) {
		switch(stat) {
			case HP:
				dynaStats[0] += val;
				if(dynaStats[0] > stats[0]) { //don't overcap
					dynaStats[0] = stats[0];
				} //if
				else if(dynaStats[0] < 0) { //can't go below 0
					dynaStats[0] = 0;
				} //else if
				return;
			case Stamina:
				dynaStats[1] += val;
				if(dynaStats[1] > stats[1]) {
					dynaStats[1] = stats[1];
				} //if
				else if(dynaStats[1] < 0) {
					dynaStats[1] = 0;
				} //else if
				return;
			case Mana:
				dynaStats[2] += val;
				if(dynaStats[2] > stats[2]) {
					dynaStats[2] = stats[2];
				} //if
				else if(dynaStats[2] < 0) {
					dynaStats[2] = 0;
				} //else if
				return;
			case Attack: 
				dynaStats[3] += val;
				if(dynaStats[3] > stats[3]) {
					dynaStats[3] = stats[3];
				} //if
				else if(dynaStats[3] < 0) {
					dynaStats[3] = 0;
				} //else if
        		return;
        	case Defense: 
        		dynaStats[4] += val;
				if(dynaStats[4] > stats[2]) {
					dynaStats[4] = stats[4];
				} //if
				else if(dynaStats[4] < 0) {
					dynaStats[4] = 0;
				} //else if
        		return;
        	case MagicAttack: 
        		dynaStats[5] += val;
				if(dynaStats[5] > stats[5]) {
					dynaStats[5] = stats[5];
				} //if
				else if(dynaStats[5] < 0) {
					dynaStats[5] = 0;
				} //else if
        		return;
        	case MagicDefense: 
        		dynaStats[6] += val;
				if(dynaStats[6] > stats[6]) {
					dynaStats[6] = stats[6];
				} //if
				else if(dynaStats[6] < 0) {
					dynaStats[6] = 0;
				} //else if
        		return;
        	case Speed: 
        		dynaStats[7] += val;
				if(dynaStats[7] > stats[7]) {
					dynaStats[7] = stats[7];
				} //if
				//25% reduction from lvl 1 base is min
				else if(dynaStats[7] < 30) {
					dynaStats[7] = 30;
				} //else if
        		return;
        	case Luck: 
        		dynaStats[8] += val;
				if(dynaStats[8] > stats[8]) {
					dynaStats[8] = stats[8];
				} //if
				else if(dynaStats[8] < 0) {
					dynaStats[8] = 0;
				} //else if
        		return;
			default:
				return;
		} //switch
	} //modifyDynaStats()
	
	/**
	 * Modifies the value of current Health, Stamina, or Mana by a given percentage.
	 * @param stat the stat to modify
	 * @param per the percentage of change
	 */
	public void modifyDynaStatsByPercent(StatName stat, double per) {
		double val = per / 100.0;
		modifyDynaStatsByValue(stat, val);
	} //modifyDynaStatsByPercent()
	
	/**
	 * Gets the dynamic stats of the Character
	 * @return the dynamic stats
	 */
	public double[] getDynaStats() {
		return dynaStats;
	} //getDynaStats()
	
	/**
	 * Gets the Character's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	} //getName()

	/**
	 * Sets the Character's name to a default name.
	 */
	public void setName() {
		this.name = race.getName();
		if(this.name.equals("BASE")) {
			name = "Stranger";
		} //if
	} //setName()
	
	/**
	 * Sets the Character's name to a given name.
	 * @param name the given name
	 */
	public void setName(String name) {
		this.name = name;
	} //setName()
	
	/**
	 * Checks if the Character is alive
	 * @return if the character is alive
	 */
	public boolean checkAlive() {
		if(dynaStats[0] > 0) {
			return true;
		} //if
		return false;
	} //checkAlive()
	
	/**
	 * Checks if a given Character is the same as this Character. They must share the same name,
	 * race, and class.
	 * @param c the given Character
	 * @return if they are the same Character
	 */
	public boolean equals(Character c) {
		boolean same = true;
		if(!(c.getName().toUpperCase().equals(this.getName().toUpperCase()) && 
			(c.getCl().equals(this.cl) && (c.getRace().equals(this.race))))) {
			same = false;
		} //if
		return same;
	} //equals()
} //Character
