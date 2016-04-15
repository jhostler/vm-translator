import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class CodeWriter {
	
	private PrintWriter translate;
	private int jumpFlag;
	
	public CodeWriter(File output){
		try{
			translate = new PrintWriter(output);
			jumpFlag = -1;
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

        }/*else if (command.equals("gt")){
		
		else{
			translate.print("no type match");
		}*/
		
	}
	
	public void writePushPop(String command, String segment, int index){
		if(command.equals("push")){
			if (segment.equals("constant")){
                translate.print("@" + index + "\n" + "D=A\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
			}
		}
	}


	public void close(){
		translate.close();
	}
}