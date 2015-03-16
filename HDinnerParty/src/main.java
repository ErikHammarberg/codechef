import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;


class main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		

		new DinnerReader().readInput();
	}
}


class DinnerReader {
	
	int[] amountArray;
	int[] valueArray;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	final String alfabet = "abcdefghijklmnopqrstuvwxyz";
	int targetValue;
	LinkedList<Solution> solutionList = new LinkedList<Solution>();
	
	
	public void readInput2() throws NumberFormatException, IOException{
		solutionList.add(new Solution(targetValue));
		int noGuests = Integer.parseInt(reader.readLine());
		for(int i = 0;i<noGuests;i++) {
			String[] values = reader.readLine().split(" ");
			String order = reader.readLine();
			targetValue = Integer.parseInt(reader.readLine());
			
			generatePatterns(values);
			
		}
	}
	
	private void generatePatterns(String[] values) {
		int remainder = targetValue;
		
		
		
	}

	public void readInput() throws NumberFormatException, IOException{
		int noGuests = Integer.parseInt(reader.readLine());
		for(int i = 0;i<noGuests;i++) {
			extractIngredients();
//			prettyPrint();
			Solution sol = findCombo(targetValue);
			if(sol != null) {
				for(Integer j : sol.ingredients){
					System.out.print(alfabet.charAt(j));
				}
				System.out.print("\n");
			} else {
				System.out.println("IMPOSSIBLE");
			}
		}

	}
	public void extractIngredients() throws IOException{

		String[] values = reader.readLine().split(" ");
		
		int noIngr = Integer.parseInt(values[0]);
		amountArray = new int[noIngr];
		valueArray = new int[noIngr];
		for(int i = 1; i <= noIngr; i++){
			int value = Integer.parseInt(values[i]);
			valueArray[i-1] = value;
			
		}
		// Here starts the Heavy Hauling
		String order = reader.readLine();
		targetValue = Integer.parseInt(reader.readLine());
		for(char ingred : order.toCharArray()) {
			int value = valueArray[alfabet.indexOf(ingred)];
			addToSolutions(value, ingred);
		}
	}
	private void addToSolutions(int value, char ingred) {
		// TODO Auto-generated method stub
		LinkedList<Solution> tempList = new LinkedList<Solution>();
		for(Solution s : solutionList) {
			Solution old = s.add(value, ingred);
			if(old != null) {
				if(s.isComplete){
					
				}
				tempList.add(old);
			}
		}
		
	}

	public void prettyPrint () {
		for(int i = 0; i<amountArray.length; i++){
			System.out.print("Ingredient: " +alfabet.charAt(i));
			System.out.print(" Has value: " +valueArray[i]);
			System.out.println(" And amount: " + amountArray[i]);
		}
	}
	public Solution findCombo(int targetValue) {
		LinkedList<Solution> solutionList = new LinkedList<Solution>();
		solutionList.add(new Solution(targetValue));
		
		for (int i = 0; i < valueArray.length; i++) {
			int remainsCurrent = amountArray[i];
			while (remainsCurrent > 0) {
				LinkedList<Solution> tempList = new LinkedList<Solution>();
				for(Solution s : solutionList) {
					Solution old = s.add(valueArray[i], i);
					if(old != null) {
						if(s.isComplete){
							return s;
						}
						tempList.add(old);
					}
				}
				solutionList.addAll(tempList);
				remainsCurrent--;
			}
		}
		return null;
	}
}

class Solution implements Comparable<Solution>{
	public int remainder;
//	public LinkedList<Character> ingredients;
	String ingredients;
	boolean isComplete;
	
	public Solution(int remainder) {
		this.remainder = remainder;
		ingredients = "";
	}
	public Solution(Solution other) {
		remainder = other.remainder;
		ingredients = new String(other.ingredients);
	}
	public Solution add(int value, char position) {
		if (value > remainder)
			return null;
		Solution temp = new Solution(this);
		remainder -= value;
		ingredients += position;
		if(remainder == 0)
			isComplete = true;
		return temp;
	}
	@Override
	public int compareTo(Solution arg0) {
		// TODO Auto-generated method stub
		return this.ingredients.compareTo(arg0.ingredients);
	}
	@Override
	public boolean equals(Solution other) {
		return ingredients.equals(other.ingredients);
	}
	
}
