package com.sundance.downloadFiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;


public class TestZip {
	String downloadfolder="/Users/jesusromollamas/Desktop/Zip/";
	String destination = "/Users/jesusromollamas/Desktop/Files_Unzip/";

	public static void main(String[] args) throws IOException {
		
		String FileName="";
		TestZip WorkUnzip = new TestZip();
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
	
	
	public void unzipFile3(String source) throws IOException {
		ZipFile zipFile = new ZipFile(downloadfolder+source);
		try {
		  Enumeration<? extends ZipEntry> entries = zipFile.entries();
		  while (entries.hasMoreElements()) {
		    ZipEntry entry = entries.nextElement();
		    File entryDestination = new File(destination,  entry.getName());
		    if (entry.isDirectory()) {
		        entryDestination.mkdirs();
		    } else {
		        entryDestination.getParentFile().mkdirs();
		        InputStream in = zipFile.getInputStream(entry);
		        OutputStream out = new FileOutputStream(entryDestination);
		        IOUtils.copy(in, out);
		        IOUtils.closeQuietly(in);
		        out.close();
		    }
		  }
		  System.out.println("Done File "+source);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
		  zipFile.close();
		}
	}

}
