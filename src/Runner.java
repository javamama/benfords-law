import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Runner {

	public static void main(String[] args){
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("1. Random Numbers");
		System.out.println("2. Randon Numbers with Random Ranges");
		System.out.println("3. Enter Own Data");
		
		int e = s.nextInt();
		if(e == 1){
			Random numGen = new Random();
			int a = 10000;
			int[] numbers = new int[a];
			for(int i = 0; i < a; i++){
				numbers[i] = numGen.nextInt(numGen.nextInt(numGen.nextInt(999999))+ 1) + 1;
				
			}
		}
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		while(true){
			
			String p = s.nextLine();
			if(p.equals("end"))
				break;
			int a = Integer.parseInt(p.charAt(0) + "");
			values.add(a);
			
		}
		double [] rawValues = new double[9];
		for(Integer a: values){
			
			rawValues[a.intValue()-1]++;

			
		}
		
		double[] percent = new double[9];
		for(int i = 0 ; i < percent.length; i++){
			percent[i] = rawValues[i]/values.size();
			System.out.println("" + (i+1) + ": " + percent[i] + "%");
		}
		
		
	}
	
	
}
