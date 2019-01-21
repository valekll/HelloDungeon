package HelloDungeon.Game;

public class tester {
	public static void main(String[] args) {
		Character dude = new Character(RaceType.Dwarf);
		System.out.println("Character of race: " + dude.getRace() + "\nStats:");
		double[] stats = dude.getStats();
		for(int i = 0; i < stats.length; i++) {
			System.out.println(stats[i]);
		}//for
	} //main
} //tester
