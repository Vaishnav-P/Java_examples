import java.util.Scanner;

class node
{
	int data;
	node next;

}

class LinkedList
{
	public static void main(String[] args) {
		
		node head = new node();
		Scanner scan = new Scanner(System.in);
		String s;
		head.next = null;
		while(true)
		{
			System.out.print("Enter the data:");
			int data = scan.nextInt();
			scan.skip("\\R");
			node new_node = new node();
			new_node.data = data;
			if(head.next == null)
			{
				head.next = new_node;
				new_node.next = null;

			}
			else
			{
				node last = head;
				while(last.next!=null)
				{
					last = last.next;	

				}
				last.next = new_node;
				new_node.next = null;

			}

			System.out.println("Do you wish to continue (y/n):");
			s = scan.nextLine();
			System.out.println(s.length());
			if(!s.equals("y"))
				break;
		}	
		 node last = head.next;
		 while(last!=null)
		 {
		 	System.out.print(last.data+" ");
		 	last = last.next;

		 }

	}

}