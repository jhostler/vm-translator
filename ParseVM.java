import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseVM {
	
	public ParseVM(File fileIn){
		try	{
			Scanner code = new Scanner(fileIn);
			String curLine;
			String readIn = "";
			while(code.hasNext()) {
            curLine = inputFunc.noComments(code.nextLine());
            if (curLine.length() > 0) {
                readIn += curLine;
				}
			}
		}
		
		
		
		catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	}
	
	public boolean hasMoreCommands(Scanner code){
		return code.hasNextLine();
	}
	
	public void advance(Scanner code){
		code.nextLine();
		
	}
	//public command type
	
}