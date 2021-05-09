class fibb
{

	int array[] = new int[100];

	public void fibSeries(int range)
	{

		array[0] = 0;
		array[1] = 1;

		for(int i = 2; i<range;i++)
		{
			array[i] = array[i-1] + array[i-2];

		}

		for(int i = 0; i<range; i++)
		{
			System.out.print(array[i]+" ");
		}

	}

}

class fib
{
	public static void main(String[] args) {
		
		fibb fib = new fibb();

		fib.fibSeries(10);
	}
}