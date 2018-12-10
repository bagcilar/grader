import java.util.Map;
import java.util.Scanner;

public class MainCourse {
	
	public static void main(String [] args) throws WeightViolationException, NullAssignmentException {
		
		System.out.println("Enter total number of components: ");
		
		Scanner numberScanner = new Scanner(System.in);
		int componentNumber = numberScanner.nextInt();
		
		Course myCourse = new Course("EECS2031", componentNumber);
		myCourse.weightCreator();
		
		for (Map.Entry<String, Component> entry : myCourse.componentList.entrySet()){
			System.out.println("component name: " + entry.getKey());
			System.out.println("component weight: " + entry.getValue().getWeight());
		}
		
		for (Map.Entry<String, Component> entry : myCourse.componentList.entrySet()){
			myCourse.markerConsole(myCourse.componentList.get(entry.getKey()));
			System.out.println("Accumulated so far: " + myCourse.currentAccumulated());
			System.out.println("Lost so far: " + myCourse.currentLost());

		}
		
		System.out.println("FINAL COURSE GRADE: " + myCourse.finalMarker());
		

		String status = "uninitialized";
		Scanner statusScanner = new Scanner(System.in);
		
		while (!status.startsWith("exit")) {
			
			System.out.println("Action: ");
			status = statusScanner.nextLine();
			
			if(status.startsWith("remark")) {
				while(status.endsWith("remark")) {
					System.out.println("Specify a component to be remarked: ");
					status = statusScanner.nextLine();
				}
				String remarkedComponent = status.substring(7, status.length());
				myCourse.remarker(remarkedComponent);
				System.out.println("UPDATED FINAL COURSE GRADE: " + myCourse.finalMarker());
			
			}else if (status.startsWith("summary")) {
				myCourse.printSummary();
				
			}else if (status == "exit") {
				myCourse.closeScanners();
				return;	
			}	

		}
		
		System.out.println("Exited");



		

		
		
	}

}