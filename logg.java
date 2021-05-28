package t1;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Arrays;
import java.awt.EventQueue;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.TextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ActionEvent;
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

public class logg {

	private JFrame frame;
	public static JTextField t2;
	public static TextArea t3;

	File[] paths;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logg window = new logg();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public logg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 787, 670);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 745, 597);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		t2 = new JTextField();
		t2.setBounds(252, 118, 116, 22);
		panel.add(t2);
		t2.setColumns(10);
		
		JLabel l3 = new JLabel("Directory :");
		l3.setFont(new Font("Tahoma", Font.BOLD, 18));
		l3.setBounds(75, 113, 116, 29);
		panel.add(l3);
		
		TextArea t3 = new TextArea();
		t3.setBounds(10, 194, 725, 393);
		panel.add(t3);
		
		JButton b1 = new JButton("Display Results :");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				 int filecount=0;
					String directory=t2.getText();		
				//	String directory_comp=company.getText();
					File dir = new File(directory);
				//	File dir2 = new File(directory_comp);
				    File[] fileslist = dir.listFiles(new FileFilterer());
				    
				//    File[] fileslisting_other = dir2.listFiles(new FileFilterer());
				    Otherscan b;
				    FileScanning bb;
				    
				     b =new Otherscan();
				   //  bb =new FileScanning();
				    
				    b.FileScann(fileslist);
				    b.tfIdfCalculator();
				    
				   String[] namess  = new String[10];
				    int i=0;
				   String lll="";
				    
				    
				    for (File f : fileslist)
				    {
				    	namess[i]=f.getName();
				    	System.out.println(namess[i]);
				    	
				    	lll=lll+" "+f.getName();
				    	
				    	i++;
				    }
				    
				String[] splitedd = lll.split(" ");
				System.out.println(splitedd[0]);
				    
				    double[] aa = b.getCosineSimilarity();
				    int lenn=aa.length;
				//bb.FileScanning_new(fileslisting_other);
				   int le=1;
				    for(int k=0; k<lenn;k++)
		            {
		           	  String kk="";
		           	  kk=kk+aa[k];
		           	  String s2="";
		           	  s2 +=namess[le];
				      t3.append(" "+s2+"    -------->    "+kk+"\n");
				       le++;
	
		            }
				    
				  
				    Connection com = null;
					try {
				Class.forName("com.mysql.jdbc.Driver");//driver name 
					}
					catch(ClassNotFoundException e1)
					{
						e1.printStackTrace();
					}
					try {
						 com=DriverManager.getConnection("jdbc:mysql://localhost:3306/resume","root","sudhir");
						
						  
						Statement st=com.createStatement();
						
						
						
					     PreparedStatement ps=com.prepareStatement("insert into file3(name) values(?)");
					    // Array array = com.createArrayOf("VARCHAR", splitedd);
				        // ps.setArray(1, array);
					     ps.setString(1,t3.getText());
					    
					     
						 ps.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					finally
					{
						try {
							com.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
						
					
				    
				  
				    
				    
				    
				    
				    
			}
		});
		b1.setBounds(520, 92, 161, 41);
		
		panel.add(b1);
		
		JLabel lblResumeRecommendation = new JLabel("Resume Recommendation System");
		lblResumeRecommendation.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblResumeRecommendation.setBounds(75, 13, 490, 66);
		panel.add(lblResumeRecommendation);
		
		JLabel lblTfidf = new JLabel("Tf-idf");
		lblTfidf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTfidf.setBounds(541, 159, 77, 29);
		panel.add(lblTfidf);
		
		JLabel lblFileName = new JLabel("File Name");
		lblFileName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFileName.setBounds(85, 157, 135, 33);
		panel.add(lblFileName);
		
	}
}
