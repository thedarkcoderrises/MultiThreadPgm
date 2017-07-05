package com.threadpool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkerThread implements Runnable {
  
    private String command;
    private static List<String> entryLst = new ArrayList<>();
    public WorkerThread(String s){
        this.command=s;
    }

    @Override
    public void run() {
            processCommand();	
    }

    private void processCommand() {
        try {
        	final File folder = new File("C:\\Users\\sony\\Desktop\\multiThread\\src");
        	for (final File fileEntry : folder.listFiles()) {
        		boolean startReading = false;
        		synchronized (entryLst) {
        			if(!entryLst.contains(fileEntry.getName())){
                		entryLst.add(fileEntry.getName());
                		startReading=true;
                	}
        		}
                	if(startReading){
                		System.out.println(Thread.currentThread().getName()+" Start. File Archiving  = "+fileEntry.getName());
                		archiveFile(fileEntry.getName());
                		  Thread.sleep(2000);
                	}
            }
          
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    private void archiveFile(String name) {
    	BufferedReader br = null;
		FileReader fr = null;
		
		BufferedWriter bw = null;
		FileWriter fw = null;

		String sCurrentLine;

		try {
			fr = new FileReader("C:\\Users\\sony\\Desktop\\multiThread\\src\\"+name);
			br = new BufferedReader(fr);
			fw = new FileWriter("C:\\Users\\sony\\Desktop\\multiThread\\dest\\"+name);
			bw = new BufferedWriter(fw);
			while ((sCurrentLine = br.readLine()) != null) {
				bw.write(sCurrentLine);
				bw.write("\\\n");
			}
			System.out.println(Thread.currentThread().getName()+" End. File Archiving  = "+name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
    	
	}

	@Override
    public String toString(){
        return this.command;
    }
}
