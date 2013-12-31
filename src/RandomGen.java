import java.util.Random;


public class RandomGen {



	public static void main(String[] args){
		
		int min = 1;
		int max = 999999;
			
		Random numGen = new Random();
		int a = 10000;
		int[] numbers = new int[a];
		for(int i = 0; i < a; i++){
			numbers[i] = numGen.nextInt(numGen.nextInt(numGen.nextInt(999999))+ 1) + 1;
			
		}
		
		for(int c : numbers){
			
			System.out.println("" + c);
			
		}
		
	}
	
	
}