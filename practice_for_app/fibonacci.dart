void main(List<String> args) {
	print("\n\nThe " + args[0] + " number in the fibonacci sequence is " + fib(int.parse(args[0])).toString() + "\n\n");
}

int fib(int num) {
	if(num < 3)
		return 1;
	int first_num = 1;
	int second_num = 1;
	int temp = 0;
	for(int i = 3; i <= num; i++) {
		temp = second_num;
		second_num = first_num + second_num;
		first_num = temp;
	}
	return second_num;
}
