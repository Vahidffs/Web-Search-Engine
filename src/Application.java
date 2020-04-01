import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
public static void main(String[] args) throws FileNotFoundException {
	String regex = "[^a-zA-Z0-9]";
	BinarySearchTree a = new BinarySearchTree();
	Scanner scan = new Scanner(new File("Query.txt"));
	while(scan.hasNext()) {
		String nextWord = scan.next().toLowerCase().replaceAll(regex, "").trim();
		if (nextWord.length() != 0) {
		if (nextWord.endsWith(",")) {
			nextWord = nextWord.substring(0, nextWord.length() - 1);
			}
		Word werd = new Word(nextWord, 1);
			if(a.contains(werd)) {
				a.findandupdate(werd);
			} else {
			a.insert(werd);
			}
		}
	}
	
	
			a.printTree();
			
	
}
}
