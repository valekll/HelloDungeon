package HelloDungeon.Game;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A stage upon which two teams 
 * @author Valek
 * @version 1.0
 * @since 1.0
 */
public class BattleStage {
	private Environment enviro;
	private Party[] teams;
	private Timer tmr;
	
	/**
	 * Constructs a Battle Stage for combat to occur.
	 * @param p1 the left party
	 * @param p2 the right party
	 * @param e the environment
	 */
	public BattleStage(Party p1, Party p2, Environment e) {
		teams = new Party[2];
		teams[0] = p1;
		teams[1] = p2;
		enviro = e;
		tmr = new Timer();
	} //CombatStage()
	
	/**
	 * Starts a battle stage and commences combat.
	 * @return the resulting teams
	 */
	public Party[] start() {
		return teams;
	} //start()
	
	
} //BattleStage
