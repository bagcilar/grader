
public class Component {

/*
 * Creates an component with a total weight that does not exceed 100%.
 *
 * Component identifies the component (e.g., Lab Test 1, Homework 2, Final, etc).
 * Weight is a percentage value that identifies the weight of this component in the course grade. 
 * Mark is a percentage value that identifies the grade earned for this component.
 * markAdded is a Boolean value that checks if a mark has already been entered for this component.
 *  
 */
	private String component;
	private double weight;
	private double mark;
	private double grade;
	private boolean markAdded;
	
	/*
	 * Creates a new component with component name and component weight
	 * Throws WeightViolationException if componentWeight is > 100 or < 1
	 * Initializes the markAdded boolean value to false
	 */
	public Component(String componentName, double componentWeight) throws WeightViolationException {
		
		if (componentWeight > 100 || componentWeight < 1) {
			throw new WeightViolationException("component weight cannot exceed 100%");
		}
		
		this.markAdded = false;
		this.component = componentName;
		this.weight = componentWeight;
	
	}
	
	/*
	 * This method sets the mark of a given component
	 * The mark is a value between 0 and 100, inclusive.
	 */
	public void setMark(double courseMark) {
		this.mark = courseMark;
		this.markAdded = true;
	}
	
	/*
	 * Retrieves the mark of a given component
	 */
	public double getMark() {
		return this.mark;
	}
	
	/*
	 * Returns true if a mark has been added for this component, false otherwise
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
	 * Retrieves the weight of a given component
	 */
	public double getWeight() {
		return this.weight;
	}
	
	/*
	 * Retrieves the component name of a given component
	 */
	public String getComponent() {
		return this.component;
	}
	

	/*
	 * Grades a given component using an obtained mark value and the component weight
	 * Outputs the total marks earned from an component.
	 * e.g., weight: 50%, mark: 100%, grade: 50 - means all 50 marks available from this component were earned
	 * e.g., weight: 50%, mark: 10%, grade: 5 - means 5 marks out of 50 for this component were earned
	 * e.g., weight: 60%, mark: 73%, grade: 43.8 marks - means 43.8 marks out of 60 for this component were earned 
	 */
	public double grader() {
		this.grade = (weight*mark)/100;
		return this.grade;
	}
	

		
}