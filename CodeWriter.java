import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class CodeWriter {
	
	private PrintWriter translate;
	
	public CodeWriter(File output){
		try{
			translate = new PrintWriter(output);
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	}
	
	/*public void setFileName(String fileName){
		
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

        }else if (command.equals("gt")){
			
			translate.print("@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "A=A-1\n" +
                "D=M-D\n" +
                "@FALSE" + jumpFlag + "\n" +
                "D;JLE\n" +
                "@SP\n" +
                "A=M-1\n" +
                "M=-1\n" +
                "@CONTINUE" + jumpFlag + "\n" +
                "0;JMP\n" +
                "(FALSE" + jumpFlag + ")\n" +
                "@SP\n" +
                "A=M-1\n" +
                "M=0\n" +
                "(CONTINUE" + jumpFlag + ")\n";)
		
	}
	
	public void writePushPop(String command, String segment, int index){
		
	}*/
	
	public void close(){
		translate.close();
	}
}