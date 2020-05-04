package businessLayer;

import java.util.ArrayList;

public interface IRestaurantProcessing {
	
	//administrator op
	public void createMenuItem(MenuItem m);
	public void deleteMenuItem(MenuItem m);
	public void editMenuItem(MenuItem m,MenuItem x);
	
	public ArrayList<MenuItem> getMenu();
	
	
	//waiter op
	public void createOrder(Order ord,ArrayList<MenuItem> m);
	public double computePriceOrder(Order ord);
	public void generateBill(Order ord);
	

}
