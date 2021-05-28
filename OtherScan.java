package t1;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class Otherscan {
	public List<String[]> termsDAr = new ArrayList<String[]>();
    public List<String> all = new ArrayList<String>(); //to hold all terms
    public List<double[]> tfidfDocsV = new ArrayList<double[]>();
     
   //output array values 
  //  public List<double[]> keep = new ArrayList<double[]>();
	public void FileScann(File fileslist[])
	{

	try
	{
		   		
			//FileWriter foutput=new FileWriter("C:\\Users\\Dell\\OneDrive\\Desktop\\java project\\file\\Res.docx");
		int i=0;
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
			                 
			                  
			                
			                 
			                  
			              //    System.out.println("Working");
			                  for(String term : tokenizedTerms)	//avoid duplicate entries
			                  {
			                  	if(!all.contains(term))
			                  	{
			                  		all.add(term);
			                  		
			                  	}
			                  }
			                  termsDAr.add(tokenizedTerms);
			                  
			                  }
			              
			              
			              
					       
					}
				
					catch(Exception ae){
					   ae.printStackTrace();}
					
					
				}}}}
	catch (Exception exep)
      {
          exep.printStackTrace();
      }  

	
}
	
	
	public void tfIdfCalculator() 
    {
        double tf; //term frequency
        double idf; //inverse document frequency
        double tfidf; //term frequency inverse document frequency        
        for (String[] docTermsArray : termsDAr) 
        {
            double[] tfidfvectors = new double[all.size()];
            int count = 0;
            for (String terms : all) 
            {
                tf = new TfIdf().tfCalculator(docTermsArray, terms);
                idf = new TfIdf().idfCalculator(termsDAr, terms);
               
                
                tfidf = tf * idf;
           //     System.out.println(terms);
           //     System.out.println(tfidf);
                
                tfidfvectors[count] = tfidf;
                
                count++;
               
            }
            tfidfDocsV.add(tfidfvectors);  //storing document vectors;            
        }
    }
	
	public double[] getCosineSimilarity() 
    {
		int vall=0;
		
		 double[] keepss = new double[tfidfDocsV.size()];
		
            for (int k = 1; k < tfidfDocsV.size(); k++) 
            {
	           
	             keepss[vall]= new cosineSimilarity().cosineSimilarit(tfidfDocsV.get(0),  tfidfDocsV.get(k));
	             vall++;
            }
        
        //   for(int k=0;k<vall;k++)
        //    {
        //   	System.out.println(keepss[k]);
        //    }
           
           return keepss;
    }
	}
