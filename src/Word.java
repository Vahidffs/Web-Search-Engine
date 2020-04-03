
public class Word implements Comparable<Object> {

	String oneWord;
	int counter;
	
	Word(String oneWord, int counter) {
		this.oneWord = oneWord;
		this.counter = 1;
	}
	public String getoneWord() {
		return oneWord;
	}
	public void setoneWord(String oneWord) {
		this.oneWord = oneWord;
	}
	public int getcounter() {
		return counter;
	}
	public void setcounter(int counter) {
		this.counter = counter;
	}
	
	@Override
	public String toString() {
		return "Word: " + oneWord + " *** Frequency: " + counter;
	}
	
	@Override
	public int compareTo(Object o) {
		
		Word newWord = (Word)o; 

		return this.oneWord.compareTo(newWord.getoneWord()); 
	}
	
}