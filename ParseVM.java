import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseVM {
	
	public ParseVM(File fileIn){
		code = new Scanner(fileIn);
		String curLine;
		String readIn;
		while(code.hasNext()){
            curLine = noComments(code.nextLine());
            if (line.length() > 0) {
                readIn += curLine;
			}
		}
			
	}
	
	public boolean hasMoreCommands(){
		return code.hasNextLine();
	}
	
	public void advance(){
		command = code.nextLine();
		
	}
	public command type
	
}