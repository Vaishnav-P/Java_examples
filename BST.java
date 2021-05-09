import java.util.Scanner;

class Node
{
	int data;
	Node right,left;

	Node(int data)
	{
		this.data = data;

	}
	public String toString()
	{
		return "Data:"+data;
	}
}


class BST
{
	Node root;

	public void  addNode(int data)
	{
		Node newNode = new Node(data);

		if(root == null)
		{
			root = newNode; // Root is empty then newNode becomes the root node
		}
		else
		{
			Node focusNode = root; // used to traverse the tree starting from the root
			Node parent; // A node for storing the parent of a child node

			while(true)
			{
				parent = focusNode; // root is the top most parent

				// check if the newNode should go to the left of parent node

				if(data < focusNode.data)
				{
					// moving to the left child node
					focusNode = focusNode.left;

					if(focusNode == null) // left child is empty
					{
						parent.left = newNode;
						return;
					}

				}
				else
				{
					focusNode = focusNode.right;
					if(focusNode == null)
					{
						parent.right = newNode;
						return;
					}

				}

			}

		}

	}

	public void inorder(Node root)
	{
		if(root != null)
		{
			inorder(root.left);
			System.out.print(root+" ");
			inorder(root.right);

		}


	}

	public Node findNode(int data)
	{
		Node focusNode = root;

		while(focusNode.data != data)
		{
			// search left
			if(data < focusNode.data)
			{
				focusNode = focusNode.left;
			}
			else // search right
			{
				focusNode = focusNode.right;

			}
			if(focusNode == null)
				return null;
		}	

		return focusNode;
	}

	public static void main(String[] args) 
	{
			BST tree = new BST();

			tree.addNode(55);
			tree.addNode(1);
			tree.addNode(5);
			tree.addNode(35);
			tree.addNode(52);
			tree.addNode(23);
			tree.addNode(34);

			tree.inorder(tree.root);
			Node found = tree.findNode(52);

			System.out.println("\n Node found:"+found);
	}	


}