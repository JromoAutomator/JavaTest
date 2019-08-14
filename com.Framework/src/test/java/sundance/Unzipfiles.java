package sundance;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Unzipfiles {
	String downloadfolder="/Users/jesusromollamas/Desktop/Zip/";
	String destination = "/Users/jesusromollamas/Desktop/Files_Unzip/"; 

	public static void main(String[] args) {
		String FileName="";
		Unzipfiles WorkUnzip = new Unzipfiles();
		File folder = new File(WorkUnzip.downloadfolder);
		File[] fileNames = folder.listFiles();
		System.out.println("found "+(fileNames.length-1)+" zip files on current directory");
		for(File file : fileNames){
			FileName=file.getName();
			if(FileName.contains(".zip")) {
				WorkUnzip.unzipFile(FileName);
			}
			
        }
		System.out.println("Done, Files ready at "+WorkUnzip.destination);

	}
	
	 public String unzipFile(String source) {
		  try {
		      ZipFile zipFile = new ZipFile(downloadfolder+source);
		      zipFile.extractAll(destination);
		      System.out.println("unziping file: "+source);
		  } catch (ZipException e) {
		      e.printStackTrace();
		  }
		  return destination;
	  }

}
