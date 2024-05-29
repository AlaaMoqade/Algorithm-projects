package Project2Comp24;

public class TreeNode< TimeUnit> {
	TimeUnit data;
	TreeNode<TimeUnit> left;
	TreeNode<TimeUnit> right;
	int Height;

	public TreeNode(TimeUnit data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return data.toString();
	}

	public TreeNode<TimeUnit> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<TimeUnit> left) {
		this.left = left;
	}

	public TreeNode<TimeUnit> getRight() {
		return right;
	}

	public void setRight(TreeNode<TimeUnit> right) {
		this.right = right;
	}
	
	
}
