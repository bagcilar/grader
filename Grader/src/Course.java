import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Course {
	
	/*
	 * Create a course class for each course you take, with total # of components as the parameter.
	 * Each component needs to be assigned a weight, not exceeding 100%, individually and collectively.
	 * Grade is initially set to -1
	 * Count signifies the number of components in the created course
	 * The map contains a map of all components added to this course.
	 * The keys are component names (component) and the values are component objects
	 */
	
	private double grade = 0;
	public int count;
	String name;
	Map <String, Component> componentList = new LinkedHashMap<String, Component>();
	Scanner stringScanner = new Scanner (System.in);
	Scanner doubleScanner = new Scanner (System.in);
	/*
	 * Creates a course with the number of components this course has
	 */
	public Course(String name, int componentCount) {
		this.count = componentCount;
		this.name = name;
	}
	
	/*
	 *creates all the components and adds them to the map of this course

	 */
	
	public void weightCreator() throws WeightViolationException {
		
		double totalWeight = 0;
		double weight = 0;
		
		/*
		 * Obtains the names and weights of all components to be added to this course from the user
		 */
		for(int i = 1; i <= this.count; i++) {
			System.out.println("Enter name for component " + i + ":");
			String name = stringScanner.nextLine();
			while(componentList.containsKey(name)) {
				System.out.println("There already exists a component with this name. Reenter name: ");
				name = stringScanner.nextLine();
			}
			
			System.out.println("Enter weight of component " + name + ":");
			weight = doubleScanner.nextDouble();
			
			if (weight + totalWeight == 100) {
				totalWeight = totalWeight + weight;
				System.out.println("All components successfully added");
				componentList.put(name, new Component(name, weight));
				return;
			}
			
			//if an component weight's addition causes the total weight to exceed 100, the user is prompted for another entry
			while (weight + totalWeight > 100) {
				System.out.println("Enter weight of component " + name + ". Total weight cannot exceed 100%");
				weight = doubleScanner.nextDouble();	
				if (weight + totalWeight == 100) {						
					System.out.println("All components successfully added");
					totalWeight = totalWeight + weight;
					componentList.put(name, new Component(name, weight));
					//weightScanner.close();
					//nameScanner.close();
					return;
				}
							
			}
			
			//If all components have been added and the total weight is not 100, prompt the user to reenter the last weight
			while ((i == this.count) && (weight + totalWeight < 100)) {
				System.out.print("The total weight doesn't add up to 100%. Reenter weight: ");
				System.out.print("It is currently at " + totalWeight);
				weight = doubleScanner.nextDouble();
				if (weight + totalWeight == 100) {
					System.out.println("All components successfully added");
					totalWeight = totalWeight + weight;
					componentList.put(name, new Component(name, weight));
		/*			weightScanner.close();
					nameScanner.close();*/
					return;
				}
			}
			
			
			totalWeight = totalWeight + weight;
			componentList.put(name, new Component(name, weight));	
			
		}
		
	}
	
	//Marks a given component
	public void markerConsole(Component component) throws NullAssignmentException {
		Double mark;
		System.out.println("Enter mark for " + component.getComponent() + ": ");
		mark = doubleScanner.nextDouble();
		
		while (mark < 0) {
			System.out.print("Mark has to be greater than 0");
			mark = doubleScanner.nextDouble();
		}
		
		if (component.getMarkStatus()) {
			System.out.println("Warning: This component already has a mark");
		}
		
		component.setMark(mark);
		
	}
	
	//Remarks a given component
	public void remarker(String remarkedComponent) throws NullAssignmentException {
		
		while (!componentList.containsKey(remarkedComponent)) {
			System.out.println(remarkedComponent + " not found. Reenter component name: ");
			remarkedComponent = stringScanner.nextLine();
		}
		
		Component requestedComponent = componentList.get(remarkedComponent);
		Double oldMark = requestedComponent.getMark();

		System.out.println("Enter new mark for " + remarkedComponent + ": ");
		Double newMark = doubleScanner.nextDouble();
					
		while (newMark < 0) {
			System.out.print("Mark has to be greater than 0");
			newMark = doubleScanner.nextDouble();
		}
		componentList.get(remarkedComponent).setMark(newMark);
		System.out.println(remarkedComponent + "'s mark has been changed from " + oldMark + " to " + newMark);
	}


	/*
	 * Provides a grade for each component in this course
	 */
	public double finalMarker() {
		grade = 0;
		for (Map.Entry<String, Component> entry : this.componentList.entrySet()){
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
		for (Map.Entry<String, Component> entry : this.componentList.entrySet()){
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
		for (Map.Entry<String, Component> entry : this.componentList.entrySet()){
			if(entry.getValue().getMarkStatus()) {
				currentLost += (entry.getValue().getWeight() - entry.getValue().grader());
			}
		}
		return currentLost;
	}
	
	public void printSummary() {
		for (Map.Entry<String, Component> entry : componentList.entrySet()){
			System.out.println(entry.getKey() + ": " + entry.getValue().getMark());
		}
		System.out.println("FINAL COURSE GRADE: " + finalMarker());
	}
	
	
	public void closeScanners () {
		stringScanner.close();
		doubleScanner.close();
	}

	
}
