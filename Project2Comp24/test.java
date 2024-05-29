package Project2Comp24;

public class test {

	public static void main(String[] args) {
		AvlTree<Integer> list = new AvlTree<>();
		for (int y = 2017; y <= 2024; y++) {
			list.insert(y);
		}

	//	System.out.println(list.printLevelOrder());
		list.printRoot();
		System.out.println(list.find(2013));
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		list.delete(2020);
	//	System.out.println(list.printLevelOrder());

		list.printRoot();
		// System.out.println(list.printLevelOrder());

	}

}
