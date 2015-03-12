import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		new DinnerReader().readInput();
	}
}


class DinnerReader {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	Map<Character, Ingredient> ingredients = new TreeMap<Character,Ingredient>();
	final String alfabet = "abcdefghijklmnopqrstuvwxyz";
	
	public void readInput() throws NumberFormatException, IOException{
		int noGuests = Integer.parseInt(reader.readLine());
		System.out.println("no guests: "+noGuests);

		
		for(String s : reader.readLine().split(" ")){
			
		}
	}
	public void extractIngredients() throws IOException{

		String[] values = reader.readLine().split(" ");
		
		int noIngr = Integer.parseInt(values[0]);
		for(int i = 1; i <= noIngr; i++){
			char name = alfabet.charAt(i);
			int value = Integer.parseInt(values[i]);
			Ingredient ing = new Ingredient(""+name, value, 0);
			ingredients.put(name,ing );
		}
		/*String[] names = reader.readLine().split(" ");
		for (int i = 0; i < names.length ; i++){
			
		}*/
	}

}

class Ingredient {
	public final String name;
	public final int proteinCount;
	public final int quantity;
	public Ingredient(String n, int pC, int q){
		name = n;
		proteinCount = pC;
		quantity = q;
	}
}
