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
private static final Object lock = new Object();    
    public WorkerThread(String s){
        this.command=s;
    }

    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
            processCommand();	
//        System.out.println(Thread.currentThread().getName()+" End.");
    }

    private void processCommand() {
        try {
        	final File folder = new File("C:\\Users\\sony\\Desktop\\multiThread\\src");
        	FileReader  fr = null;
        	FileWriter fw= null;
        	for (final File fileEntry : folder.listFiles()) {
        		/* if (fileEntry.isFile()) {
                	if(!entryLst.contains(fileEntry.getName())){
                		System.out.println(Thread.currentThread().getName()+" Start. File Archiving  = "+fileEntry.getName());
                		archiveFile(fileEntry.getName());
                		entryLst.add(fileEntry.getName());
                	}
                	}
                	*/
        		boolean startReading = false;
        		synchronized (lock) {
                	/*try{
                		fr = new FileReader("C:\\Users\\sony\\Desktop\\multiThread\\ctlfiles\\"+fileEntry.getName()+".CTL");
                		
                	}catch (FileNotFoundException e) {
                			try {
								fw = new FileWriter("C:\\Users\\sony\\Desktop\\multiThread\\ctlfiles\\"+fileEntry.getName()+".CTL");
							} catch (IOException e1) {
								//
							}
                			startReading= true;
                	}*/
        			
        			if(!entryLst.contains(fileEntry.getName())){
//                		System.out.println(Thread.currentThread().getName()+" Start. File Archiving  = "+fileEntry.getName());
//                		archiveFile(fileEntry.getName());
                		entryLst.add(fileEntry.getName());
                		startReading=true;
                	}
        			
        		}
                	if(startReading){
                		System.out.println(Thread.currentThread().getName()+" Start. File Archiving  = "+fileEntry.getName());
                		archiveFile(fileEntry.getName());
                		  Thread.sleep(2000);
                	}
                	
//                } else if (entryLst.size()== folder.listFiles().length) {
//                    System.out.println("All files read complete");
//                }
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
//			System.out.println("Archived file name :"+name);
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
