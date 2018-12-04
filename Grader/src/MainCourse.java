import java.util.Map;
import java.util.Scanner;

public class MainCourse {
	
	public static void main(String [] args) throws WeightViolationException, NullAssignmentException {
		
		System.out.println("Enter total number of assignments: ");
		
		Scanner numberScanner = new Scanner(System.in);
		int assignmentNumber = numberScanner.nextInt();
		
		Course myCourse = new Course("EECS2031", assignmentNumber);
		myCourse.weightCreator();
		
		for (Map.Entry<String, Assignment> entry : myCourse.assignmentList.entrySet()){
			System.out.println("Assignment name: " + entry.getKey());
			System.out.println("Assignment weight: " + entry.getValue().getWeight());
		}
		
		for (Map.Entry<String, Assignment> entry : myCourse.assignmentList.entrySet()){
			myCourse.markerConsole(myCourse.assignmentList.get(entry.getKey()));
			System.out.println("Accumulated so far: " + myCourse.currentAccumulated());
			System.out.println("Lost so far: " + myCourse.currentLost());

		}

		System.out.println("FINAL COURSE GRADE: " + myCourse.courseGrader());
		
	}

}