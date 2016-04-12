import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseVM {
	private String command;
	private Scanner code;
	public static ArrayList<String> arithmetics = new ArrayList<String>();
	static{
		arithmetics.add("push");
		arithmetics.add("add");
		arithmetics.add("sub");
		arithmetics.add("neg");
		arithmetics.add("eq");
		arithmetics.add("gt");
		arithmetics.add("lt");
		arithmetics.add("and");
		arithmetics.add("or");
		arithmetics.add("not");
	}
	
	
	public ParseVM(File fileIn){
		try{
		code = new Scanner(fileIn);
		String curLine="";
		String readIn="";
		
		while(code.hasNext()){
            curLine = code.nextLine();
            if (curLine.length() > 0) {
                readIn += curLine;
			}
		}
		code = new Scanner(readIn);
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	}
	
	public boolean hasMoreCommands(){
		return code.hasNextLine();
	}
	
	public void advance(){
		command = code.nextLine();
		
	}
	public void parseCommand(){
		String[] sections = command.split(" ");
		
		switch(sections[0]){
			case "add": case "sub": case "neg": case "eq": case "gt": case "lt": case "and": case "or": case "not":
				writeArithmetic("add");
				break;
			case "push": case "pop":
				writePushPop("push")
				break;
	}
	/*public commandType(){
		
		}	
	}*/
}