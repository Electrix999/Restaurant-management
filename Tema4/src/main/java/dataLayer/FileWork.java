package dataLayer;

import java.io.*;
import java.util.ArrayList;

import businessLayer.MenuItem;

public class FileWork {
	
	public void createFile(int ordID)
	{
		File file=new File("bon"+ordID+".txt");
		try {
			if(!file.createNewFile())
			{
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void makeBill(int ordId,double orderPrice,ArrayList<MenuItem> it)
	{
		try
		{
			FileWriter write=new FileWriter("bon"+ordId+".txt");
			write.write("Bill Order "+ordId+"\n\n");
			write.write("Items:\n");
			for(MenuItem x:it)
			{
				write.write(x.toString()+"\n");
			}
			write.write("\nTOTAL:"+orderPrice);
			write.close();
		}catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
	}

}
