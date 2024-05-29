package Project2Comp24;

import java.util.LinkedList;

public class AvlTree<TimeUnit> {

	TreeNode<TimeUnit> root;

	public AvlTree() {
		root = null;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>> height and rotate method<<<<<<<
	public int height(TreeNode<TimeUnit> node) {
		if (node == null) {
			return 0;
		}
		return node.Height;
	}

	public int height() {
		return height(root);
	}

	public TreeNode<TimeUnit> rotateRight(TreeNode<TimeUnit> k2) {
		TreeNode<TimeUnit> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.Height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.Height = Math.max(height(k1.left), height(k1.right)) + 1;
		return k1;
	}

	public TreeNode<TimeUnit> rotateLeft(TreeNode<TimeUnit> k1) {
		TreeNode<TimeUnit> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.Height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.Height = Math.max(height(k2.left), height(k2.right)) + 1;
		return k2;
	}

	public TreeNode<TimeUnit> doubleRotateRight(TreeNode<TimeUnit> k3) {
		k3.left = rotateLeft(k3.left);
		return rotateRight(k3);
	}

	public TreeNode<TimeUnit> doubleRotateLeft(TreeNode<TimeUnit> k3) {
		k3.right = rotateRight(k3.right);
		return rotateLeft(k3);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> insert >>>>>>>>
	// method

	// insert method
	private TreeNode<TimeUnit> insert(TreeNode<TimeUnit> ptr, TimeUnit element) {
		if (ptr == null) {
			ptr = new TreeNode<>(element);
			ptr.Height = Math.max(height(ptr.left), height(ptr.right)) + 1;
			return ptr;
		}
		Comparable<TimeUnit> comparable = (Comparable<TimeUnit>) element;
		if (comparable.compareTo(ptr.data) < 0) {
			ptr.left = insert(ptr.left, element);
		} else {
			ptr.right = insert(ptr.right, element);
		}

		ptr.Height = Math.max(height(ptr.left), height(ptr.right)) + 1;
		ptr = rebalance(ptr);
		return ptr;
	}

	public void insert(TimeUnit element) {
		root = insert(root, element);
	}

	// inorder method
	private void inorderHelper(TreeNode<TimeUnit> ptr) {
		if (ptr != null) {
			inorderHelper(ptr.left);
			System.out.println(ptr + "(" + ptr.Height + ")");
			inorderHelper(ptr.right);
		}
	}

	public void inorder() {
		inorderHelper(root);
	}

	// >>>>>>>>>>>>>>print level by level method>>>>>>>>>>>>>

	public String printAllYearsMonthsDays() {
		StringBuilder result = new StringBuilder();
		printAllYears(root, result);
		return result.toString();
	}

	public void printAllYears(TreeNode<TimeUnit> node, StringBuilder result) {
		if (node != null) {
			Year year = (Year) node.data;
			printAllMonths(year.getMonthAvlTree().root, result);
			result.append("\n");
			printAllYears(node.left, result); 
			printAllYears(node.right, result); 
		}
	}

	public void printAllMonths(TreeNode<Month> root2, StringBuilder result) {
		if (root2 != null) {
			Month month = (Month) root2.data;
			printAllDays(month.getDayAvlTree().root, result);
			printAllMonths(root2.right, result); 
		}
	}

	public void printAllDays(TreeNode<Day> root2, StringBuilder result) {
		if (root2 != null) {
			Day day = (Day) root2.data;
			result.append(day.getInformation()).append("\n");
			printAllDays(root2.left, result); 
			printAllDays(root2.right, result); 
		}
	}

	////////////////////>>>>>>>>>>>>>>>>>>>> print level by level
	public String printLevelOrder() {
		if (root == null) {
			return "Tree is empty.";
		}

		StringBuilder result = new StringBuilder();
		LinkedList<TreeNode<TimeUnit>> currentLevel = new LinkedList<>();
		LinkedList<TreeNode<TimeUnit>> nextLevel = new LinkedList<>();

		currentLevel.add(root);

		while (!currentLevel.isEmpty()) {
			TreeNode<TimeUnit> current = currentLevel.poll();
			result.append(" ").append(current.data).append(" ");

			if (current.left != null) {
				nextLevel.add(current.left);
			}
			if (current.right != null) {
				nextLevel.add(current.right);
			}

			if (currentLevel.isEmpty()) {
				result.append("\n"); 
				currentLevel.addAll(nextLevel);
				nextLevel.clear();
			}
		}

		return result.toString();
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.to print tree root
	public void printRoot() {
		if (root == null) {
			System.out.println("Tree is empty.");
		} else {
			System.out.println("Root value: " + root.data);
		}
	}

	public TimeUnit getRootValue() {
		if (root != null) {
			return root.data;
		}
		return null; 
	}
	
	public TreeNode<TimeUnit> getRoot() {
        return root;
    }

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>rebalance method
	private TreeNode<TimeUnit> rebalance(TreeNode<TimeUnit> ptr) {
		if (ptr == null) {
			return null;
		}
		int balance = Math.abs(height(ptr.left) - height(ptr.right));
		if (balance <= 1) {
			return ptr;
		}
		int leftHeight = height(ptr.left);
		int rightHeight = height(ptr.right);

		if (rightHeight > leftHeight) {
			TreeNode<TimeUnit> right = ptr.right;
			leftHeight = height(right.left);
			rightHeight = height(right.right);
			if (rightHeight >= leftHeight) {
				return rotateLeft(ptr);
			}

			return doubleRotateLeft(ptr);
		}
		TreeNode<TimeUnit> left = ptr.left;
		leftHeight = height(left.left);
		rightHeight = height(left.right);
		if (leftHeight >= rightHeight) {
			return rotateRight(ptr);
		}

		return doubleRotateRight(ptr);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> delete method
	public void delete(TimeUnit element) {
		root = delete(root, element);
	}

	private TreeNode<TimeUnit> delete(TreeNode<TimeUnit> ptr, TimeUnit element) {
		if (ptr == null) {
			return null;
		}

		Comparable<TimeUnit> comparable = (Comparable<TimeUnit>) element;
		if (comparable.compareTo(ptr.data) < 0) {
			ptr.left = delete(ptr.left, element);
		} else if (comparable.compareTo(ptr.data) > 0) {
			ptr.right = delete(ptr.right, element);
		} else {
			if (ptr.left == null || ptr.right == null) {
				ptr = (ptr.left == null) ? ptr.right : ptr.left;
			} else {
				TimeUnit minRight = getMin(ptr.right);
				ptr.data = minRight;
				ptr.right = delete(ptr.right, minRight);
			}
		}

		if (ptr != null) {
			ptr.Height = Math.max(height(ptr.left), height(ptr.right)) + 1;
			ptr = rebalance(ptr);
		}
		return ptr;
	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< find method
	public TimeUnit find(TimeUnit element) {
		return find(root, element);
	}

	private TimeUnit find(TreeNode<TimeUnit> node, TimeUnit element) {
		if (node == null) {
			return null;
		}

		if (((Comparable<TimeUnit>) element).compareTo(node.data) == 0) {
			return node.data;
		} else if (((Comparable<TimeUnit>) element).compareTo(node.data) < 0) {
			return find(node.left, element);
		} else {
			return find(node.right, element);
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> find int
	public TimeUnit find(int val) {
		return find(root, val);
	}

	private TimeUnit find(TreeNode<TimeUnit> node, int val) {
		if (node == null) {
			return null;
		}

		if (node.data instanceof Year) {
			// Handle Year type
			int nodeValue = ((Year) node.data).getYear();
			if (val == nodeValue) {
				return node.data;
			} else if (val < nodeValue) {
				return find(node.left, val);
			} else {
				return find(node.right, val);
			}
		} else if (node.data instanceof Month) {
			// Handle Month type
			int nodeValue = ((Month) node.data).getMonthAsInt();
			if (val == nodeValue) {
				return node.data;
			} else if (val < nodeValue) {
				return find(node.left, val);
			} else {
				return find(node.right, val);
			}
		} else if (node.data instanceof Day) {
			// Handle Day type
			int nodeValue = ((Day) node.data).getDay();
			if (val == nodeValue) {
				return node.data;
			} else if (val < nodeValue) {
				return find(node.left, val);
			} else {
				return find(node.right, val);
			}
		} else {
			// Handle other types or throw an exception based on your requirements
			return null;
		}
	}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>. get min and max method
	public TimeUnit getMin() {
		return getMin(root);
	}

	private TimeUnit getMin(TreeNode<TimeUnit> ptr) {
		if (ptr == null) {
			return null;
		}
		if (ptr.left != null) {
			return getMin(ptr.left);
		}
		return ptr.data;
	}

	public TimeUnit getMax() {
		return getMax(root);
	}

	private TimeUnit getMax(TreeNode<TimeUnit> ptr) {
		if (ptr == null) {
			return null;
		}

		if (ptr.right != null) {
			return getMax(ptr.right);
		}

		return ptr.data;
	}
	//////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> get size and get method 

	public int getSize() {
		return getSize(root);
	}

	private int getSize(TreeNode<TimeUnit> node) {
		if (node == null) {
			return 0;
		}
		return getSize(node.left) + 1 + getSize(node.right);
	}

	public TimeUnit get(int val) {
		return get(root, val);
	}

	private TimeUnit get(TreeNode<TimeUnit> node, int val) {
		if (node == null) {
			return null;
		}

		if (node.data instanceof Year) {
			
			int nodeValue = ((Year) node.data).getYear();
			if (val == nodeValue) {
				return node.data;
			} else if (val < nodeValue) {
				return get(node.left, val);
			} else {
				return get(node.right, val);
			}
		} else if (node.data instanceof Month) {
			
			int nodeValue = ((Month) node.data).getMonthAsInt();
			if (val == nodeValue) {
				return node.data;
			} else if (val < nodeValue) {
				return get(node.left, val);
			} else {
				return get(node.right, val);
			}
		} else if (node.data instanceof Day) {
			
			int nodeValue = ((Day) node.data).getDay();
			if (val == nodeValue) {
				return node.data;
			} else if (val < nodeValue) {
				return get(node.left, val);
			} else {
				return get(node.right, val);
			}
		} else {
			
			return null;
		}
	}
	

}
