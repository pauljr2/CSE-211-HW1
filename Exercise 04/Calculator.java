
public class Calculator{

	private static int result; 

	public void add(int n){
		result = result + n;
	}
	public void substract(int n){
		result = result - n; 
	}
	public void multiply(int n){
		result *= n;
	} 
	public void divide(int n){
		result = result / n;
	}
	public void square(int n){
		result = n * n;
	}
	public void squareRoot(int n){
		result = n;
		int squareRoot = n/2;
		for (int temp = squareRoot * 2; temp-squareRoot !=0; squareRoot = (temp + (n / temp))/2) {
			temp = squareRoot;
			result = temp;
		}

	}
	public void clear(){ 
		result = 0;
	}
	public int getResult(){
		return result;
	}
}