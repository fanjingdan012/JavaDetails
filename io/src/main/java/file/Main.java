package file;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main{
	public static final String DATE="0606";

	private static void findErrorsInFile(File theFile,PrintWriter writer){


		LineIterator it = null;
		try {
			it = FileUtils.lineIterator(theFile, "UTF-8");
			int lineNumber = 0;
			while (it.hasNext()) {
				String line = it.nextLine();
				lineNumber++;
				//System.out.println(line);
				if(StringUtils.isEmpty(line)||line.startsWith("^")||line.startsWith("Date")){
					continue;
				}
				if(!line.startsWith("2017")){
					writer.println(lineNumber+" "+line);
					continue;
				}
				String[] logComponents = line.split("\\t");
				if(logComponents.length<5){
					writer.println(lineNumber+" "+line);
					continue;
				}
				String level = logComponents[2];
				/*for(String logComponent:logComponents){
					System.out.println(logComponent);
				}*/
				if("ERROR".equals(level)){
					writer.println(lineNumber+" "+line);
					continue;
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		} finally{
			LineIterator.closeQuietly(it);
		}
	}
	public static void main(String[] args){
		File dir = new File("C:\\log\\perf\\"+DATE+"\\");
		File[] listOfFiles = dir.listFiles();
		PrintWriter writer=null;
		try {
			writer = new PrintWriter("C:\\log\\perf\\"+DATE+".txt", "UTF-8");
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					writer.println("File " + listOfFiles[i].getName());
					findErrorsInFile(listOfFiles[i],writer);
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("Directory " + listOfFiles[i].getName());
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			if(writer!=null) {
				writer.close();
			}
		}


	}
}