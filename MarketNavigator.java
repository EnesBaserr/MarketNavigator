
package question;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.io.*;
import java.security.PublicKey;

import javax.sound.midi.Soundbank;
import javax.sql.rowset.JoinRowSet;

import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.w3c.dom.css.Counter;

import java.util.*;
import java.util.jar.Attributes.Name;

public class MarketNavigator
{  
	
	/* Method that gives the distance between two points */
	public static double distanceFinder(int x1, int y1, int x2, int y2) {
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}
	static Double minDistance;
	//declaring minDistance to use compare distances each other.
	private static double swap(ArrayList<House> permutation, int i, int j,int size,House migrosHouse) {		
		Collections.swap(permutation, i, j);
		//exchange between indexes in ArrayList
			double distance=0;
			for (int t = 0; t < permutation.size() - 1; t++) {
				// this loop calculate distance  along for every permutated arrayList. 
				 House house1 = permutation.get(t);
				 House house2 = permutation.get(t+1);
				 distance += distanceFinder(house1.getX(), house1.getY(), house2.getX(),house2.getY());
			 }
			distance += distanceFinder(migrosHouse.getX(), migrosHouse.getY(), permutation.get(0).getX(), permutation.get(0).getY());
			//adding distance migros and first city in permutated arrayList.
			 distance += distanceFinder(migrosHouse.getX(), migrosHouse.getY(), permutation.get(permutation.size()-1).getX(), permutation.get(permutation.size()-1).getY());
			 ////adding distance migros and last city in permutated arrayList.
			 if (distance < minDistance) {
				 minDistance = distance;
				 //to update every step if new distance is smaller than previous ones.
			 }	
		return minDistance;	 
		//return statement
	}	
	 private static void findPerm(ArrayList<House> permutation, int currentIndex,int size,House migrosHouse) {
		 
		 //if (currentIndex == permutation.size() - 1) {
			 //to test permutated ArrayLists.
			// printHouseArray(permutation);					 	 
		 //}			 
		 for (int i = currentIndex; i < permutation.size(); i++)
	        {		 
	            swap(permutation, currentIndex, i,size,migrosHouse);	            	            
	            findPerm(permutation, currentIndex + 1,size,migrosHouse);
	            swap(permutation, currentIndex, i,size,migrosHouse);    
	            //recursive methods to change ArrayList every recursion.
	            	
	        }
		 }		 	 	 
	 public static void permMain(ArrayList<House> permutation,int currentIndex,int size,House migrosHouse) {		 	    	 
	        findPerm(permutation,currentIndex,size,migrosHouse);
	        // main method that orients two method used above.
	    }	 
	 /*public static void printHouseArray(ArrayList<House> houses) {
		 for (House house : houses) {
			 System.out.println(house.toString());
		 }
	 }	
	 */// to test updated ArrayList containing objects.
	public static int pathFinder(String filename) {
		
		/* Find the smallestTotalDistance */
		double smallestTotalDistance = 0;
		
		
		//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
				
		ArrayList<House> houses = new ArrayList<House>();
		// to store objects in ArrayList.
		try {
			FileInputStream fis = new FileInputStream(filename);
			Scanner sc = new Scanner(fis);
			String[] migrosArr = sc.nextLine().split(" ");	
			// to create an array of an migros.
			House migros = new House(migrosArr[0], Integer.parseInt(migrosArr[1]), Integer.parseInt(migrosArr[2]));
			// to create migros object through array that we created above.
			while(sc.hasNextLine()) {
				String line =(sc.nextLine());
				String[] tokens = line.split(" ");	
				// to create an array of an every line using split.
				House house = new House(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
				// to create object through arrays that we created above.
				houses.add(house);		
				// storing objects.
								
			}
			
			sc.close();			
			minDistance = Double.MAX_VALUE;			
			permMain(houses, 0,houses.size(),migros);
			// calling method.
			smallestTotalDistance = minDistance;		
		}			
		 catch (IOException ex) {
			 
		}
		//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
		
		int roundedValue = (int) Math.round(smallestTotalDistance);
		return roundedValue;
		
	}

	public static void main(String[] args) {
		
		/* This part is for you to test your method, no points will be given from here */
		String path = MarketNavigator.class.getProtectionDomain().getCodeSource().getLocation().getPath() + File.separator + ".." + File.separator+"coordinates.txt";
		int distance = pathFinder("coordinates.txt");
		System.out.println("Smallest distance:" + distance);
	}
	
}  

