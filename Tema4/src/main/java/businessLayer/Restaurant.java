package businessLayer;


import java.util.*;

import dataLayer.FileWork;
import dataLayer.RestaurantSerialization;

@SuppressWarnings("deprecation")
public class Restaurant extends Observable implements IRestaurantProcessing{

	private Map<Order, ArrayList<MenuItem>> map=new HashMap<Order,ArrayList<MenuItem>>();
	private ArrayList<MenuItem> menu=new ArrayList<MenuItem>();
	private RestaurantSerialization restSer;
	private String fileName;
	
	public Restaurant(String fileName)
	{
		restSer=new RestaurantSerialization();
		this.fileName=fileName;
		menu=restSer.deserialization(this.fileName);
		
	}
	
	
	/**
	 * This method creates a new MenuItem
	 * @param the new MenuItem that will be added in MenuItem list which contains all Items from Menu
	 * @pre m!=null && the menu do not contains the element that we want to add
	 * @post menu.size()==menu.size()@pre + 1
	 * @invariant isWellFormed()
	 */
	public void createMenuItem(MenuItem m) {
		// TODO Auto-generated method stub
		
		
		assert m!=null && !menu.contains(m);
		assert isWellFormed();
		
		int sizePre=menu.size();
		
		
		menu.add(m);
		restSer.serialization(fileName, menu);
		
		
		int sizePost=menu.size();
		assert sizePost==sizePre+1;
		assert isWellFormed();
		

		
		
	}

	/**
	 * This method delete a MenuItem from menu list
	 * @param an item from menu that we want to delete
	 * @pre m!=null && the menu contains the element m, that we want to delete
	 * @post menu.size()==menu.size()@pre - 1
	 * @invariant isWellFormed()
	 */
	public void deleteMenuItem(MenuItem m) {
		// TODO Auto-generated method stub
		assert m!=null && menu.contains(m);
		assert isWellFormed();
		int sizePre=menu.size();
		
		menu.remove(m);
		restSer.serialization(fileName, menu);
			
		int sizePost=menu.size();
		assert sizePost==sizePre-1;
		assert isWellFormed();
		
		
	}
	
	/**
	 * This method is used to edit an item from Menu
	 * @param first param is the item that we want to modify
	 * @param second param is the item that contains new data
	 * @pre sh!= null && the menu contents the element sh, that we want to modify
	 * @pre ed!=null
	 * @post menu.size()==menu.size()@pre
	 * @invariant isWellFormed()
	 */
	public void editMenuItem(MenuItem sh,MenuItem ed) {
		// TODO Auto-generated method stub
		assert sh!=null && menu.contains(sh) && ed!=null;
		assert isWellFormed();
		int sizePre=menu.size();
		
		int index=menu.indexOf(sh);
		menu.set(index, ed);
		restSer.serialization(fileName, menu);
					
		int sizePost=menu.size();
		assert sizePre==sizePost;
		assert isWellFormed();
		
	}
	
	/**
	 * This method creates a new order
	 * @param first represents the order data
	 * @param second param contains ordered products
	 * @pre ord!=null && m.size()>0 && map.get(ord)==null
	 * @post noOrders==noOrders@pre +1
	 * @invariant isWellFormed()
	 * 
	 */
	public void createOrder(Order ord,ArrayList<MenuItem> m) {
		// TODO Auto-generated method stub
		assert ord!=null && m.size()>0 && map.get(ord)==null;
		assert isWellFormed();
		
		int noOrdersPre=map.size();

		ArrayList<MenuItem> comand=new ArrayList<MenuItem>();
		for(MenuItem x:m)
		{
			if(x instanceof CompositeProduct)
			{
				comand.add(x);
					
			}
		}
			
		map.put(ord, m);
		setChanged();
		notifyObservers(comand);
		
		int noOrdersPost=map.size();
		
		assert noOrdersPost==noOrdersPre+1;
		assert isWellFormed();
		
	}
	
	/**
	 * This method computes total price for an Order
	 * @param The order from which we want to know the total price
	 * @return total price for an order
	 * @pre ord!=null, the order must to exist
	 * @invariant isWellFormed()
	 */
	public double computePriceOrder(Order ord) {
		// TODO Auto-generated method stub
		
		assert ord!=null;
		assert isWellFormed();
		
		ArrayList<MenuItem> m=map.get(ord);
		if(m.size()>0)
		{
			double price=0.0;
			for(MenuItem x:m)
			{
				price+=x.computePrice();
			}
			assert isWellFormed();
			return price;
		}
		assert isWellFormed();
		return 0;
		
	}
	
	/**
	 * This method generate a bill for an order
	 * @param The order for which we want to make the bill
	 * @pre map.get(ord)!=null && ord!=null, the order must to exist
	 * @post noOrders==noOrders@pre
	 * @invariant isWellFormed()
	 */
	public void generateBill(Order ord) {
		// TODO Auto-generated method stub
		
		assert ord!=null && map.get(ord)!=null;
		assert isWellFormed();
		
		int noOrdersPre=map.size();
		
		FileWork bill=new FileWork();
		
		bill.makeBill(ord.getOrderID(), computePriceOrder(ord), map.get(ord));
			
		
		int noOrdersPost=map.size();
		
		assert noOrdersPost==noOrdersPre;
		assert isWellFormed();
		
	}

	protected boolean isWellFormed(){
		
		if(fileName==null || restSer==null || menu==null || map ==null)
		{
			return false;
		}
		for(MenuItem x:menu)
		{
			if(x==null)
			{
				return false;
			}
		}
		
		Set<Order> order=new HashSet<Order>();
		order=map.keySet();
		
		for(Order x:order)
		{
			if(map.get(x)==null)
			{
				return false;
			}
		}
		
		return true;

	}
	
	public Map<Order, ArrayList<MenuItem>> getMap() {
		return map;
	}

	public ArrayList<MenuItem> getMenu() {
		return menu;
	}

	
	
	
}
