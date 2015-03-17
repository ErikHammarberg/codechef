import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class main {

	public static void main(String[] args) throws NumberFormatException,
			IOException {

		new DinnerReader().readInput2();
	}
}

class DinnerReader {

	int[] amountArray;
	int[] valueArray;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
			System.out));
	final String alfabet = "abcdefghijklmnopqrstuvwxyz";
	String order;
	int targetValue;
	HashSet<Solution> solutionList;
	TreeSet<Solution> completed;

	public void readInput2() throws NumberFormatException, IOException {
		int noGuests = Integer.parseInt(reader.readLine());
		for (int i = 0; i < noGuests; i++) {
			completed = new TreeSet<Solution>();
			solutionList = new HashSet<Solution>();

			String[] values = reader.readLine().split(" ");
			order = reader.readLine();
			targetValue = Integer.parseInt(reader.readLine());
			solutionList.add(new Solution(targetValue));
			extractIngredients(values);
			if (completed.size() > 0) {
				System.out.println(completed.first().ingredients);
			} else {
				System.out.println("IMPOSSIBLE");
			}
		}
	}

	public void extractIngredients(String[] values) throws IOException {

		int noIngr = Integer.parseInt(values[0]);
		amountArray = new int[noIngr];
		valueArray = new int[noIngr];
		for (int i = 1; i <= noIngr; i++) {
			int value = Integer.parseInt(values[i]);
			valueArray[i - 1] = value;

		}
		// Here starts the Heavy Hauling

		for (char ingred : order.toCharArray()) {
			int index = alfabet.indexOf(ingred);
			int value = valueArray[index];
			addToSolutions(value, ingred);
		}
	}

	private void addToSolutions(int value, char ingred) {
		// TODO Auto-generated method stub
		LinkedList<Solution> tempList = new LinkedList<Solution>();
		for (Iterator<Solution> it = solutionList.iterator(); it.hasNext();) {
			Solution s = it.next();
			Solution old = s.add(value, ingred);
			if (old != null) {
				if (s.isComplete) {
					completed.add(s);
					it.remove();
				}
				tempList.add(old);
			}
		}
		solutionList.addAll(tempList);

	}

	public void prettyPrint() {
		for (int i = 0; i < amountArray.length; i++) {
			System.out.print("Ingredient: " + alfabet.charAt(i));
			System.out.print(" Has value: " + valueArray[i]);
			System.out.println(" And amount: " + amountArray[i]);
		}
	}

	class Solution implements Comparable<Solution> {
		public int remainder;
		// public LinkedList<Character> ingredients;
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
			if (remainder == 0)
				isComplete = true;
			return temp;
		}

		@Override
		public int compareTo(Solution arg0) {
			// TODO Auto-generated method stub
			return this.ingredients.compareTo(arg0.ingredients);
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof Solution) {
				return ingredients.equals(((Solution) other).ingredients);
			}
			return false;
		}

		@Override
		public int hashCode() {
			return ingredients.hashCode();
		}
	}
}
