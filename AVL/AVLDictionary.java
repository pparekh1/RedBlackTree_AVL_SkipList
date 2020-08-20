import java.util.Scanner;

public class AVLDictionary<K, V>{
    AVLNode<K, V> root; //Root of the AVL Tree.

    public boolean debugging = true;

    public AVLDictionary() {
        this(null); //Constructor
    }

    public AVLDictionary(AVLNode<K, V> root) {
        this.root = root; //Initialize the root
    }
    
    public void inorder(AVLNode<K,V> node) { //Inorder : Left subtree, Root, Right subtree
        if(node != null) {
            inorder(node.getLeft()); //Get the left keys first
            String gk = node.getKey().toString();
            String gv = node.getValue().toString();
            System.out.println("key: " + gk + " element: " + gv);
            inorder(node.getRight()); //Get the right keys.
        }
    }
	
    public void printTree() { //Printing the tree
        System.out.println("\n--------Printing the AVL Tree-------");
        inorder(root);
    }
	    
    public V search(K key) { //Returns the value of the node with the key value
        AVLNode<K, V> find;
        find = searchBelow(root, key);
        if(find == null) {
            return null; 
        }
        else return searchBelow(root,key).getValue();
    }

    private AVLNode<K,V> searchBelow(AVLNode<K, V> node, K key) { //Recursive solution to finding a specific key
        if(node == null) {
            return null; //nothing correctly passed.
        }
        
        if(((String) key).compareTo((String) node.getKey()) == 0) { //If the root is the key
            return node;
        }
       
        else if (((String) key).compareTo((String) node.getKey()) < 0) {   //If the key we are searching for is smaller than the key, go down the left subtree
            return searchBelow(node.getLeft(), key);
        }

        else if(((String) key).compareTo((String) node.getKey()) >  0) {  //If the key we are searching for is smaller than the key, go down the right subtree
            return searchBelow(node.getRight(), key);
        }
        else {
            System.out.println("Something went wrong. ERROR #1"); //This case not to be reached
            return null;
        }
    }
    
    boolean isEmpty(boolean bol) {
        return root == null;
    }
    
    public String toString() {
        String ret = isEmpty(true) ? "└── \n" : root.toString();
        return ret;
    }
    
    int height(AVLNode<K,V> n) { //Height of the AVL tree
        if (n!=null) 
            return n.height;
  
        return -1;
    } 
  
    int max(int a, int b) { 
        if(a>b)
        	return a;
        else return b;
    } 
   
    AVLNode<K,V> rightRotate(AVLNode<K,V> n) {  //Right Rotate
        AVLNode<K,V> m = n.left; 
        AVLNode<K,V> T2 = m.right; 
  
        m.right = n;  //Rotation
        n.left = T2; 
   
        n.height =1 + Math.max(height(n.left), height(n.right)); 
        m.height =1 + Math.max(height(m.left),height(m.right)); 
  
        return m; //Returning new root
    } 
   
    AVLNode<K,V> leftRotate(AVLNode<K,V> m) { //Left Rotate
        AVLNode<K,V> n = m.right; 
        AVLNode<K,V> T2 = n.left; 
 
        n.left = m;  //Rotation
        m.right = T2; 
 
        m.height = 1 + Math.max(height(m.left), height(m.right)); 
        n.height = 1+ Math.max(height(n.left), height(n.right)); 
 
        return n;  //Returning new root
    } 
  
    int getBalance(AVLNode<K,V> N) { //Balance of the tree
        if (N == null) 
            return 0; 
  
        return height(N.left) - height(N.right); 
    } 
    
    public int compare(AVLNode<K,V> n1, AVLNode<K,V> n2) { //Compare function
  
        return n1.compareTo(n2);
    }
      
    AVLNode<K,V> insert(AVLNode<K,V> node, K key, V value) {  //Insert function

        if (node == null) 
            return (new AVLNode<K,V>(key, value)); 
  
        if (key.toString().compareTo(node.key.toString()) < 0) {
        	//System.out.println(key.toString()+":"+node.key.toString()+"="+key.toString().compareTo(node.key.toString()));
        	node.left = insert(node.left, key, value); 
        } else if (key.toString().compareTo(node.key.toString()) > 0) {
        	//System.out.println(key.toString()+":"+node.key.toString()+"="+key.toString().compareTo(node.key.toString()));
            node.right = insert(node.right, key, value); 
        } else // Duplicate keys not allowed 
            return node; 
  
        node.height = 1 + max(height(node.left), height(node.right)); 
 
        int balance = getBalance(node); 
   
        if (balance > 1 && key.toString().compareTo(node.left.key.toString())<0) {
        	//System.out.println("LL Rotation:\n"+key+":"+node.left.key+"="+key.toString().compareTo(node.left.key.toString()));
            return rightRotate(node); 
        }
  
        // Right Right Case 
        if (balance < -1 && key.toString().compareTo(node.right.key.toString())>0) {
        	//System.out.println("RR Rotation:\n"+key+":"+node.right.key+"="+key.toString().compareTo(node.right.key.toString()));
            return leftRotate(node); 
        }
  
        // Left Right Case 
        if (balance > 1 && key.toString().compareTo(node.left.key.toString())>0) {
        	//System.out.println("LR Rotation:\n"+key+":"+node.left.key+"="+key.toString().compareTo(node.left.key.toString())); 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        // Right Left Case 
        if (balance < -1 && key.toString().compareTo(node.right.key.toString())<0) {
        	//System.out.println("RL Rotation:\n"+key+":"+node.right.key+"="+key.toString().compareTo(node.right.key.toString())); 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
        /* return the (unchanged) node pointer */
        return node; 
    }
    
    AVLNode<K,V> minValueNode(AVLNode<K,V> node)  
    {  
    	AVLNode<K,V> current = node;  
  
        /* loop down to find the leftmost leaf */
        while (current.left != null)  
        current = current.left;  
  
        return current;  
    }  
    
    AVLNode<K,V> deleteNode(AVLNode<K,V> root, K key)  //Delete Function
    {  
        if (root == null) 
            return root;  
  
        //If the key to be deleted is greater than, then going down in left subtree  
        if (key.toString().compareTo(root.key.toString())> 0)  
            root.right = deleteNode(root.right, key);  
  
        //If the key to be deleted is smaller than, then going down in left subtree  
        else if (key.toString().compareTo(root.key.toString())< 0)  
            root.left = deleteNode(root.left, key);  
  
        //If key is same as root's key, then this is the node  
        else
        {  
  
            // node with only one child or no child  
            if ((root.left == null) || (root.right == null))  
            {  
            	AVLNode<K,V> temp = null;  
                if (temp == root.left)  
                    temp = root.right;  
                else
                    temp = root.left;  
  
                // No child case  
                if (temp == null)  
                {  
                    temp = root;  
                    root = null;  
                }  
                else // One child case  
                    root = temp; // Copy the contents of  
                                // the non-empty child  
            }  
            else
            {  
  
                // node with two children: Get the inorder  
                // successor (smallest in the right subtree)  
            	AVLNode<K,V> temp = minValueNode(root.right);  
  
                // Copy the inorder successor's data to this node  
                root.key = temp.key;  
  
                // Delete the inorder successor  
                root.right = deleteNode(root.right, temp.key);  
            }  
        }  
  
        // If the tree had only one node then return  
        if (root == null)  
            return root;  
    
        root.height = max(height(root.left), height(root.right)) + 1;  //Updating the height of the tree
  
        int balance = getBalance(root);  //Balance of the tree
  
        //If this node becomes unbalanced, then there are 4 cases  
        //Left Left Case  
        if (balance > 1 && getBalance(root.left) >= 0)  
            return rightRotate(root);  
  
        //Left Right Case  
        if (balance > 1 && getBalance(root.left) < 0)  
        {  
            root.left = leftRotate(root.left);  
            return rightRotate(root);  
        }  
  
        //Right Right Case  
        if (balance < -1 && getBalance(root.right) <= 0)  
            return leftRotate(root);  
  
        //Right Left Case  
        if (balance < -1 && getBalance(root.right) > 0)  
        {  
            root.right = rightRotate(root.right);  
            return leftRotate(root);  
        }  
  
        return root;  
    }  

	public class AVLNode<K, V> {
		
		protected K key;

		protected V value;
		
		protected int height;

		protected AVLNode<K, V> left, right;

		public int balance; 

		public AVLNode(K key, V value, AVLNode<K, V> left, AVLNode<K, V> right,int balance) //Constructor
		{
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			this.balance = balance;
		} 
		
		public AVLNode(K key, V value) {
			this.key = key;
			this.value = value;
			this.height = 1;
		}

		public K getKey() {
			return key;
		} 
		
		public void setKey(K key) {
			this.key = key;
		} 

		public V getValue() {
			return value;
		} 

		public void setValue(V value) {
			this.value = value;
		}
		
		public AVLNode<K, V> getRight() {
			return right;
		}
		
		public void setRight(AVLNode<K, V> node) {
			right = node;
		} 

		
		public AVLNode<K, V> getLeft() {
			return left;
		} 
		
		public void setLeft(AVLNode<K, V> node) {
			left = node;
		}
		

		public int getBalance() {
			return balance;
		} 

		public void setBalance(int balance) {
			this.balance = balance;
		} // setBalance method
		
		public String toString() {
	        StringBuilder sb = new StringBuilder();
	        toString("", sb, true);
	        return sb.toString();
	    }
		
		private void toString(String prefix, StringBuilder sb, boolean tail) {
	        sb.append(prefix)
	                .append(tail ? "└── " : "├── ")
	                .append(key).append("::").append(value)
	                .append('\n');
	        if (left != null) {
	            left.toString(
	                    prefix + (tail ? "    " : "│   "),
	                    sb,
	                    right == null
	            );
	        }
	        if (right != null) {
	            right.toString(
	                    prefix + (tail ? "    " : "│   "),
	                    sb,
	                    true
	            );
	        }
	    }
		
		public int compareTo(AVLNode<K,V> node) {
	        return ((Comparable<Integer>)key).compareTo((Integer) node.key);
	    }
	}
	    
}
