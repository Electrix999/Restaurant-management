package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.IRestaurantProcessing;
import businessLayer.MenuItem;
import exceptionLayer.BadInput;
import view.ViewAdminCp;

public class ControlAdminCp {
	private ViewAdminCp view;
	private IRestaurantProcessing restaurant;
	
	private List<String> cpSelected=new ArrayList<String>();
	private List<String> bpSelected=new ArrayList<String>();
	
	
	public ControlAdminCp(IRestaurantProcessing restaurant,ViewAdminCp view)
	{
		this.restaurant=restaurant;
		this.view=view;
		this.view.listListenerBp(new ListListenerBp());
		this.view.listListenerCp(new ListListenerCp());
		this.view.createListener(new CreateListener());
		this.view.editListener(new EditListener());
		this.view.removeListener(new RemoveListener());
	}
	
	public MenuItem searchInMenu()
	{
		for(MenuItem item:restaurant.getMenu())
        {
        	if(item instanceof CompositeProduct)
        	{
        		if(cpSelected.size()>0)
        		{
        			if(item.getName().equals(cpSelected.get(0)))
                	{
                		return item;
                	}
        		}
        		
        	}
        	
        }
		return null;
	}
	class ListListenerCp implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()) {
	            List<String> selectedValuesList = view.getCompProdList().getSelectedValuesList();
	            cpSelected.clear();
	            for(String x: selectedValuesList)
	            {
	            	cpSelected.add(x);
	            	
	            }
	            
	           MenuItem item=searchInMenu();
	           if(item!=null) {
	        	   view.getNameTF().setText(item.getName());
	        	   view.getCompTF().setText(((CompositeProduct)item).getDescription()+"");
			       view.getPriceTF().setText(item.computePrice()+"");
	           }
           
		}
		
	}

	}
	
	class ListListenerBp implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()) {
	            List<String> selectedValuesList = view.getBaseProdList().getSelectedValuesList();
	            bpSelected.clear();
	            for(String x: selectedValuesList)
	            {
	            	bpSelected.add(x);
	            	
	            }
           
		}
		
	}

	}
	
	class CreateListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String name="";
			name=view.getNameTF().getText();
			
			System.out.println("Sunt aici!");
			try
			{
				if(name.equals(""))
				{
					throw new BadInput("Nu au fost completate toate casutele pentru a se putea realiza CREATE!");
				}
				
				if(bpSelected.size()==0)
				{
					throw new BadInput("Nu au fost selectate produsele de baza pentru CREATE!");
				}
				MenuItem nou=new CompositeProduct(name);
				
				int i=0;
				for(MenuItem x:restaurant.getMenu())
				{
					if(x.getName().equals(bpSelected.get(i)))
					{
						i++;
						((CompositeProduct)nou).addProduct(x);
						if(i==bpSelected.size())
						{
							break;
						}
					}
				}
				
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
			
			MenuItem item=searchInMenu();
			
			try
			{
				MenuItem nou=new CompositeProduct(name);
				if(bpSelected.size()!=0)
				{
				
					int i=0;
					for(MenuItem x:restaurant.getMenu())
					{
						if(x instanceof BaseProduct)
						{
							if(x.getName().equals(bpSelected.get(i)))
							{
								i++;
								
								if(((CompositeProduct)item).removeProduct(x)==false)
								{
									((CompositeProduct)item).addProduct(x);
								}
								
								if(i==bpSelected.size())
								{
									break;
								}
							}
						}						
					  }
				}
				
				((CompositeProduct)nou).setItemList(((CompositeProduct)item).getItemList());
				
				
				restaurant.editMenuItem(item,nou);
				
				view.showError("Produsul "+name+" a fost editat cu succes!");
				view.notVisible();
				ViewAdminCp v=new ViewAdminCp(restaurant.getMenu());
			    ControlAdminCp control=new ControlAdminCp(restaurant,v);
				
				
			}catch(NumberFormatException ex)
			{
				view.showError("Nu s-a introdus o valoare valida pentru pret!");
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
			ViewAdminCp v=new ViewAdminCp(restaurant.getMenu());
		    ControlAdminCp control=new ControlAdminCp(restaurant,v);
			
		}
		catch(AssertionError er)
		{
			view.showError("Nu se poate realiza stergerea!!!");
		}
		
		

	}
	
}

}
