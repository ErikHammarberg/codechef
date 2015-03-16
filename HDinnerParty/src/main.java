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
			amountArray[alfabet.indexOf(ingred)]++;
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

class Solution {
	public int remainder;
	public LinkedList<Integer> ingredients;
	boolean isComplete;
	
	public Solution(int remainder) {
		this.remainder = remainder;
		ingredients = new LinkedList<Integer>();
	}
	public Solution(Solution other) {
		remainder = other.remainder;
		ingredients = new LinkedList<Integer>(other.ingredients);
	}
	public Solution add(int value, int position) {
		if (value > remainder)
			return null;
		Solution temp = new Solution(this);
		remainder -= value;
		ingredients.add(position);
		if(remainder == 0)
			isComplete = true;
		return temp;
	}
	
}
