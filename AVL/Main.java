import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
    AVLDictionary<String, String> dict = new AVLDictionary<String, String>();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("**********************AVL TREE***********************");
		char c = 0;
		do {
			
			//System.out.println("1. Insert into Tree.");
            System.out.println("\nAVL Tree Operations\n");
            
            System.out.println("1. Insert ");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Printing the tree");
            
            System.out.println();
            System.out.println("Enter the operation to be carried out :");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1: 
					System.out.println("Enter the number of key value pairs to insert: ");
					int i = 0; 
					int num = sc.nextInt();
					System.out.println("Inserting "+num+" elements :");
					while(i < num)
					{
						String str = sc.next();
						String[] arr = str.split(",");
						dict.root=dict.insert(dict.root, arr[0], arr[1]);
						i++;
					}
					System.out.println("\n\n\nTree to to String\n\n\n"+dict.toString());
					break;
				
				case 2: 
					System.out.println("\n\n\nAVL TREE\n\n\n"+dict.toString());
					System.out.println("Enter the key to be searched :");
					String element = sc.next();
					System.out.println("The value of the key is "+dict.search(element));
					break;
			
				case 3:
					System.out.println("\n\n\nAVL TREE\n\n\n"+dict.toString());
					System.out.println("Enter the number of key value pair to be deleted :");
					int j = 0; 
					int num1 = sc.nextInt();
					System.out.println("deleting "+num1+" elements :");
					while(j < num1)
					{
						String str = sc.next();
						String[] arr = str.split(",");
						dict.root=dict.insert(dict.root, arr[0], arr[1]);
						j++;
					}
					break;
					
				case 4:
					dict.printTree();
					break;
					
				default:
					System.out.println("Invalid choice"); 
					break;
			}
			
			System.out.println("\nDo you want to continue (y/n): \n");
			c = sc.next().charAt(0);
		}while (c == 'y');
            

//  	    
//  	    StringBuilder s = new StringBuilder();
//  	    s.append("hello ");
//  	    s.append(true);
//  	    System.out.println(s);

		}
	}
