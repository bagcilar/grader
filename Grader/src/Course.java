import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Course {
	
	/*
	 * Create a course class for each course you take, with total # of assignments as the parameter.
	 * Each assignment needs to be assigned a weight, not exceeding 100%, individually and collectively.
	 * Grade is initially set to -1
	 * Count signifies the number of assignments in the created course
	 * The map contains a map of all assignments added to this course.
	 * The keys are assignment names (component) and the values are Assignment objects
	 */
	
	private double grade = 0;
	public int count;
	String name;
	boolean hasFinal = false;
	
	Map <String, Assignment> assignmentList = new LinkedHashMap<String, Assignment>();

	/*
	 * Creates a course with the number of assignments this course has
	 */
	public Course(String name, int assignmentCount) {
		this.count = assignmentCount;
		this.name = name;
	}
	
	/*
	 *creates all the assignments and adds them to the map of this course

	 */
	
	public void weightCreator() throws WeightViolationException {
		
		double totalWeight = 0;
		double weight = 0;
		Scanner nameScanner = null;
		Scanner weightScanner = null;
		
		/*
		 * Obtains the names and weights of all assignments to be added to this course from the user
		 */
		for(int i = 1; i <= this.count; i++) {
			System.out.println("Enter name for assignment " + i + ":");
			nameScanner = new Scanner(System.in);
			String name = nameScanner.nextLine();
			
			System.out.println("Enter weight of assignment " + i + ":");
			weightScanner = new Scanner(System.in);
			weight = weightScanner.nextDouble();
			
			if (weight + totalWeight == 100) {
				System.out.println("All assignments successfully added");
				totalWeight = totalWeight + weight;
				assignmentList.put(name, new Assignment(name, weight));
				/*weightScanner.close();
				nameScanner.close();*/
				return;
			}
			
			//if an assignment weight's addition causes the total weight to exceed 100, the user is prompted for another entry
			while (weight + totalWeight > 100) {
				System.out.println("Enter weight of assignment " + i + ". Total weight cannot exceed 100%");
				weight = weightScanner.nextDouble();	
				if (weight + totalWeight == 100) {						
					System.out.println("All assignments successfully added");
					totalWeight = totalWeight + weight;
					assignmentList.put(name, new Assignment(name, weight));
				/*	weightScanner.close();
					nameScanner.close();*/
					return;
				}
							
			}
			
			//If all assignments have been added and the total weight is not 100, prompt the user to reenter the last weight
			while ((i == this.count) && (weight + totalWeight < 100)) {
				System.out.print("It appears you have made a mistake. The total weight doesn't add up to 100%. Reenter weight: ");
				weight = weightScanner.nextDouble();
				if (weight + totalWeight == 100) {
					System.out.println("All assignments successfully added");
					totalWeight = totalWeight + weight;
					assignmentList.put(name, new Assignment(name, weight));
		/*			weightScanner.close();
					nameScanner.close();*/
					return;
				}
			}
			
			
			totalWeight = totalWeight + weight;
			assignmentList.put(name, new Assignment(name, weight));	
			
		}
		
		//initializes hasFinal	
		if (this.assignmentList.containsKey("final")) {
			this.hasFinal = true;
		}
		
	}
	
	public void markerConsole(Assignment assignment) throws NullAssignmentException {
		Double mark;
		System.out.println("Enter mark for " + assignment.getComponent() + ": ");
		Scanner markScanner = new Scanner(System.in);
		mark = markScanner.nextDouble();
	
//		while (mark < 0 || mark > 100) {
//			System.out.print("Please enter a mark between 0 and 100, inclusive ");
//			mark = markScanner.nextDouble();
//		}
		
		while (mark < 0) {
			System.out.print("Mark has to be greater than 0");
			mark = markScanner.nextDouble();
		}
		
		if (assignment.getMarkStatus()) {
			System.out.println("Warning: This assignment already has a mark");
		}
		
		assignment.setMark(mark);
		
	}

	/*
	 * Provides a grade for this course.
	 * For each assignment in this course, 
	 */
	public double courseGrader() {
		for (Map.Entry<String, Assignment> entry : this.assignmentList.entrySet()){
			if (!entry.getValue().getMarkStatus()) {
				System.out.println("Warning: No mark has been yet added for " + entry.getKey());
			}
			grade += entry.getValue().grader();			
		}
		return grade;
	}
	
	/*
	 * returns the grade earned for this course
	 */
	public double getGrade() {
		return this.grade;
	}
	
	/*
	 * 	current percentage accumulated
	 */
	public double currentAccumulated() {
		double currentProgress = 0;
		for (Map.Entry<String, Assignment> entry : this.assignmentList.entrySet()){
			if(entry.getValue().getMarkStatus()) {
				currentProgress += entry.getValue().grader();
			}
		}
		return currentProgress;
	}
	
	/*
	 * 	current percentage lost
	 */
	public double currentLost() {
		double currentLost = 0;
		for (Map.Entry<String, Assignment> entry : this.assignmentList.entrySet()){
			if(entry.getValue().getMarkStatus()) {
				currentLost += (entry.getValue().getWeight() - entry.getValue().grader());
			}
		}
		return currentLost;
	}
	
	
	public void finalEstimator() {
		
	}
	
}
