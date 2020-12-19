public class Fibonacci {
	
	public static void main(String[] args) {
		System.out.println("\n\nThe " + args[0] + " number in the Fibonacci sequence is " + fib(Integer.parseInt(args[0])) + "\n\n");
	}

	public static int fib(int num) {
		if(num < 3)
			return 1;
		int first_num = 1;
		int second_num = 1;
		int temp = 0;
		for(int i = 2; i < num; i++) {
			temp = second_num;
			second_num = first_num + second_num;
			first_num = temp;
		}
		return second_num;
	}
}	
