package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.IRestaurantProcessing;
import businessLayer.MenuItem;
import view.ViewAdmin;
import view.ViewAdminBp;
import view.ViewAdminCp;

public class ControlAdmin {
	
	private ViewAdmin view;
	private IRestaurantProcessing restaurant;

	
	
	
	public ControlAdmin(IRestaurantProcessing restaurant,ViewAdmin view)
	{
		this.restaurant=restaurant;
		this.view=view;
		this.view.menuBpListener(new MenuActionListener());
		this.view.menuCpListener(new MenuActionListener());
		this.view.showListener(new ShowListener());
	}
	
	
	
	public class MenuActionListener implements ActionListener {
		  public void actionPerformed(ActionEvent e) {
		    if(e.getActionCommand().equals("Base Products"))
		    {
		   
		    	ViewAdminBp v=new ViewAdminBp(restaurant.getMenu());
		    	ControlAdminBp control=new ControlAdminBp(restaurant,v);
		    	
		    }
		    
		    if(e.getActionCommand().equals("Composite Products"))
		    {
		   
		    	ViewAdminCp v=new ViewAdminCp(restaurant.getMenu());
		    	ControlAdminCp control=new ControlAdminCp(restaurant,v);
		    	
		    }

		  }
		}
	
	public class ShowListener implements ActionListener {
		  public void actionPerformed(ActionEvent e) {
		    if(e.getActionCommand().equals("Show menu"))
		    {
		    	ArrayList<MenuItem> menu=new ArrayList<MenuItem>();
		    	menu=restaurant.getMenu();
		    	JFrame frame=new JFrame();
		    	frame.setTitle("Menu table");
		    	String[] column= {"NAME","DESCRIPTION","PRICE"};
		    	String[][] data=new String[menu.size()][3];
		    	
		    	int i=0;
		    	for(MenuItem x:menu)
		    	{
		    		data[i][0]=x.getName();
		    		if(x instanceof BaseProduct)
		    		{
		    			data[i][1]=x.getName();
		    		}
		    		else
		    		{
		    			data[i][1]=((CompositeProduct)x).getDescription();
		    		}
		    		data[i][2]=x.computePrice()+"";
		    		i++;
		    	}
		    	
		    	JTable table=new JTable(data,column);
		    	table.setBounds(40, 40, 300, 300);
		    	JScrollPane sp = new JScrollPane(table);
		    	
		    	System.out.println("Sunt aici!");
		    	frame.add(sp);
		    	frame.setSize(600,200);
		    	frame.setVisible(true);
		    	
		    }
		    

		  }
		}

}


