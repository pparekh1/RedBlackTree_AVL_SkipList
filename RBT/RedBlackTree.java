
public class RedBlackTree<E> implements Dictionary<E> {

	public static final String color = null;

	private final Node nil;

	private Node root;

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public int getOperations() {
		return operations;
	}

	public void setOperations(int operations) {
		this.operations = operations;
	}

	public Node getMin() {
		return min;
	}

	public void setMin(Node min) {
		this.min = min;
	}

	public Node getMax() {
		return max;
	}

	public void setMax(Node max) {
		this.max = max;
	}

	public Node getNil() {
		return nil;
	}

	private int comparisons;

	private int operations;

	private Node min, max;

	public RedBlackTree() {
		nil = new Node(null);
		root = max = min = nil;
		comparisons = 0;
	}

	@Override
	public boolean isEmpty() {
		return isEmpty(false);
	}

	public boolean contains(E item) {
		reset();
		boolean ret = item != null && search(new Node(item)) != nil;
		return ret;
	}

	public boolean hasPredecessor(E item) {
		reset();
		boolean ret = item != null && !isEmpty(true) && compare(new Node(item), min) > 0;
		return ret;
	}

	public boolean hasSuccessor(E item) {
		reset();
		boolean ret = item != null && !isEmpty(true) && compare(new Node(item), max) < 0;
		return ret;
	}

	public E min() {
		if (isEmpty(true))
			System.out.println();
			System.out.println("Dictionary is empty");
		return min.key;
	}
	
	public E max(){
		if (isEmpty(true))
			System.out.println();
			System.out.println("Dictionary is empty");
		return max.key;
	}

	public boolean add(E item) {
		reset();
		Node node = new Node(item);
		boolean tmp = node.key != null && insert(node);
		return tmp;
	}

	public boolean delete(E item) {
		
		if (item == null) {
			return false;
		}
		if (isEmpty(true)) {
			return false;
		}
		Node z = search(new Node(item));
		if (z == nil) {
			return false;
		}
		delete(z);
		return true;
	}

	public String toString() {

		String ret = isEmpty(true) ? "└── \n" : root.toString();
		return ret;
	}

	private Node search(Node node) {
		
		if (isEmpty(true)) //tree is empty, no node exists
			return nil;
		Node curr = root;
		// move down the tree until we find an element with the same value (as
		// defined by their comparative values)
		while (curr != nil) {
			int cmp = compare(node, curr);

			if (cmp == 0)
				return curr;
			if (cmp < 0) {
				if (curr.left != nil) {
					curr = curr.left;
					continue;
				}
			} else {
				if (curr.right != nil) {
					curr = curr.right;
					continue;
				}
			}
			curr = nil;
		}
		return nil;
	}

	public boolean insert(Node element) {
		if (element.key == null)
			return false;
		Node curr = root;
		if (isEmpty(true)) {
			root = element;
			element.color = Node.BLACK;
			element.parent = nil;
			min = max = root;
			return true;
		} else {
			element.color = Node.RED;
			while (true) {
				int cmp = compare(element, curr);
				if (cmp == 0)
					return false;
				if (cmp < 0) {
					if (curr.left == nil) {
						curr.left = element;
						element.parent = curr;
						break;
					} else
						curr = curr.left;
				} else {
					if (curr.right == nil) {
						curr.right = element;
						element.parent = curr;
						break;
					} else
						curr = curr.right;
				}
			}
			fixInsert(element);
		}

		if (min == nil || max == nil) {
			min = max = element;
		} else {
			if (compare(element, min) < 0)
				min = element;
			else if (compare(element, max) > 0)
				max = element;
		}
		return true;
	}

	private void fixInsert(Node node) {
		
		while (node.parent.color == Node.RED) { //Continue till parent is black
			
			Node uncle;
			Node grandParent;
			if (node.parent == node.parent.parent.left) {
				
				uncle = node.parent.parent.right; //set the Uncle to be right child
				grandParent = node.parent.parent;

				if (uncle.color == Node.RED) {
			
					node.parent.color = Node.BLACK;  // case 1 - the node's uncle is red

					uncle.color = Node.BLACK;
					grandParent.color = Node.RED;
					node = grandParent;
				} else {
					if (node == node.parent.right) {
						
						node = node.parent; // case 2 - uncle is black and node is a right child
						rotateLeft(node);
					}
					// 
					node.parent.color = Node.BLACK; //case 3 - uncle is black and node is a left child
					grandParent.color = Node.RED;
					rotateRight(grandParent);
				}
			} else {
			
				uncle = node.parent.parent.left;//set the uncle to be left child,symmetric with right and left exchanged
				grandParent = node.parent.parent;

				if (uncle.color == Node.RED) {
					node.parent.color = Node.BLACK;
					uncle.color = Node.BLACK;
					grandParent.color = Node.RED;
					node = node.parent.parent;
				} else {
					if (node == node.parent.left) {
						node = node.parent;
						rotateRight(node);
					}
					node.parent.color = Node.BLACK;
					grandParent.color = Node.RED;
					rotateLeft(grandParent);
				}
			}
		}
		root.color = Node.BLACK;
	}

	private void delete(Node elementDel) {
		Node shift;
		Node curr = elementDel;
		int yOrigColour = curr.color;

		// check the cases - dependent on how many children the node has
		if (elementDel.left == nil) {
			// if the node to delete doesn't have a left child, just replace it
			// by the right child
			shift = elementDel.right;
			// transplant(elementDel, elementDel.right);
			if (elementDel.parent == nil)
				root = elementDel.right;
			else if (elementDel == elementDel.parent.left)
				elementDel.parent.left = elementDel.right;
			else
				elementDel.parent.right = elementDel.right;
			elementDel.right.parent = elementDel.parent;

		} else if (elementDel.right == nil) {
			// and vice versa for the left child
			shift = elementDel.left;
			// transplant(elementDel, elementDel.left);
			if (elementDel.parent == nil)
				root = elementDel.left;
			else if (elementDel == elementDel.parent.left)
				elementDel.parent.left = elementDel.left;
			else
				elementDel.parent.right = elementDel.left;
			elementDel.left.parent = elementDel.parent;
		} else {
			// if it has two children, find the successor
			curr = minimum(elementDel.right);
			// update the colour before any changes to the tree structure occur
			yOrigColour = curr.color;
			shift = curr.right;
			if (curr.parent == elementDel) {
				shift.parent = curr;
			} else {
				// transplant(curr, curr.right);
				if (curr.parent == nil)
					root = curr.right;
				else if (curr == curr.parent.left)
					curr.parent.left = curr.right;
				else
					curr.parent.right = curr.right;
				curr.right.parent = curr.parent;

				curr.right = elementDel.right;
				curr.right.parent = curr;
			}
			// transplant(elementDel, curr);
			if (elementDel.parent == nil)
				root = curr;
			else if (elementDel == elementDel.parent.left)
				elementDel.parent.left = curr;
			else
				elementDel.parent.right = curr;
			curr.parent = elementDel.parent;

			curr.left = elementDel.left;
			curr.left.parent = curr;
			curr.color = elementDel.color;
		}

		// if the node colour was black then we might have violated the
		// properties of a red-black tree - so fix up the tree
		if (yOrigColour == Node.BLACK)
			fixDelete(shift);

		// finally, update the references to the min/max if necessary
		if (isEmpty(true))
			min = max = nil;
		else if (elementDel == min)
			min = minimum(root);
		else if (elementDel == max)
			max = maximum(root);
	}

	private void fixDelete(Node node) {
		while (node != root && node.color == Node.BLACK) {
			++comparisons;
			if (node == node.parent.left) {
				Node sibling = node.parent.right;
				if (sibling.color == Node.RED) {
					// case 1 - node's sibling is red
					sibling.color = Node.BLACK;
					node.parent.color = Node.RED;
					rotateLeft(node.parent);
					sibling = node.parent.right;
				}
				if (sibling.left.color == Node.BLACK && sibling.right.color == Node.BLACK) {
					// case 2 - node's sibling is black and both of the
					// sibling's children are black
					sibling.color = Node.RED;
					node = node.parent;
				} else {
					if (sibling.right.color == Node.BLACK) {
						// case 3 - node's sibling is black, the node's left
						// child is red, and the node's right child is black
						sibling.left.color = Node.BLACK;
						sibling.color = Node.RED;
						rotateRight(sibling);
						sibling = node.parent.right;
					}
					// case 4 - the node's sibling is black and the sibling's
					// right child is red
					sibling.color = node.parent.color;
					node.parent.color = Node.BLACK;
					sibling.right.color = Node.BLACK;
					rotateLeft(node.parent);
					node = root;
				}
			} else {
				// symmetric to the above clause with left and right exchanged
				Node sibling = node.parent.left;
				if (sibling.color == Node.RED) {
					sibling.color = Node.BLACK;
					node.parent.color = Node.RED;
					rotateRight(node.parent);
					sibling = node.parent.left;
				}
				if (sibling.right.color == Node.BLACK && sibling.left.color == Node.BLACK) {
					sibling.color = Node.RED;
					node = node.parent;
				} else {
					if (sibling.left.color == Node.BLACK) {
						sibling.right.color = Node.BLACK;
						sibling.color = Node.RED;
						rotateLeft(sibling);
						sibling = node.parent.left;
					}
					sibling.color = node.parent.color;
					node.parent.color = Node.BLACK;
					sibling.left.color = Node.BLACK;
					rotateRight(node.parent);
					node = root;
				}
			}
		}
		node.color = Node.BLACK;
	}

	private void rotateLeft(Node node) {
		Node rightChild = node.right;
		node.right = rightChild.left;
		if (rightChild.left != nil)
			rightChild.left.parent = node;
		rightChild.parent = node.parent;
		if (node.parent == nil)
			root = rightChild;
		else if (node == node.parent.left)
			node.parent.left = rightChild;
		else
			node.parent.right = rightChild;
		rightChild.left = node;
		node.parent = rightChild;
	}

	private void rotateRight(Node node) {
		Node leftChild = node.left;
		node.left = leftChild.right;
		if (leftChild.right != nil)
			leftChild.right.parent = node;
		leftChild.parent = node.parent;
		if (node.parent == nil)
			root = leftChild;
		else if (node == node.parent.right)
			node.parent.right = leftChild;
		else
			node.parent.left = leftChild;
		leftChild.right = node;
		node.parent = leftChild;
	}

	private Node maximum(Node node) {
		while (node.right != nil) {
		
			node = node.right;
		}
		return node;
	}

	private Node minimum(Node node) {
		while (node.left != nil) {
			
			node = node.left;
		}
		return node;
	}

	public boolean isEmpty(boolean quiet) {
		if (!quiet) {
		
		}
		return root == nil;
	}

	private int compare(Node n1, Node n2) {
	
		return n1.compareTo(n2);
	}

	private void reset() {
		comparisons = 0;
	}

	public class Node {

		private static final byte RED = 0;

		private static final byte BLACK = 1;

		private E key;

		private int color = BLACK;

		private Node left, right, parent;

		private Node(E key) {
			this.key = key;
			left = nil;
			right = nil;
			parent = nil;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			toString("", sb, true);
			return sb.toString();
		}

		private void toString(String prefix, StringBuilder sb, boolean tail) {
			sb.append(prefix).append(tail ? "└── " : "├── ").append(key).append("::").append(color).append('\n');
			if (left != nil) {
				left.toString(prefix + (tail ? "    " : "│   "), sb, right == nil);
			}
			if (right != nil) {
				right.toString(prefix + (tail ? "    " : "│   "), sb, true);
			}
		}

		@SuppressWarnings("unchecked")
		public int compareTo(Node node) {
			return ((Comparable<String>) key).compareTo((String) node.key);
		}
	}



}
