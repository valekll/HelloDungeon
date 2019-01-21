package HelloDungeon.Game;

/**
 * An ability that can be activated by an entity in combat.
 * @author Valek
 * @version 1.0
 * @since 1.0
 */
public class CombatAbility extends Ability {
	//0: PhysicalValue, 1: PhysicalScaling, 2: MagicValue, 3: MagicScaling, 4: Pierce
	//5: StaminaConsumption, 6: ManaConsumption, 7: Speed
	private double[] abStats; //negative values restore properties
	private Effect[] effects;
	private String caType; //combat ability type - see notes for more details
	private int usesTurn;
	private int turnLimit; //no limit: -1
	private int usesBattle;
	private int battleLimit; //no limit: -1
	
	/**
	 * Creates an ActiveAbility with a name, description, and given ability stats with no Effects.
	 * @param n the name
	 * @param d the description
	 * @param s the ability stats
	 * @param t the combat ability type
	 */
	public CombatAbility(String n, String d, double[] s, String t) {
		super(n, d);
		resetBattle();
		caType = t;
		turnLimit = -1;
		battleLimit = -1;
		setAbStats(s);
	} //CombatAbility()

	/**
	 * Creates an ActiveAbility with a name, description, given ability stats, and given Effects.
	 * @param n the name
	 * @param d the description
	 * @param s the ability stats
	 * @param t the combat ability type
	 * @param e the Effects
	 */
	public CombatAbility(String n, String d, double[] s, String t, Effect[] e) {
		this(n, d, s, t);
		effects = e;
	} //CombatAbility()
	
	/**
	 * Creates an ActiveAbility with a name, description, given ability stats, and given Effects.
	 * @param n the name
	 * @param d the description
	 * @param s the ability stats
	 * @param e the Effects
	 */
	public CombatAbility(String n, String d, double[] s, String t, Effect[] e, int tl, int bl) {
		this(n, d, s, t);
		effects = e;
		if(tl >= -1 && tl != 0) {
			turnLimit = tl;
		} //if
		if(bl >= -1 && bl != 0) {
			battleLimit = bl;
		} //if
	} //CombatAbility()
	
	/**
	 * {@inheritDoc}
	 * Iterates the ability if it does not exceed a battle or a turn limit.
	 */
	@Override
	public boolean iterate() {
		if((usesTurn < turnLimit || turnLimit == -1) && (usesBattle < battleLimit || battleLimit == -1)) {
			usesTurn++;
			usesBattle++;
			return true;
		} //if
		return false;
	} //consume()

	/**
	 * Resets the ability for the next turn.
	 */
	public void resetTurn() {
		usesTurn = 0;
	} //resetTurn()
	
	/**
	 * Resets the ability for the next battle, and removes all environmental input.
	 */
	public void resetBattle() {
		resetTurn();
		usesBattle = 0;
	} //resetBattle()
	
	/**
	 * Gets the amount of times the ability has been used this turn.
	 * @return the usesTurn
	 */
	public int getUsesTurn() {
		return usesTurn;
	} //getUsesTurn()
	
	/**
	 * Gets the amount of times the ability has been used this battle.
	 * @return the usesBattle
	 */
	public int getUsesBattle() {
		return usesBattle;
	} //getUsesBattle()
	
	/**
	 * Returns the specified ability stat or -1.0 if an invalid ability stat type was specified
	 * @param s the specified ability stat type
	 * @return returns the specified abilty stat as a double or -1.0 if an invalid ability stat 
	 * type was specified
	 */
	public double getStat(CombatAbilityStat s) {
		//0: PhysicalValue, 1: PhysicalScaling, 2: MagicValue, 3: MagicScaling, 4: Pierce
		//5: StaminaConsumption, 6: ManaConsumption, 7: Speed
		switch(s) { 
        	case PhysicalValue: 
        		return abStats[0];
        	case PhysicalScaling: 
        		return abStats[1];
        	case MagicValue: 
        		return abStats[2];
        	case MagicScaling: 
        		return abStats[3];
        	case Pierce:
        		return abStats[4];
        	case StaminaConsumption: 
        		return abStats[5];
        	case ManaConsumption: 
        		return abStats[6];
        	case Speed: 
        		return abStats[7];
        	default: 
        		return -1.0;
		} //switch
	}//getStat()

	/**
	 * Gets the stat values of the ability.
	 * @return the stat values
	 */
	public double[] getAbStats() {
		return abStats;
	} //getAbStats()

	/**
	 * Sets all ability stats to specified values
	 * @param s specified stats
	 */
	public void setAbStats(double[] s) {
		for(int i = 0; i < 8; i++) {
			abStats[i] = s[i];
		} //for
	} //setAbStats()

	/**
	 * Gets the Effects of the ability.
	 * @return the effects
	 */
	public Effect[] getEffects() {
		return effects;
	} //getEffects()

	/**
	 * Gets the type of combat ability.
	 * @return the type
	 */
	public String getCombatAbilityType() {
		return caType;
	} //getCombatAbilityType()

	/**
	 * Sets the combat ability type
	 * @param t the type
	 */
	public void setCombatAbilityType(String t) {
		this.caType = t;
	} //setCombatAbilityType()
} //CombatAbility
