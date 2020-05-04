package dataLayer;

import java.io.*;
import java.util.*;

import businessLayer.MenuItem;

public class RestaurantSerialization {

	
	public ArrayList<MenuItem> deserialization(String fileName)
	{
		ArrayList<MenuItem> menu=new ArrayList<MenuItem>();
		try {
			FileInputStream file = new FileInputStream(fileName);
			
			if(file.available()!=0)
			{
			ObjectInputStream in= new ObjectInputStream(file);
			menu=(ArrayList)in.readObject();	
			in.close();
			}
			file.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return menu;
	}
	
	public void serialization(String fileName,ArrayList<MenuItem> menu)
	{
		FileOutputStream file;
		try {
			file = new FileOutputStream(fileName);
			ObjectOutputStream out=new ObjectOutputStream(file);
			out.writeObject(menu);
			out.close();
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
