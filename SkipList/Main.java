import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SkipList sl = new SkipList();
		
		System.out.println("**********************SkipList***********************");
		System.out.println();
		sl.insert("One", 1);
		sl.insert("Four", 4);
		sl.insert("Ten", 10);
		sl.insert("Three", 3);
		sl.insert("Eleven", 11);
		sl.printHorizontal();
		System.out.println();
		System.out.println("Size of the Skip List : ");
		System.out.println();
		int bol2 = sl.size();
		System.out.println("-->"+bol2);
		System.out.println();

		System.out.println("1.Enter the key whose associted value is required :");
		Integer value = sl.get("Eleven"); // get the value
		System.out.println();
		System.out.print("-->The value of the key passed "+value);
		System.out.println();

		System.out.println();
		System.out.println("2.Enter the key of the pair to be searched : ");
		SkipListEntry value1 = sl.findEntry(sc.next()); // to find the entry
		System.out.println();
		System.out.print("-->The key value pair searched :"+value1);
		System.out.println();

		System.out.println(); // insert the key-value pair
		System.out.println("3.Enter the key-value to be inserted in Skip List :");
		System.out.println();
		sl.insert("Sixty", 60);
		sl.printHorizontal();

		System.out.println(); // get one row of the key passed as parameter
		System.out.println("4.Get one row of the key passed as parameter : ");
		System.out.println();
		String value3 = sl.getOneRow(value1);
		System.out.println("-->"+value3);

		System.out.println(); // get the column of the key passed as parameter in a horizontal manner
		System.out.println("5.Get the column of the key passed as parameter in a horizontal manner : ");
		System.out.println();
		String value4 = sl.getOneColumn(value1);
		System.out.println("-->"+value4);

		System.out.println(); // to check whether the skiplist is empty
		System.out.println("6.Empty Skiplist check ");
		System.out.println();
		boolean bol1 = sl.isEmpty();
		System.out.println("-->"+bol1);

		System.out.println(); // gives the size of the skiplist
		System.out.println("7.Size of the Skip List : ");
		System.out.println();
		int size = sl.size();
		System.out.println("-->"+size);
		
		System.out.println("8.Remove from the Skip List : ");
		System.out.println();
		sl.remove("One");
		sl.printHorizontal();
		
		
//		char c = 0;
//		do {
//            System.out.println("\nSkip List Operations\n");
//            
//            System.out.println("1. Insert ");
//            System.out.println("2. Get the value");
//            System.out.println("3. Search an entry");
//            System.out.println("4. Get one row of the key passed as parameter");
//            System.out.println("5. Get the column of the key passed as parameter in a horizontal manner");
//            System.out.println("6. Empty Skiplist check");
//            System.out.println("7. Print the Skip List");
//            System.out.println("8. Size of the Skiplist");
//            
//            System.out.println();
//            System.out.println("Enter the operation to be carried out on the Skip List:");
//			int choice = sc.nextInt();
//			
//			switch(choice) {
//				case 1: 
//					System.out.println("Enter the pair to insert: ");
//						sl.insert("One", 1);   //Insert
//						sl.insert("Four", 4);
//						sl.insert("Ten", 10);
//						sl.insert("Three", 3);
//						sl.insert("Eleven", 11);
//						sl.printHorizontal();
//					break;
//					
//		     	case 2:
//					System.out.println("Enter the key whose associted value is required :");
//					Integer value = sl.get(sc.next()); // get the value of the key passed
//					System.out.print(value);
//					break;
//					
//				case 3:
//					System.out.println("Enter the key of the pair to be searched : ");
//					SkipListEntry value1 = sl.findEntry(sc.next()); // to find the entry of the key passed
//					System.out.print(value1);
//					break;
//						
//		     	case 4:
//					System.out.println("Get one row of the key passed as parameter : ");
////					String value3 = sl.getOneRow(sc.next());
////					System.out.println(value3);
//					break;
//					
//		     	case 5:
//		     		System.out.println("Get the column of the key passed as parameter in a horizontal manner : ");
////		     		String value4 = sl.getOneColumn(sc.next());
////		    		System.out.println(value4);
//					break;
//					
//		     	case 6:
//		     		System.out.println("Empty Skiplist check ");
//		    		boolean empty = sl.isEmpty();
//		    		System.out.println(empty);
//					break;
//		     		
//		     	case 7:
//		     		System.out.println("Print the Skip List : ");
//		     		sl.printHorizontal();
//					break;
//				
//		     	case 8:
//		     		System.out.println("Size of the Skip List : ");
//		    		int size = sl.size();
//		    		System.out.println(size);
//					break;
//					
//				default:
//					System.out.println("Invalid choice");
//					break;
//			}
//			
//			System.out.println("\nDo you want to continue (y/n): \n");
//			c = sc.next().charAt(0);
//		}while (c == 'y');
//		
            
         


	}
}