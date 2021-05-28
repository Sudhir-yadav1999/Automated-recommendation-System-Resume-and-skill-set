package t1;



import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class FileScanning {
	
	public void FileScanning_new(File fileslist[])
	{

	try
	{
		   		
			//FileWriter foutput=new FileWriter("C:\\Users\\Dell\\OneDrive\\Desktop\\java project\\file\\Res.docx");
		
			for (File f : fileslist)
		    {
				if(f.isFile())
				{
				if(f.getName().endsWith(".pdf"))
				{
					
					try (PDDocument document = PDDocument.load(f))
					{
																
			              document.getClass();
			              if (!document.isEncrypted()) 
			              {   
			            	  PDFTextStripperByArea stripper = new PDFTextStripperByArea(); 
			                  stripper.setSortByPosition(true);
			                  
			                  PDFTextStripper tStripper = new PDFTextStripper();
			                  
			                  String pdfFileInText = tStripper.getText(document);
			                  
			                  //String lines[] = pdfFileInText.split("\\r?\\n");
			                  
			                 // StringBuilder sb = new StringBuilder();
			                  
			                  String s = null;
			                  
			                  String[] tokenizedTerms = pdfFileInText.toString().replaceAll("[\\W&&[^\\s]]", "").split("\\W+");   //to get individual terms
			                  int i;
			                  
			                  for(i=0;i<tokenizedTerms.length;i++)
			                  {
			                	  System.out.println(tokenizedTerms[i]);
			                  }
			                  
			                  
			               
			                  
			                  //System.out.println("Working");
			                 /* for(String term : tokenizedTerms)	//avoid duplicate entries
			                  {
			                  	if(!allTerms.contains(term))
			                  	{
			                  		allTerms.add(term);
			                  		System.out.println(term);
			                  	}
			                  }
			                  termsDocsArray.add(tokenizedTerms);
			                  
			                  */
			                }
			              
			              
			              
					       
					}
				
					catch(Exception ae){
					   ae.printStackTrace();}
					
					
				}}}}
	catch (Exception exep)
      {
          exep.printStackTrace();
      }  

	
}}
