import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.PrintWriter;
import java.util.Scanner;
public class studentHighLowGrade {

	
	static int ctStudents = 10; // can ingest up to 24 students per input file
	static int ctFileFields = 11; // file has student name + 10 pre-filled grades
	static String studentWHighAverage;
	static double highAverage = 0;
	static String studentWLowAverage;
	static double lowAverage = 100;
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		int counterStudent = 0;
		System.out.println("Exam scores for a group of students have been "
				+ "loaded to this application. Your selection will return each "
				+ "student's average grade thru the input exam number.\n");
		System.out.println("How many exams do you want to average? (Max 10)");
		//max 10 grades per student preloaded input file
		//int ctExams = 10;
		int ctExams = sc.nextInt();
		sc.close();
		Scanner inputFile;
		//PrintWriter outputFile;
		inputFile = new Scanner
				(new FileReader("./input/studentGrades.txt"));
				// tab-delimited
		//outputFile = new PrintWriter ("output.txt");
		while (counterStudent < ctStudents){
			//parse file for each line / student record
			String oneLine = inputFile.nextLine();
			String oneLineArr[] = oneLine.split("\t", ctFileFields);
			String studentName = oneLineArr[0];
			double avgScore = avgNumericGrade(oneLineArr, ctExams);
			System.out.printf("Student: %-10s\t Average: %3.1f\t Grade: %C %n"
					,studentName, avgScore, letterGrade(avgScore));
			counterStudent ++;
			if (avgScore > highAverage) {
				highAverage = avgScore;
				studentWHighAverage = studentName;	
			}
			if (avgScore < lowAverage) {
				lowAverage = avgScore;
				studentWLowAverage = studentName;	
			}
		}
		
		inputFile.close();
		System.out.println("---");
		System.out.printf("Highest: %-10s\t Average: %3.1f\t Grade: %C %n"
				,studentWHighAverage, highAverage, letterGrade(highAverage));
		System.out.printf("Lowest: %-10s\t Average: %3.1f\t Grade: %C %n"
				,studentWLowAverage, lowAverage, letterGrade(lowAverage));
		//outputFile.close();
	}
	public static double avgNumericGrade (String [] arrGrades, int ctExams) {
		double sum = 0;
		
		for(int i = 1; i <= ctExams; i++) {
			sum = sum + Double.parseDouble(arrGrades[i]);
		}
		return sum / ctExams;
	}
	
	public static char letterGrade (double someNumericGrade) {
		//the strictest grading possible, no rounding :)
		switch ((int)someNumericGrade / 10) {
		case 10:
			return 'A';
		case 9:
			return 'A';
		case 8:
			return 'B';
		case 7:
			return 'C';
		case 6:
			return 'D';
		default:
			return 'F';
		}

	}
}
