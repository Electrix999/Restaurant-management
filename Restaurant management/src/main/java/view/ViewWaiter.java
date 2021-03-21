package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

import businessLayer.MenuItem;

public class ViewWaiter extends JFrame {

	private JFrame frame;
	private JList<String> menuItemsList;
	private JLabel orderLabel=new JLabel("ORDER ID:");
	private JLabel dateLabel=new JLabel("DATE:");
	private JLabel tableLabel=new JLabel("TABLE:");
	private JButton addOrderBtn=new JButton("NEW ORDER");
	private JButton viewOrdersBtn=new JButton("VIEW ORDERS");
	private JButton billBtn=new JButton("MAKE BILL");
	private JTextField orderTF=new JTextField(20);
	private JTextField dateTF=new JTextField(20);
	private JTextField tableTF=new JTextField(10);
	
	private ArrayList<MenuItem> menu;
	
	public ViewWaiter(ArrayList<MenuItem> m)
	{
		frame=new JFrame("WAITER INTERFACE");
		this.menu=m;
		
	    DefaultListModel<String> menuItems = new DefaultListModel<String>();
		for(MenuItem x:menu)
		{
			menuItems.addElement(x.getName());
		}
		
		menuItemsList=new JList(menuItems);
		
		JScrollPane scrollPaneMenu = new JScrollPane();
	    scrollPaneMenu.setViewportView(menuItemsList);
	    
	    
	    
	    JPanel listPanel=new JPanel(new GridLayout(2,1));
	    listPanel.add(new JLabel("MENU:"));
	    listPanel.add(scrollPaneMenu);
	    
	    JPanel orderPanel=new JPanel(new FlowLayout());
	    orderPanel.add(orderLabel);
	    orderPanel.add(orderTF);
		
		JPanel datePanel=new JPanel(new FlowLayout());
		datePanel.add(dateLabel);
		datePanel.add(dateTF);
		
		JPanel tablePanel=new JPanel(new FlowLayout());
		tablePanel.add(tableLabel);
		tablePanel.add(tableTF);
		
		JPanel btnPanel=new JPanel(new FlowLayout());
		btnPanel.add(addOrderBtn);
		btnPanel.add(viewOrdersBtn);
		btnPanel.add(billBtn);
		
		JPanel smallPanel=new JPanel(new GridLayout(4,1));
		smallPanel.add(orderPanel);
		smallPanel.add(datePanel);
		smallPanel.add(tablePanel);
		smallPanel.add(btnPanel);
		
		JPanel bigPanel=new JPanel(new GridLayout(1,2));
		bigPanel.add( listPanel);
		bigPanel.add(smallPanel);
		
		frame.setSize(1000,300);
		frame.add(bigPanel);
		frame.setVisible(true);
		
		
	}

	public JList<String> menuItemsList() {
		return menuItemsList;
	}
	public JTextField getOrderTF() {
		return orderTF;
	}

	public JTextField getDateTF() {
		return dateTF;
	}

	public JTextField getTableTF() {
		return tableTF;
	}
	
	
	
	public void orderListener(ActionListener order)
	{
		addOrderBtn.addActionListener(order);
	}
	
	public void viewListener(ActionListener vOrder)
	{
		viewOrdersBtn.addActionListener(vOrder);
	}
	
	public void billListener(ActionListener bill)
	{
		billBtn.addActionListener(bill);
	}
	
	public void listListener(ListSelectionListener list)
	{
		menuItemsList.addListSelectionListener(list);
	}
	
	public void showError(String message)//metoda pt afisarea erorilor in GUI
	{
		JOptionPane.showMessageDialog(this, message);
	}

	
	
}
