import java.util.*;
import java.io.*;

public class DrugInteractions
{
	public static void main( String[] args ) throws Exception
	{
		BufferedReader foodDrug2CategoryFile = new BufferedReader( new FileReader( "foodDrug2Category.txt" ) );
		BufferedReader patient2FoodDrugFile = new BufferedReader( new FileReader( "patient2FoodDrug.txt") );
		BufferedReader dontMixFile = new BufferedReader( new FileReader( "dontMix.txt" ) );

    TreeMap<String, TreeSet<String>> foodDrug2Category = new TreeMap<String, TreeSet<String>>();
    TreeMap<String, TreeSet<String>> patient2FoodDrug = new TreeMap<String, TreeSet<String>>();

		//step 1
    while(foodDrug2CategoryFile.ready()){
      String line = foodDrug2CategoryFile.readLine();
      String tokens[] = line.split(",");

			TreeSet<String> food = new TreeSet<String>();
      for(int i = 1; i < tokens.length ; i++ ) {
				food.add(tokens[i]);
      }
			foodDrug2Category.put(tokens[0], food);
    }
		for (String keys : foodDrug2Category.keySet() ) {
			System.out.print(keys);
			for (String foods: foodDrug2Category.get(keys) ) {
					System.out.print( " " + foods);
				}
				System.out.println();
      }
			//step 2
			while(patient2FoodDrugFile.ready()) {
				String line = patient2FoodDrugFile.readLine();
				String tokens[] = line.split(",");

				TreeSet<String> patientFood = new TreeSet<String>();
				for (int i = 1; i < tokens.length ; i++ ) {
					patientFood.add(tokens[i]);
				}
				patient2FoodDrug.put(tokens[0], patientFood);
			}
			System.out.println();
			for (String keys : patient2FoodDrug.keySet() ) {
				System.out.print(keys);
				for (String foods: patient2FoodDrug.get(keys) ) {
						System.out.print( " " + foods);
					}
					System.out.println();
	      }
				System.out.println();

				//step 3
				while(dontMixFile.ready()){
					String[] tokens = new String[2];
					String line = dontMixFile.readLine();
					tokens = line.split(",");
					TreeSet<String> values = new TreeSet<String>();
					TreeSet<String> values2 = new TreeSet<String>();

							String key = tokens[0];
							String key2 = tokens[1];
							values = foodDrug2Category.get(key);
							values2 = foodDrug2Category.get(key2);
							//for Thinners values are
							//coumadin,plavix
							for (String keys : patient2FoodDrug.keySet() ) {
								for (String med : patient2FoodDrug.get(keys)) {
										if(values.contains(med) && values2.contains(med)) System.out.println(keys);
						}
				}
			}



	} // END MAIN

} // END CLASS
