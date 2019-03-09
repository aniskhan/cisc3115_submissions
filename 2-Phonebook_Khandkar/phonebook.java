/*Anisa Khandkar
CISC-3115: HW2
Due: 14 MAR 19*/
import java.util.Arrays;
import java.util.Scanner;

public class phonebook {
	//init with some data
	static String[] arrNames = {"Foo","Bar"};
	static String[] arrPhoneNumbers = {"555-1234","555-5678"};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		showIntroduction();
		while(true) {
			String input = sc.nextLine();
			if (input.equalsIgnoreCase("-q")) {//check if user wants to quit
				sc.close();
				System.out.println("\n So long and thanks for all of the fish!");
				System.exit(0);
			} else {
				routeInput(input);
			}
		}
	}
	static void showIntroduction(){
		String txtIntro = "Welcome to Anisa's phonebook app! \n"
				+ "Enter '-help' for supported functions.\n"
				+ "Enter '-q' to quit.";
		System.out.println(txtIntro);
	}
	static void routeInput(String someInput) {
		String [] splitInput = someInput.split(" "); // split on space
		switch (splitInput[0]) {
			case "-all":
				showAllEntries();
				break;
			case "-help":
				showHelp();
				break;
			// the following cases are positioned to allow
			// access to default error message upon failure
			case "-add":
				if (splitInput.length > 2) {//req 2 args: name/number
						addEntry(splitInput[1],splitInput[2]);
					break;
				}
			case "-nlu": //lookup name from phone number
				if (splitInput.length > 1) { //req 1 arg
					getShowName (splitInput[1]);
					break;
				}
			case "-plu": // lookup phone number for name
				if (splitInput.length > 1) { //req 1 arg
						getShowNumber (splitInput[1]);
						break;
					}
			default:
				errMessage(0);
		}		
	}
	static void addEntry(String someName, String someNumber){
		//copy each array, and make new array 1 bigger
		arrNames = Arrays.copyOf(arrNames, arrNames.length + 1);
		arrPhoneNumbers = Arrays.copyOf(arrPhoneNumbers, arrPhoneNumbers.length + 1);
		// add new data
		arrNames[arrNames.length - 1] = someName;
		arrPhoneNumbers[arrPhoneNumbers.length - 1] = someNumber;
		showAllEntries();
	}
	static void showAllEntries(){
		int ctEntries = arrNames.length;
		int i = 0;
		while (i<ctEntries) {
			System.out.printf("%s\t%s%n",arrNames[i],arrPhoneNumbers[i]);
			i++;
		}
	}
	static void getShowName(String someNumber) {
		int someIndex = 
				java.util.Arrays.asList(arrPhoneNumbers).indexOf(someNumber);
		if (someIndex == -1) {//if element doesn't exist, -1 is rtn
			errMessage(1);
		}else {
			//print first match found
			System.out.println(arrNames[someIndex]);
			//check for & print more matches (persons can share a number)
			someIndex++;
			while(someIndex < arrPhoneNumbers.length) {
				if (arrPhoneNumbers[someIndex].equals(someNumber)) {
					System.out.println(arrNames[someIndex]);
				}
				++someIndex;
			}
		}
		
	}
	static void getShowNumber(String someName) {
		int someIndex = 
				java.util.Arrays.asList(arrNames).indexOf(someName);
		if (someIndex == -1) {//if element doesn't exist, -1 is rtn
			errMessage(1);
		}else {
			//print first match found
			System.out.println(arrPhoneNumbers[someIndex]);
			//check for & print more matches (person can have multiple numbers)
			someIndex++;
			while(someIndex < arrNames.length) {
				if (arrNames[someIndex].equals(someName)) {
					System.out.println(arrPhoneNumbers[someIndex]);
				}
				++someIndex;
			}	
		}
	}
	static void showHelp(){
		String txtHelp = "-add <lastName> <phoneNumber>\n"
				+ "\t create new phone book listing; \n"
				+ "\t NOTE: spaces are *not* supported in names or numbers\n"
				+ "-all\n"
				+ "\t show all phonebook listings\n"
				+ "-help\n"
				+ "\t print this help message\n"
				+ "-nlu <phoneNumber>\n"
				+ "\t show name associated with <phoneNumber>\n"
				+ "-plu <lastName>\n"
				+ "\t show phone number associated with <lastName>\n"
				+ "-q\n"
				+ "\t quit application \n";
		System.out.println(txtHelp);
	}
	static void errMessage (int someErrID) {
		switch (someErrID) {
		 	case 0:
		 		System.err.println("Unrecognized Option");
		 		break;
		 	case 1:
		 		System.err.println("No entry Found");
		 		break;
		}
	}
}
