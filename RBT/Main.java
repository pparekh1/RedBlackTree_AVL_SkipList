import java.util.Scanner;

//import rbt.RedBlackTree.Node;

public class Main {

	public static void main(String args[]) {
		// Dictionary<String> d;

		Scanner sc = new Scanner(System.in);
		RedBlackTree<String> rbt = new RedBlackTree<>();

		System.out.println("**********************RED BLACK TREE***********************");
		char c = 0;
		do {
			
			//System.out.println("1. Insert into Tree.");
            System.out.println("\nRed Black Tree Operations\n");
            
            System.out.println("1. Insert ");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("\n---------For case 4 and 5, please make sure the tree is not empty---------\n");
            System.out.println("4. Minimum Node");
            System.out.println("5. Maximum node");
            System.out.println("6. Empty tree check");
            
            
            System.out.println();
            System.out.println("Enter the operation to be carried out :");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					System.out.println("Enter the  number of elements to insert : ");
					int num = sc.nextInt();
					System.out.println("Inserting "+num+" elements :");
					int i=0;
					while( i < num)
					{
					  rbt.add(sc.next());
					  i++;
					}
					System.out.println("Printing Red Black Tree : (1 = Black color, 0 = Red Color)");
					System.out.println(rbt.toString());
					break;
					
		     	case 2:
					System.out.println("Enter the element to search: ");
					boolean search = rbt.contains(sc.next());
					System.out.println(search);
					break;
					
				case 3:
					System.out.println("Enter the  number of elements to delete : ");
					int num1 = sc.nextInt();
					System.out.println("Deleting "+num1+" elements :");
					int d = 0;
					while(d < num1)
					{
					  rbt.delete(sc.next());
					  d++;
					}
					System.out.println(rbt.toString());
					if(rbt.isEmpty(true))
						System.out.println("The Tree is Empty");
					break;
						
		     	case 4:
					System.out.println("Minimum of the entire tree : ");
					String value = rbt.min();
					System.out.println(value);
					break;
					
		     	case 5:
		     		System.out.println("Maximum of the entire tree: ");
		     		String value1 = rbt.min();
					System.out.println(value1);
					rbt.max();
					break;
					
		     	case 6:
		     		System.out.println("Check whether the tree is empty: ");
					boolean e = rbt.isEmpty();
					System.out.println();
					System.out.println(e);
					break;
		     		
				default:
					System.out.println("Invalid choice");
					break;
			}
			
			System.out.println("\nDo you want to continue (y/n): \n");
			c = sc.next().charAt(0);
		}while (c == 'y');
		
		System.out.println();
		
		System.out.println("Printing Red Black Tree : (1 = Black color, 0 = Red Color)");
		System.out.println(rbt.toString());
	
	}
}

//
//		System.out.println("The minimum item :");
//		System.out.println(rbt.min());
//		System.out.println("The maximum item :");
//		System.out.println(rbt.max());



	

