import java.io.*;
public class OutputAnalysis {
	String inD;  //Represents the directory for the input file.
	String outD; //Represents the directory for the output file.

	int numWord;  //Counts number of words in the output.
	int numLine;  //Counts number of lines in the output.
	int numSpace; //Counts number of spaces in the output.
	int numChar;  //Counts number characters in the output.
	
	public OutputAnalysis(String inputDir, String outputDir) throws IOException {
		inD = inputDir;
		outD = outputDir;
		
		numWord = 0;
		numLine = 0;
		numSpace = 0;
		numChar = 0;
		
		analysis();
	}
	/* analysis(): Reads output file and determines the number of characters, words, spaces, and lines in
	 * the file.
	 */
	private void analysis() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(outD)); //Reading output file.
		String line; //Used to read line from file.
		
		while ((line = br.readLine()) != null) { //Reads all lines of the output file.
			numLine++; //Increment for each line read.
			numChar = numChar + line.length(); //Increment by length of each line.
			boolean charLetter = false; //Used to indicate if the previous character was a letter.
			
			/* for loop: Goes through each character of the line. the word count is increased each time
			 * a non letter is reached or each time the line ends at a letter. The space count is increased 
			 * each time a space character is found.
			 */
			for (int i = 0; i < line.length(); i++) {
				if (Character.isLetter(line.charAt(i))) { //When current character is a letter:
					charLetter = true;
					if (i == line.length() - 1) //When the last character of the line is a letter:
						numWord++; //Increments the word count, since no words are split between lines.
				}
				else { //When current character is not a letter:
					if (charLetter) //If previous character was a letter:
						numWord++; //Increments the word count.
					if (line.charAt(i) == ' ') //If current character is a space:
						numSpace++; //Increments space count.
					charLetter = false;
				}
			}
		}
		br.close();
	}
	public int getTotalWords () {
		return numWord;
	}
	public int getTotalLines() {
		return numLine;
	}
	public int removedSpaces() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(inD)); //Reading input file.
		String line; //Used to read line from file.
		int inSpaces = 0; //Represents number of spaces in the input file.
		while ((line = br.readLine()) != null) { //Reads all lines of the input file.
			for (int i = 0; i < line.length(); i++) { //Iterates through each character in the line.
				if (line.charAt(i) == ' ')
					inSpaces++; //Increments space count when current character is a space.
			}
		}
		br.close();
		return inSpaces - numSpace; //Returns number of spaces removed from the input file.
	}
	public double wordsPerLine() {
		return (double)numWord / numLine;
	}
	public double linesLength() {
		return (double)numChar / numLine;
	}
}
