package sundance;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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
				WorkUnzip.unzipFile3(FileName);
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
	 
	 
	 
	 private void unzipFile3(String source) {
	        File dir = new File(destination);
	        if(!dir.exists()) dir.mkdirs();
	        ZipInputStream zis=null;
	        ZipEntry ze=null;
	        FileInputStream fis = null;
	        FileOutputStream fos=null;
	        
	        byte[] buffer = new byte[64*1024];

	        	try {
		            fis = new FileInputStream(downloadfolder+source);
		            zis = new ZipInputStream(fis);
		            ze = zis.getNextEntry();
	        	}catch(Exception e) {
	        	}
	            
	            while(ze != null){
	            	try {
		                String fileName = ze.getName();
		                File newFile = new File(destination + File.separator + fileName);
		                new File(newFile.getParent()).mkdirs();
		                fos = new FileOutputStream(newFile);
		                int len;
		                while ((len = zis.read(buffer)) > 0) {
		                fos.write(buffer, 0, len);
		                }
		                fos.close();
		                zis.closeEntry();
		                ze = zis.getNextEntry();
	            	}catch(Exception e) {
	            		System.out.println("there was a problem with file "+ze.getName()+e.getMessage());
	            	}
	            }
	            try {
		            zis.closeEntry();
		            zis.close();
		            fis.close();
	            }catch(Exception e) {
	            	
	            }
	            System.out.println("unziping file: "+source);   
	    }
	 
	 public void unzipFile2(String source) {
		 ZipInputStream zis = null;
	     ZipEntry zipEntry = null;
	     byte[] buffer = new byte[1024];
	     try {
		     zis = new ZipInputStream(new FileInputStream(downloadfolder+source));
		     zipEntry = zis.getNextEntry();
	     }catch(Exception e) {
		 }
	     while (zipEntry != null) {
	    	 try {
		    	 File newFile = new File(destination + File.separator + zipEntry.getName());
		    	 new File(newFile.getParent()).mkdirs();
		         FileOutputStream fos = new FileOutputStream(newFile);
		         int len;
		         while ((len = zis.read(buffer)) > 0) {
		              fos.write(buffer, 0, len);
		         }
		         fos.close();
		         zipEntry = zis.getNextEntry();
	    	 }catch(Exception e) {
				 System.out.println("there was a problem unziping file "+zipEntry.getName()+e.getMessage());
			 }
	      }
	     try {
	      zis.closeEntry();
	      zis.close();
	     }catch(Exception e) {
		 }
	      System.out.println("unziping file: "+source);
		 
	 }
	 
	

}
