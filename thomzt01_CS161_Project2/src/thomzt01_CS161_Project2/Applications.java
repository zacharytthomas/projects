package thomzt01_CS161_Project2;
/*
 * Zachary Thomas
 * Oct. 2018
 * Prof. Petruska
 * Project 2
 */
public class Applications  {

	/**
	 * main method of the application and the center for the running of the application, pulls all elements together and 
	 * makes the magic happen
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] names = new String[] {"Sweet Potato", "Marzipan Hoofs", "Night Breeze", "Singapur Hero", "Stargazer Boy", "Cajun Miracle",
                "Jockey Duster", "Imperial", "Lion Mane", "Fender Bender" , "Torquemada", "Grandeur"};
		RaceOrganizer model = new RaceOrganizer(25,200);
		NameSelector selector = new NameSelector(names, model);
		selector.buildFrame();
		selector.setModel(model);
		while(!selector.getSelectionFinished()) {
			System.out.print("");
		}
		selector.resetSelectionFinished();
		RaceGUI raceGui = new RaceGUI(model,"A Day at the Races");
		while(!selector.getSelectionFinished()) {
			System.out.print("");
		}
		model.runRace();
	}
}
