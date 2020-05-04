package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import businessLayer.Restaurant;
import businessLayer.IRestaurantProcessing;
import businessLayer.MenuItem;
import businessLayer.Order;
import exceptionLayer.BadInput;
import view.ViewWaiter;

import java.util.*;

public class ControlWaiter {
	private ViewWaiter view;
	private IRestaurantProcessing restaurant;
	
	private List<String> selected=new ArrayList<String>();
	
	public ControlWaiter(IRestaurantProcessing rest,ViewWaiter v)
	{
		view=v;
		restaurant=rest;
		view.listListener(new ListListener());
		view.orderListener(new OrderListener());
		view.billListener(new BillListener());
		view.viewListener(new ShowListener());
	}
	
	class ListListener implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()) {
	            List<String> selectedValuesList = view.menuItemsList().getSelectedValuesList();
	            selected.clear();
	            for(String x: selectedValuesList)
	            {
	            	selected.add(x);
	            	
	            }
           
		}
		
	}

	}
	
	class OrderListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try
			{
			    int id=Integer.parseInt(view.getOrderTF().getText());
				int table=Integer.parseInt(view.getTableTF().getText());
				String date="";
				date=view.getDateTF().getText();
				
				if(table<1 && table >12)
				{
					throw new BadInput("Numarul mesei trebuie sa fie cuprins intre 1 si 12 inclusiv!");
				}
				
				if(date.equals(""))
				{
					throw new BadInput("Nu s-a introdus nico valoare in campul DATE!");
				}
				if(selected.size()<=0)
				{
					throw new BadInput("Nu s-au selectat produsele dorite!");
				}
				
				Order ord=new Order(id,date,table);
				ArrayList <MenuItem> menu=new ArrayList<MenuItem>();
				
				
				int i=0;
				for(MenuItem x:restaurant.getMenu())
				{
					
					if(x.getName().equals(selected.get(i)))
					{
						
						menu.add(x);
						i++;
						if(i==selected.size())
						{
							break;
						}
					}
				}
			
				restaurant.createOrder(ord, menu);
				view.showError("Comanda "+ord.getOrderID()+" s-a realizat cu succes!");

				
			}catch(NumberFormatException ex)
			{
				view.showError("Valoarea pentru campul ORDER ID sau pentru campul TABLE nu este valida!");
			}
			catch(BadInput ex)
			{
				view.showError(ex.getMessage());
			}
			catch(AssertionError er)
			{
				view.showError("Comanda nu s-a putut realiza!");
			}

		}
		
	}
	
	public class ShowListener implements ActionListener {
		  public void actionPerformed(ActionEvent e) {
		    Map<Order, ArrayList<MenuItem>> map=new HashMap<Order,ArrayList<MenuItem>>();
		    map=((Restaurant)restaurant).getMap();
		    	
		    Set<Order> orders=new HashSet<Order>();
		    orders=map.keySet();
		    	
		    System.out.println("Sunt AICI!");
		    JFrame frame=new JFrame();
		    frame.setTitle("Menu table");
		    String[] column= {"ORDER ID","PRODUCT","PRICE","TABLE"};
		    	
		    String[][] data=new String[orders.size()*10][4];
		    
		    int i=0;
		    for(Order x:orders)
		    {
		    	for(MenuItem m:map.get(x))
		    	{
		    		data[i][0]=x.getOrderID()+"";
		    		data[i][1]=m.getName()+"";
		    		data[i][2]=m.computePrice()+"";
		    		data[i][3]=x.getTable()+"";
		    		i++;
		    	}
		    		
		    }
		    	
		    JTable table=new JTable(data,column);
		    table.setBounds(40, 40, 300, 300);
		    JScrollPane sp = new JScrollPane(table);
		    	
		    	
		    frame.add(sp);
		    frame.setSize(600,200);
		    frame.setVisible(true);
		    	
		    
		    

		  }
		}
	
class BillListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try
			{
				int id=Integer.parseInt(view.getOrderTF().getText());
				int table=Integer.parseInt(view.getTableTF().getText());
				String date="";
				date=view.getDateTF().getText();
				if(id<=0)
				{
					throw new BadInput("ID-ul trebuie sa fie pozitiv");
				}
				if(table<1 && table >12)
				{
					throw new BadInput("Numarul mesei trebuie sa fie cuprins intre 1 si 12 inclusiv!");
				}
				if(date.equals(""))
				{
					throw new BadInput("Nu s-a introdus nico valoare in campul DATE!");
				}
				
				Order ord=new Order(id,date,table);
				
				restaurant.generateBill(ord);
				view.showError("Bonul s-a generat cu succes!");
				
				
			}catch(NumberFormatException ex)
			{
				view.showError("Valoarea pentru campul ORDER ID sau pentru campul TABLE nu este valida!");
			}
			catch(BadInput ex)
			{
				view.showError(ex.getMessage());
			}
			catch(AssertionError er)
			{
				view.showError("Nu s-a gasit comanda pentu care s-a solicitat bonul!");
			}

		}
		
	}
	
	
}


