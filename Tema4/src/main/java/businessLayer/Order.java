package businessLayer;


public class Order {
	private int orderID;
	private String date;
	private int table;
	public Order(int orderID, String date, int table) {
		super();
		this.orderID = orderID;
		this.date = date;
		this.table = table;
	}
	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	public int getOrderID() {
		return orderID;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTable() {
		return table;
	}
	public void setTable(int table) {
		this.table = table;
	}
	
	public int hashCode()
	{
		
		return orderID*date.hashCode()*table;
	}
	
	public boolean equals(Object o)
	{
		
		if(this==o)
		{
			return true;
		}
		if(o==null)
		{
			return false;
		}
		if(this.getClass()!=o.getClass())
		{
			return false;
		}
		Order or=(Order)o;
		return orderID==or.orderID && date.equals(or.date) && table==or.table;
	}

}
