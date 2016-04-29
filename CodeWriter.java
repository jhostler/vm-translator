import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class CodeWriter {
	
	private PrintWriter translate;
	private int jumpCount;
	
	public CodeWriter(File output){
		try{
			translate = new PrintWriter(output);
			jumpCount = 0;
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

        }else if (command.equals("eq")){
			
			translate.print("@SP\nAM=M-1\nD=M\nA=A-1\nD=M-D\n@TRUE"+jumpCount+"\nD;JEQ\n@FALSE"+jumpCount+"\n0;JMP\n(TRUE"+jumpCount+")\n@SP\nA=M-1\nM=-1\n@END"+jumpCount+"\n0;JMP\n(FALSE"+jumpCount+")\n@SP\nA=M-1\nM=0\n(END"+jumpCount+")\n");
			
			jumpCount++;
		}else if (command.equals("lt")){
			
			translate.print("@SP\nAM=M-1\nD=M\nA=A-1\nD=M-D\n@TRUE"+jumpCount+"\nD;JLT\n@FALSE"+jumpCount+"\n0;JMP\n(TRUE"+jumpCount+")\n@SP\nA=M-1\nM=-1\n@END"+jumpCount+"\n0;JMP\n(FALSE"+jumpCount+")\n@SP\nA=M-1\nM=0\n(END"+jumpCount+")\n");
			
			jumpCount++;
		}else if (command.equals("gt")){
			
			translate.print("@SP\nAM=M-1\nD=M\nA=A-1\nD=M-D\n@TRUE"+jumpCount+"\nD;JGT\n@FALSE"+jumpCount+"\n0;JMP\n(TRUE"+jumpCount+")\n@SP\nA=M-1\nM=-1\n@END"+jumpCount+"\n0;JMP\n(FALSE"+jumpCount+")\n@SP\nA=M-1\nM=0\n(END"+jumpCount+")\n");
			
			jumpCount++;
		}
		else if (command.equals("not")){
			translate.print("@SP\nM=M-1\nA=M\nM=!M\n@SP\nM=M+1\n");
		
		}
		else if (command.equals("neg")){
			translate.print("@0\nD=A\n@SP\nM=M-1\nA=M\nM=D-M\n@SP\nM=M+1\n");
				
		}
		else{
			translate.print("no type match\n");
		}
		
	}
	
	public void writeLabel(String command, String location){
		if (command.equals("label")){
			translate.print("("+location+")\n");
		}
		else if (command.equals("if-goto")){
			translate.print("@SP\nAM=M-1\nD=M\nA=A-1\n@"+location+"\nD;JNE\n");
		}
	else if (command.equals("goto")){
			translate.print("@"+location+"\n0;JMP\n");
		
		}
		else{
			translate.print("no type match");
		}
	}
	
	public void writePushPop(String command, String segment, int index){
		if(command.equals("push")){
			if (segment.equals("constant")){
                translate.print("@" + index + "\n" + "D=A\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
			}
			else if (segment.equals("local")){
				translate.print("@LCL\nD=M\n@" + index + "\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
			}
			else if (segment.equals("argument")){
				translate.print("@ARG\nD=M\n@" + index + "\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
			}
			else if (segment.equals("this")){
				translate.print("@THIS\nD=M\n@" + index + "\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
			}
			else if (segment.equals("that")){
				translate.print("@THAT\nD=M\n@" + index + "\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
			}
			else if (segment.equals("temp")){
				translate.print("@R5\nD=M\n@" + (index+5) + "\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
			}
			else if (segment.equals("pointer")){
				if (index==0){
					translate.print("@THIS\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
				}
				else if (index==1){
					translate.print("@THAT\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
				}
			}
			else if (segment.equals("static")){
				translate.print("@" + String.valueOf(16+index) + "\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
			}
		}
		else if(command.equals("pop")){
			
			if (segment.equals("local")){
				translate.print("@LCL\nD=M\n@"+ index +"\nD=D+A\n@R13\nM=D\n@SP\nA=M-1\nD=M\n@R13\nA=M\nM=D\n@SP\nM=M-1\n");
			}
			else if (segment.equals("argument")){
				translate.print("@ARG\nD=M\n@"+ index +"\nD=D+A\n@R13\nM=D\n@SP\nA=M-1\nD=M\n@R13\nA=M\nM=D\n@SP\nM=M-1\n");
			}
			else if (segment.equals("this")){
				translate.print("@THIS\nD=M\n@"+ index +"\nD=D+A\n@R13\nM=D\n@SP\nA=M-1\nD=M\n@R13\nA=M\nM=D\n@SP\nM=M-1\n");
			}
			else if (segment.equals("that")){
				translate.print("@THAT\nD=M\n@"+ index +"\nD=D+A\n@R13\nM=D\n@SP\nA=M-1\nD=M\n@R13\nA=M\nM=D\n@SP\nM=M-1\n");
			}
			else if (segment.equals("temp")){
				translate.print("@R5\nD=M\n@"+ (index+5) +"\nD=D+A\n@R13\nM=D\n@SP\nA=M-1\nD=M\n@R13\nA=M\nM=D\n@SP\nM=M-1\n");
			}
			else if (segment.equals("pointer")){
				if (index==0){
					translate.print("@THIS\nD=A\n@R13\nM=D\n@SP\nA=M-1\nD=M\n@R13\nA=M\nM=D\n@SP\nM=M-1\n");
				}
				else if (index==1){
					translate.print("@THAT\nD=A\n@R13\nM=D\n@SP\nA=M-1\nD=M\n@R13\nA=M\nM=D\n@SP\nM=M-1\n");
				}
			}
			else if (segment.equals("static")){
				translate.print("@"+ String.valueOf(index+16) + "\nD=A\n@R13\nM=D\n@SP\nA=M-1\nD=M\n@R13\nA=M\nM=D\n@SP\nM=M-1\n");
			}
		}
	}


	public void close(){
		translate.close();
	}
}