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
	private String comType;
	private String arg1;
	private int arg2;
	public static ArrayList<String> arithmetics = new ArrayList<String>();
	static{
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
            curLine = noComments(code.nextLine()).trim();
            if (curLine.length() > 0) {
                readIn += curLine + "\n";
			}
		}
		code = new Scanner(readIn.trim());
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	}
	
	public boolean hasMoreCommands(){
		return code.hasNextLine();
	}
	public void parseCommand(){
		
		command = code.nextLine(); 
		arg1 = "";
		arg2 = 1;
		
		String[] sections = command.split(" ");
		
		
		if (arithmetics.contains(sections[0])){
			comType = "arithmetic";
			arg1 = sections[0];
		}
		else if (sections[0].equals("return")){
			comType = "return";
			arg1 = sections[0];
		}
		else{ 
			arg1 = sections[1];
			//arg2 = Integer.parseInt((sections[2]));
			
			if (sections[0].equals("push")){
				comType = "push";
				arg2 = Integer.parseInt((sections[2]));
			}
			else if (sections[0].equals("pop")){
				comType = "pop";
				arg2 = Integer.parseInt((sections[2]));
			}
			else if (sections[0].equals("label")){
				comType = "label";
			}
			else if (sections[0].equals("if")){
				comType = "if";
			}
			else if (sections[0].equals("goto")){
				comType = "goto";
			}
		else if (sections[0].equals("if-goto")){
					comType = "if-goto";
			}
			else if (sections[0].equals("function")){
				comType = "function";
			}
			else if (sections[0].equals("call")){
				comType = "call";
			}
		}
	}
	public static String noComments(String strIn){

        int position = strIn.indexOf("//");

        if (position != -1){

            strIn = strIn.substring(0, position);

        }

        return strIn;
    }
	public String commandType(){
		return comType;
	}
	public String arg1(){
		return arg1;
	}
	public int arg2(){
		return arg2;
	}
	public String command(){
		return command;
	}
}