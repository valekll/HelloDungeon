package HelloDungeon.Game;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


/**
 * Generates new resource files for HelloDungeon
 * @author Valek
 * @version 1.0
 * @Since 1.0
 */
public class ResourceManager {
	static final Scanner in = new Scanner(System.in);
	
	/**
	 * Manages the resources files.
	 * @param args initial arguments supplied by the command line
	 */
	public static void main(String[] args) {
		generateResources();
		readFileRaw("stat");
		in.close();
	} //main()
	
	/**
	 * Generates new resource files or updates existing ones with additional information according to user specifications.
	 */
	public static void generateResources() {
		boolean b = true; //keep running?
		boolean init = true; //first time run
		//let user create as many resources as they wish before exiting
		while(b) {
			if(init) {
				String filetype = requestFileType();
				String filename = "src/main/resources/" + filetype  + ".config";
				appendToFile(getWriteData(filetype), filename);
				init = false;
			} //if
			else {
				System.out.println("Would you like to create another resource? (y/n): ");
				int confirm = userResponse(FileType.values());
				if(confirm > 0) {
					String filetype = requestFileType();
					String filename = "src/main/resources/" + filetype  + ".config";
					appendToFile(getWriteData(filetype), filename);
				} //if
				else if(confirm < 0) {
					return;
				} //else if
			} //else
		} //while
	} //generateResources()
	
	/**
	 * Writes to designated resource file according to user
	 * specified instructions.
	 */
	public static void appendToFile(String[] data, String filename) {
		//write to file
		try {
			for(String d: data) {
				Files.write(Paths.get(filename), d.getBytes(), StandardOpenOption.APPEND);
			} //for
		} //try
		catch (IOException e) {
		    e.printStackTrace();
		} //catch
	} //appendToFile()
	
	/**
	 * Checks with user for the file type and then returns the designated file type
	 * @return the file type
	 */
	public static String requestFileType() {
		//Ask user what kind of resource to make
		System.out.println("What type of resource are you creating?");
		String userfiletype = in.nextLine();
		String filename = "";
		//set file type according to predefined list of file types
		for(FileType f: FileType.values()) {
			if(f.toString().equals(userfiletype)) {
				filename = userfiletype;
			} //if
		} //for
		//file type not defined; give user feedback
		if(filename.equals("")) {
			System.out.println("Invalid Resource Type. \nType (help) for a list of Resource Types.");
			return requestFileType();
		} //if
		return filename;
	} //requestFileType()
	
	/**
	 * Switches the write format depending on the file type, then gets write data appropriately.
	 * @param filename the designated file type
	 * @return the write data
	 */
	public static String[] getWriteData(String filename) {
		String[] data = new String[0];
		if(filename.equals("stat")) {
			System.out.println("stat print selected");
			data = writeToStat();
		} //if
		return data;
	} //getWriteData()
	
	/**
	 * Writes data to be added to the stat.config file according to user input.
	 * @return the written data
	 */
	public static String[] writeToStat() {
		//Stat files will contain a Name and an Array of Values
		String[] data = {"", ""};
		System.out.println("Race or Class Name: ");
		data[0] = in.nextLine().toUpperCase();
		boolean isValid = false;
		for(RaceType r: RaceType.values()) {
			if(r.toString().toUpperCase().equals(data[0])) {
				isValid = true;
			} //if
		} //for
		if(!isValid) {
			for(ClassType c: ClassType.values()) {
				if(c.toString().toUpperCase().equals(data[0])) {
					isValid = true;
				} //if
			} //for
			if(!isValid) {
				boolean b = true;
				while(b) {
					System.out.println(data[0] + 
							" is an invalid Race.\nType (y/n) to continue or (help) for a list of valid races: ");
					int confirm = userResponse(RaceType.values());
					if(confirm > 0) {
						return writeToStat();
					} //if
					else if(confirm < 0) {
						return new String[]{""};
					} //else if
				} //while
			} //if
		} //if
		//iterate through each stat type and get user input
		for(int i = 0; i < 18; i++) {
			//Ask the user for each stat type
			switch(i) {
				//[0: hp, 1: stamina, 2: mana, 3: attack, 4: defense, 
				// 5: magicAttack, 6: magicDefense, 7: speed, 8: luck]
				case 0: System.out.println("HP: "); 
						break;
				case 1: System.out.println("Stamina: "); 
						break;
				case 2: System.out.println("Mana: "); 
						break;
				case 3: System.out.println("Attack: "); 
						break;
				case 4: System.out.println("Defense: "); 
						break;
				case 5: System.out.println("Magic Attack: "); 
						break;
				case 6: System.out.println("Magic Defense: "); 
						break;
				case 7: System.out.println("Speed: "); 
						break;
				case 8: System.out.println("Luck: "); 
						break;
				case 9: System.out.println("HP Scaling: "); 
						break;
				case 10: System.out.println("Stamina Scaling: "); 
						break;
				case 11: System.out.println("Mana Scaling: "); 
						break;
				case 12: System.out.println("Attack Scaling: "); 
						break;
				case 13: System.out.println("Defense Scaling: "); 
						break;
				case 14: System.out.println("Magic Attack Scaling: "); 
						break;
				case 15: System.out.println("Magic Defense Scaling: "); 
						break;
				case 16: System.out.println("Speed Scaling: "); 
						break;
				case 17: System.out.println("Luck Scaling: "); 
						break;
			} //switch
			data[1] = data[1] + "|" + in.nextLine();
		} //for
		data[0] = data[0] + "\n";
		data[1] = data[1] + "\n";
		return data;
	} //writeToStat()
	
	/**
	 * Get the recorded values of a designated race, and send them back as stats.
	 * @param race the type of race to get
	 * @return the race's stats
	 */
	public static double[] getRaceStats(RaceType race) {
		String[] raw = readFileRaw("stat");
		String[] tokens = new String[0];
		double[] stats = new double[18];
		int rawlength = raw.length;
		if(rawlength % 2 == 1) {
			rawlength--;
		} //if
		//iterate through, checking every other entry for race type
		for(int i = 0; i < rawlength; i += 2) {
			if(RaceType.valueOf(raw[i]).equals(race)) {
				tokens = tokenize(raw[i + 1]); //get stat tokens
				break;
			} //if
		} //for
		//tokens exist, so turn them into stats
		if(tokens.length == stats.length) {
			for(int i = 0; i < stats.length; i++) {
				stats[i] = Double.parseDouble(tokens[i]);
			} //for
			return stats;
		} //if
		//Unable to procure a race
		System.out.println("Insufficient racial data. Returning base stats.");
		return Race.BASESTATS;
	} //getRaceStats()
	
	/**
	 * Get the recorded values of a designated Class, and send them back as stats.
	 * @param class the type of Class to get
	 * @return the class's stats
	 */
	public static double[] getClassStats(ClassType cl) {
		String[] raw = readFileRaw("stat");
		String[] tokens = new String[0];
		double[] stats = new double[18];
		int rawlength = raw.length;
		if(rawlength % 2 == 1) {
			rawlength--;
		} //if
		//iterate through, checking every other entry for race type
		for(int i = 0; i < rawlength; i += 2) {
			if(ClassType.valueOf(raw[i]).equals(cl)) {
				tokens = tokenize(raw[i + 1]); //get stat tokens
				break;
			} //if
		} //for
		//tokens exist, so turn them into stats
		if(tokens.length == stats.length) {
			for(int i = 0; i < stats.length; i++) {
				stats[i] = Double.parseDouble(tokens[i]);
			} //for
			return stats;
		} //if
		//Unable to procure a race
		System.out.println("Insufficient class data. Returning base stats.");
		return Class.BASESTATS;
	} //getClassStats()
	
	/**
	 * Breaks a string down into several trimmed substring tokens using the delimiter "|".
	 * @param raw the string to break down
	 * @return the substring tokens
	 */
	public static String[] tokenize(String raw) {
		String[] tokens = new String[0];
		//make sure String needs to be tokenized
		if(raw.contains("|")) {
			//check for delimiters and to make sure raw isn't empty
			while(raw.contains("|") && !(raw.equals("") || raw.equals("|"))) {
				String token = raw.substring(raw.lastIndexOf("|")); //everything delimiter onward
				raw = raw.substring(0, raw.lastIndexOf("|")); //subtract new token from old string
				token = token.substring(1); //erase delimiter
				String[] temp = tokens; //add token to first slot in tokens to keep order
				tokens = new String[temp.length + 1];
				System.arraycopy(temp, 0, tokens, 1, temp.length);
				tokens[0] = token;
			} //while
		} //if
		else {
			tokens = new String[] {raw};
		} //else 
		//trim all tokens
		for(int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].trim();
		} //for
		return tokens;
	} //tokenize()
	
	/**
	 * Checks whether the user wants to continue or not. 
	 * 1 = YES, -1 = NO, 0 = HELP 
	 * @param e enum of file types
	 * @return 1, 0, -1 according to user in
	 */
	public static int userResponse(Enum<?>[] e) {
		String confirm = in.nextLine();
		if(confirm.toUpperCase().equals("Y") || confirm.toUpperCase().equals("YES")) {
			return 1;
		} //if
		else if(confirm.toUpperCase().equals("N") || confirm.toUpperCase().equals("NO")) {
			return -1;
		} //else if
		//let user know what file types exist if they ask
		else if(confirm.toUpperCase().equals("HELP") || confirm.toUpperCase().equals("H")) {
			//iterate through file types
			for(int j = 0; j < e.length; j++) {
				//print them all in a nice format
				if (j == e.length - 1) {
					System.out.println(e[j].toString());
				} //if
				else {
					System.out.print(e[j].toString() + ", ");
				} //else
			} //for
		} //if
		return 0;
	} //userResponse()
	
	/**
	 * Reads the raw data of a given file.
	 * @param filetype the type of file to read
	 * @return the file data
	 */
	public static String[] readFileRaw(String filetype) {
		String[] data = new String[0];
		try {
			File config = new File("src/main/resources/" + filetype  + ".config");
			Scanner configScanner = new Scanner(config);
			System.out.println(configScanner.hasNextLine());
			while(configScanner.hasNextLine()) {
				String[] temp = data;
				data = new String[temp.length + 1];
				System.arraycopy(temp, 0, data, 0, temp.length);
				data[data.length - 1] = configScanner.nextLine();
			} //while
			configScanner.close();
		} //try
		catch (Exception e) {
			System.out.println("Error: " + e);
		} //catch
		for(String d: data) {
			System.out.println(d);
		} //for
		return data;
	} //readFileRaw()
} //ResourceGenerator
