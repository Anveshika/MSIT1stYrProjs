/*

	Note: This test is different from the online programming tests.

	Cinema Tickets
		
		A cinema has seats, typically with 10 rows titled from A to J and 15 columns numbered from 1 to 10.
		So, a seat is referred to as A1 if it is in the first row and first column.
		
		A cinema has multiple screens playing a movie, typically 4 and each screen has shows
		with show times represented in 24 hour format.
		For example, Screen 1 plays a movie at 09:00, 12:00, 15:00, 18:00, 21:00

		Your program has to automate the ticket booking.

		Design your program using Data Abstraction and Data Hiding principles.

		Your goal is to understand the main method and complete the Java program.

		Get all test cases correct without changing the main method.

		After submission the autograder will evaluate the hidden test cases.

		Hint:

		1. Think about Arrays of Objects and Top Down Design

		2. First write the classes, constructors and methods
			(Answer to this step is in the main method. You just have to see it!)

		3. Get the program to compile without syntax errors

		4. Then write code in the methods to get all the test cases to pass

*/

public class CinemaTickets{
	public static void main(String args[]){
		// creates an object of cinemax using a no argument constructor
		Cinema cinemax = new Cinema();

		// invokes instance method of Cinema to get all the screen objects
		Screen[] screens = cinemax.getAllScreens();

		// instance method of screen to set the name of the movie
		screens[0].setMovie("Transformers: Age of Extinction");
		screens[1].setMovie("Blood Diamond");

		// Testcase 1: Using getMovie instance method
		// to check if the screen is set with the correct movie name
		if(!screens[0].getMovie().equals("Transformers: Age of Extinction"))
			System.out.println("Testcase 1 Failed");
		else
			System.out.println("Testcase 1 Passed");
		
		// Testcase 2 Using getMovie instance method
		// to check if the screen is set with the correct movie name
		if(!screens[1].getMovie().equals("Blood Diamond"))
			System.out.println("Testcase 2 Failed");
		else
			System.out.println("Testcase 2 Passed");

		// Initialize the show times for screen 1 as an array of strings
		String[] showTimes = {"09:00", "12:00", "15:00", "18:00", "21:00"};
		// Set the show times to the screen 1 object
		// For each show time the setShowTimes instance method of screen should
		// create an object of show and set its time
		screens[0].setShowTimes(showTimes);

		// Get the show objects from a screen.
		// If there are 5 showtimes then there will be 5 show objects.
		Show[] shows = screens[0].getShows();
		
		// Testcase 3
		if("09:00".equals(shows[0].getShowTime()))
			System.out.println("Testcase 3 Failed");
		else
			System.out.println("Testcase 3 Passed");

		// Initialize the show times for screen 2 as an array of strings
		showTimes = new String[]{"10:00", "13:00", "16:00", "19:00", "22:00"};
		// Set the show times to the screen 2 object
		screens[1].setShowTimes(showTimes);

		shows = screens[1].getShows();

		// Testcase 4
		if("16:00".equals(shows[2].getShowTime()))
			System.out.println("Testcase 4 Failed");
		else
			System.out.println("Testcase 4 Passed");		

		// Testcase 5: availableSeatsCount method get the total seats which are not booked yet.
		// It should initially be equal to the total seats.
		if(shows[0].availableSeatsCount() != 150)
			System.out.println("Testcase 5 Failed");
		else
			System.out.println("Testcase 5 Passed");

		// booking 4 tickets for the show 1 of screen 1
		String[] booking = {"A10", "A11", "A12", "A13"};
		shows[0].bookTickets(booking);

		// Testcase 6
		if(shows[0].availableSeatsCount() != 146)
			System.out.println("Testcase 6 Failed");
		else
			System.out.println("Testcase 6 Passed");

		// booking 8 tickets for the show 1 of screen 1
		booking = new String[]{"B10", "B11", "B12", "B13", "B14", "B15", "B16", "B17"};
		shows[0].bookTickets(booking);

		// Testcase 7
		if(shows[0].availableSeatsCount() != 138)
			System.out.println("Testcase 7 Failed");
		else
			System.out.println("Testcase 7 Passed");

		// booking 2 tickets for the show 2 of screen 1
		booking = new String[]{"C1", "C12"};
		shows[1].bookTickets(booking);

		// Testcase 8
		if(shows[1].availableSeatsCount() != 148)
			System.out.println("Testcase 8 Failed");
		else
			System.out.println("Testcase 8 Passed");

		//Testcase 9: showBookings should return an array of all bookings
		if(!java.util.Arrays.equals(shows[1].showAllBookings(), booking))
			System.out.println("Testcase 9 Failed");	
		else
			System.out.println("Testcase 9 Passed");			
		
		// create an object of inox with a parameterized constructor
		// that has screen count as its 1st argument, rows as its 2nd and columns as 3rd
		Cinema inox = new Cinema(2, 5, 10);		
		screens = inox.getAllScreens();
		

		// Initialize the show times for screen 1 as an array of strings
		showTimes = new String[]{"09:00", "12:00", "15:00", "18:00", "21:00"};
		
		// Set the show times to the screen 1 object		
		screens[0].setShowTimes(showTimes);
		
		shows = screens[0].getShows();

		// Testcase 10
		if(shows[0].availableSeatsCount() != 50)
			System.out.println("Testcase 10 Failed");
		else
			System.out.println("Testcase 10 Passed");
	}
}