import java.io.File;
 
 public class vmTranslator {
	
	public static void main(String[] args) {
			
        if (args.length == 0){

            System.out.println("No Filename");
            return;

        }
		else{
			
			File fileIn = new File(args[0]);
			System.out.println("start");
			
			String fileName = fileIn.getName();
			String path = fileIn.getAbsolutePath();         //create .asm file 
			
			String fileOutPath = fileIn.getAbsolutePath().substring(0, fileIn.getAbsolutePath().lastIndexOf(".")) + ".asm";
			
			File fileOut = new File(fileOutPath);

			CodeWriter translate;
			translate = new CodeWriter(fileOut);			

			
			ParseVM parser = new ParseVM(fileIn);
			String type = "";
			while(parser.hasMoreCommands()){
				
				parser.parseCommand();
				System.out.println(parser.command());
				
				type = parser.commandType();
				
				if(type.equals("arithmetic")){
					System.out.println("Arith");
					translate.writeArithmetic(parser.arg1());
				} else if (type.equals("push") || type.equals("pop")) {
					System.out.println("PuPo");
					translate.writePushPop(type, parser.arg1(), parser.arg2());
					
				}else if (type.equals("label") || type.equals("if-goto") || type.equals("goto")){
					System.out.println("Branch");
					translate.writeLabel(type, parser.arg1());
				}
			}
			System.out.println("done");
			
		translate.close();
			
		}
    
	}
	
}