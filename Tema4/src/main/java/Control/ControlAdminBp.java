package Control;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import businessLayer.BaseProduct;
import businessLayer.IRestaurantProcessing;
import businessLayer.MenuItem;
import exceptionLayer.BadInput;
import view.ViewAdminBp;

public class ControlAdminBp {

	private ViewAdminBp view;
	private IRestaurantProcessing restaurant;
	
	
	private List<String> bpSelected=new ArrayList<String>();
	public ControlAdminBp(IRestaurantProcessing restaurant,ViewAdminBp view)
	{
		this.restaurant=restaurant;
		this.view=view;
		this.view.listListener(new ListListener());
		this.view.createListener(new CreateListener());
		this.view.editListener(new EditListener());
		this.view.removeListener(new RemoveListener());
	}
	
	public MenuItem searchInMenu()
	{
		for(MenuItem item:restaurant.getMenu())
        {
        	if(item instanceof BaseProduct)
        	{
        		if(bpSelected.size()>0)
        		{
        			if(item.getName().equals(bpSelected.get(0)))
                	{
                		return item;
                	}
        		}
        		
        	}
        	
        }
		return null;
	}
	class ListListener implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()) {
	            List<String> selectedValuesList = view.getBaseProdList().getSelectedValuesList();
	            bpSelected.clear();
	            for(String x: selectedValuesList)
	            {
	            	bpSelected.add(x);
	            	
	            }
	            
	           MenuItem item=searchInMenu();
	           if(item!=null) {
	        	   view.getNameTF().setText(item.getName());
			       view.getPriceTF().setText(((BaseProduct)item).computePrice()+"");
	           }
		       
		            		
		            
		}
		
	}

	
	}
	
	class CreateListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String name="";
			name=view.getNameTF().getText();
			String price="";
			price=view.getPriceTF().getText();
			
			try
			{
				if(name.equals("") ||price.equals(""))
				{
					throw new BadInput("Nu au fost completate toate casutele pentru a se putea realiza CREATE!");
				}
				
				double p=Double.parseDouble(price);
				
				MenuItem nou=new BaseProduct(name,p);
				
				restaurant.createMenuItem(nou);
				view.updateList(name);
				
			}catch(NumberFormatException ex)
			{
				view.showError("Nu s-a introdus o valoare valida pentru pret!");
			}
			catch(BadInput ex)
			{
				view.showError(ex.getMessage());
			}
			catch(AssertionError er)
			{
				view.showError("Nu se poate adauga produsul deoarece acesta EXISTA deja!!!");
			}
			
			
			
		}
		
	}
class EditListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String name="";
			name=view.getNameTF().getText();
			String price="";
			price=view.getPriceTF().getText();
			
			MenuItem item=searchInMenu();
			
			try
			{
				if(!name.equals(bpSelected.get(0)))
				{
					throw new BadInput("Numele nu poate fi editat!");
				}
				
				double p=Double.parseDouble(price);
				
				MenuItem nou=new BaseProduct(name,p);
				
				restaurant.editMenuItem(item,nou);
		
				view.showError("Produsul "+name+" a fost editat cu succes!");
				
			}catch(NumberFormatException ex)
			{
				view.showError("Nu s-a introdus o valoare valida pentru pret!");
			}
			catch(BadInput ex)
			{
				view.showError(ex.getMessage());
			}
			catch(AssertionError er)
			{
				view.showError("Operatia de EDIT a esuat!!!");
			}
			
			
			
		}
		
	}

class RemoveListener implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		MenuItem item=searchInMenu();
		
		try
		{
			restaurant.deleteMenuItem(item);
			view.showError("Produsul "+item.getName()+" a fost sters cu succes!");
			view.notVisible();
			ViewAdminBp v=new ViewAdminBp(restaurant.getMenu());
		    ControlAdminBp control=new ControlAdminBp(restaurant,v);
			
		}
		catch(AssertionError er)
		{
			view.showError("Nu se poate realiza stergerea!!!");
		}
		
			
		
		
		
		
	}
	
}
}
