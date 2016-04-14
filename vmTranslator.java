import java.io.File;
 
 public class vmTranslator {
	
	public static void main(String[] args) {
			
        if (args.length == 0){

            System.out.println("No Filename");
            return;

        }
		else{
			
			File fileIn = new File(args[0]);
			
			ParseVM parser = new ParseVM(fileIn);
			
			while(parser.hasMoreCommands()){
				parser.parseCommand();
				parser.advance();
			}
			
			String fileName = fileIn.getName();
			String path = fileIn.getAbsolutePath();         //create .asm file 
			
			String fileOutPath = fileIn.getAbsolutePath().substring(0, fileIn.getAbsolutePath().lastIndexOf(".")) + ".asm";
			
			File fileOut = new File(fileOutPath);

			CodeWriter translate;
			translate = new CodeWriter(fileOut);			
			translate.close();
			
		}
    
	}
	
}