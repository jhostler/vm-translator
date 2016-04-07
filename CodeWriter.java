import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class CodeWriter {
	
	private PrintWriter translate;
	
	public CodeWriter(File output){
		try{
			translate = new PrintWriter(output);
			translate.print("blank");
		
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	}
	
	public void setFileName(String fileName){
		
	}
	
	public void writeArithmetic(String command){

        if (command.equals("add")){

            translate.print("@SP\nAM=M-1\nD=M\nA=A-1\nM=M+D\n");

        }else if (command.equals("sub")){

            translate.print("@SP\nAM=M-1\nD=M\nA=A-1\nM=M-D\n");

        }else if (command.equals("and")){

            translate.print("@SP\nAM=M-1\nD=M\nA=A-1\nM=M&D\n");

        }else if (command.equals("or")){

            translate.print("@SP\nAM=M-1\nD=M\nA=A-1\nM=M|D\n");

        }
	}
	
	public void writePushPop(command, String segment, Int index){
		
	}
	
	public void close(){
		translate.close();
	}
}