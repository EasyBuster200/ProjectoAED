package dataStructures;

public class RASCUNHO {

	public RASCUNHO() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(factorial(3));
		int firstInt = 3;
		int secondInt = 8;
		int sum = add(firstInt,secondInt);
		System.out.println("The sum is: "+sum);
		
		for(int i=0; i<5;i++) {
			System.out.println(i++);
		}
	}
	
	private static int add(int firstInt, int secondInt) {
		// TODO Auto-generated method stub
		return firstInt+secondInt;
	}

	public static int factorial(int number) {
		if(number==1) {
			return number;
		}else {
			return number * factorial(--number);
		}
	}	
	public static int sequentialSearch(int[] v,int aim) {
		int i=0;//primitivo MELHOR CASO=1 PIOR CASO=1
		
		while ( i < v.length && v[i] < aim ){
			i++;//PRIMITIVO MELHORCASO=4 PIORCASO= n(4+1 )+2
		}//while não é primitivo 
		if ( i < v.length && v[i] == aim )
		return i;//primitivo
		else return -1;//primitivo
	}
	
	/*public static int binSearch(int[] vector,int target) {
		
	}*/ 

	/*public static <E> void bubbleSort( int[] vec, int vecSize) {
		for(int i = vecSize-1;i>0;i--) {
			for(int j = 0; j<i; j++) {
				if(vec[j]>vec[j+1]) {
					swapElements(vec,j,j+1);
				}
			}
		}
			
	}//pior caso= melhor caso)*/
	
	
	
	
	
}
