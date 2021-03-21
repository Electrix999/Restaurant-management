package view;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionListener;

import businessLayer.BaseProduct;
import businessLayer.MenuItem;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

public class ViewAdminBp extends JFrame{
	private JFrame frame;
	private JList<String> baseProdList;
	private JLabel nameLabel=new JLabel("NAME:");
	private JLabel priceLabel=new JLabel("PRICE:");
	private JButton createBtn=new JButton("CREATE");
	private JButton editBtn=new JButton("EDIT");
	private JButton removeBtn=new JButton("REMOVE");
	private JTextField nameTF=new JTextField(20);
	private JTextField priceTF=new JTextField(10);
	
	private ArrayList<MenuItem> menu;
	
	private DefaultListModel<String> bpName = new DefaultListModel<String>();
	
	public ViewAdminBp( ArrayList<MenuItem> m)
	{
		frame=new JFrame("BASE PRODUCTS");
		
		this.menu=m;
		
		for(MenuItem x:menu)
		{
			if(x instanceof BaseProduct)
			{
				bpName.addElement(((BaseProduct)x).getName());
			}
		}
		
		baseProdList=new JList(bpName);
		
		JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setViewportView(baseProdList);
		
		JPanel namePanel=new JPanel(new FlowLayout());
		namePanel.add(nameLabel);
		namePanel.add(nameTF);
		
		JPanel pricePanel=new JPanel(new FlowLayout());
		pricePanel.add(priceLabel);
		pricePanel.add(priceTF);
		
		JPanel btnPanel=new JPanel(new FlowLayout());
		btnPanel.add(createBtn);
		btnPanel.add(editBtn);
		btnPanel.add(removeBtn);
		
		JPanel smallPanel=new JPanel(new GridLayout(3,1));
		smallPanel.add(namePanel);
		smallPanel.add(pricePanel);
		smallPanel.add(btnPanel);
		
		JPanel bigPanel=new JPanel(new GridLayout(1,2));
		bigPanel.add(scrollPane);
		bigPanel.add(smallPanel);
		
		frame.setSize(800,300);
		frame.add(bigPanel);
		frame.setVisible(true);
		
	}
	
	
	 
	public void updateList(String x)
	{
		bpName.addElement(x);
	}
	
	public JList<String> getBaseProdList() {
		return baseProdList;
	}

    

	public JTextField getNameTF() {
		return nameTF;
	}



	public JTextField getPriceTF() {
		return priceTF;
	}


	public void notVisible()
	{
		frame.setVisible(false);
	}
	
	public void createListener(ActionListener create)
	{
		createBtn.addActionListener(create);
	}
	
	public void editListener(ActionListener edit)
	{
		editBtn.addActionListener(edit);
	}
	
	public void removeListener(ActionListener remove)
	{
		removeBtn.addActionListener(remove);
	}
	
	public void listListener(ListSelectionListener list)
	{
		baseProdList.addListSelectionListener(list);
	}
	
	public void showError(String message)//metoda pt afisarea erorilor in GUI
	{
		JOptionPane.showMessageDialog(this, message);
	}
	

}
