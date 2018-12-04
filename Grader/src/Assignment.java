
public class Assignment {

/*
 * Creates an assignment with a total weight that does not exceed 100%.
 *
 * Component identifies the assignment (e.g., Lab Test 1, Homework 2, Final, etc).
 * Weight is a percentage value that identifies the weight of this assignment in the course grade. 
 * Mark is a percentage value that identifies the grade earned for this assignment.
 * markAdded is a Boolean value that checks if a mark has already been entered for this assignment.
 *  
 */
	private String component;
	private double weight;
	private double mark;
	private double grade;
	private boolean markAdded;
	
	/*
	 * Creates a new assignment with component name and assignment weight
	 * Throws WeightViolationException if assignmentWeight is > 100 or < 1
	 * Initializes the markAdded boolean value to false
	 */
	public Assignment(String componentName, double assignmentWeight) throws WeightViolationException {
		
		if (assignmentWeight > 100 || assignmentWeight < 1) {
			throw new WeightViolationException("Assignment weight cannot exceed 100%");
		}
		
		this.markAdded = false;
		this.component = componentName;
		this.weight = assignmentWeight;
	
	}
	
	/*
	 * This method sets the mark of a given assignment
	 * The mark is a value between 0 and 100, inclusive.
	 */
	public void setMark(double courseMark) {
		this.mark = courseMark;
		this.markAdded = true;
	}
	
	/*
	 * Retrieves the mark of a given assignment
	 */
	public double getMark() {
		return this.mark;
	}
	
	/*
	 * Returns true if a mark has been added for this assignment, false otherwise
	 */
	public Boolean getMarkStatus() {
		return this.markAdded;
	}
	
	/*
	 * Sets the mark status to true
	 */
	public void setMarkStatus() {
		this.markAdded = true;
	}
	
	/*
	 * Sets the mark status to false
	 */
	public void removeMarkStatus() {
		this.markAdded = false;
	}
	
	/*
	 * Retrieves the weight of a given assignment
	 */
	public double getWeight() {
		return this.weight;
	}
	
	/*
	 * Retrieves the component name of a given assignment
	 */
	public String getComponent() {
		return this.component;
	}
	

	/*
	 * Grades a given assignment using an obtained mark value and the assignment's weight
	 * Outputs the total marks earned from an assignment.
	 * e.g., weight: 50%, mark: 100%, grade: 50 - means all 50 marks available from this assignment were earned
	 * e.g., weight: 50%, mark: 10%, grade: 5 - means 5 marks out of 50 for this assignment were earned
	 * e.g., weight: 60%, mark: 73%, grade: 43.8 marks - means 43.8 marks out of 60 for this assignment were earned 
	 */
	public double grader() {
		this.grade = (weight*mark)/100;
		return this.grade;
	}
	

		
}