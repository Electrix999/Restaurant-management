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
import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;

public class ViewAdminCp extends JFrame{
	private JFrame frame;
	private JList<String> baseProdList;
	private JList<String> compProdList;
	private JLabel nameLabel=new JLabel("NAME:");
	private JLabel priceLabel=new JLabel("PRICE:");
	private JLabel compLabel=new JLabel("COMPOSITE:");
	private JButton createBtn=new JButton("CREATE");
	private JButton editBtn=new JButton("EDIT");
	private JButton removeBtn=new JButton("REMOVE");
	private JTextField nameTF=new JTextField(20);
	private JTextField compTF=new JTextField(30);
	private JTextField priceTF=new JTextField(10);
	private DefaultListModel<String> bpName = new DefaultListModel<String>();
	private DefaultListModel<String> cpName = new DefaultListModel<String>();
	
	private ArrayList<MenuItem> menu;
	
	public ViewAdminCp(ArrayList<MenuItem> m)
	{
		
		priceTF.setEditable(false);
		compTF.setEditable(false);
		
		frame=new JFrame("COMPOSITE PRODUCTS");
		this.menu=m;
		
		
		for(MenuItem x:menu)
		{
			if(x instanceof BaseProduct)
			{
				bpName.addElement(((BaseProduct)x).getName());
			}
			if(x instanceof CompositeProduct)
			{
				cpName.addElement(((CompositeProduct)x).getName());
			}
		}
		
		baseProdList=new JList(bpName);
		compProdList=new JList(cpName);
		
		JScrollPane scrollPaneBp = new JScrollPane();
	    scrollPaneBp.setViewportView(baseProdList);
	   
	    
	    JPanel bpPanel=new JPanel(new GridLayout(2,1));
	    bpPanel.add(new JLabel("Base Products:"));
	    bpPanel.add(scrollPaneBp);
	    
	    JScrollPane scrollPaneCp = new JScrollPane();
	    scrollPaneCp.setViewportView(compProdList);
	    
	    JPanel cpPanel=new JPanel(new GridLayout(2,1));
	    cpPanel.add(new JLabel("Composite Products:"));
	    cpPanel.add(scrollPaneCp);
	    
	    JPanel namePanel=new JPanel(new FlowLayout());
		namePanel.add(nameLabel);
		namePanel.add(nameTF);
		
		JPanel compPanel=new JPanel(new FlowLayout());
		compPanel.add(compLabel);
		compPanel.add(compTF);
		
		JPanel pricePanel=new JPanel(new FlowLayout());
		pricePanel.add(priceLabel);
		pricePanel.add(priceTF);
		
		JPanel btnPanel=new JPanel(new FlowLayout());
		btnPanel.add(createBtn);
		btnPanel.add(editBtn);
		btnPanel.add(removeBtn);
		
		JPanel smallPanel=new JPanel(new GridLayout(4,1));
		smallPanel.add(namePanel);
		smallPanel.add(compPanel);
		smallPanel.add(pricePanel);
		smallPanel.add(btnPanel);
		
		JPanel bigPanel=new JPanel(new GridLayout(1,3));
		bigPanel.add(cpPanel);
		bigPanel.add(bpPanel);
		bigPanel.add(smallPanel);
		
		frame.setSize(1200,300);
		frame.add(bigPanel);
		frame.setVisible(true);
		
	}
	
	public void updateList(String x)
	{
		
		cpName.addElement(x);
		
	}
	
	public JList<String> getBaseProdList() {
		return baseProdList;
	}
	
	public JList<String> getCompProdList() {
		return compProdList;
	}
    

	public JTextField getNameTF() {
		return nameTF;
	}

	public JTextField getPriceTF() {
		return priceTF;
	}
	
	public JTextField getCompTF() {
		return compTF;
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
	
	public void listListenerBp(ListSelectionListener list)
	{
		baseProdList.addListSelectionListener(list);
	}
	
	public void listListenerCp(ListSelectionListener list)
	{
		compProdList.addListSelectionListener(list);
	}
	
	public void showError(String message)//metoda pt afisarea erorilor in GUI
	{
		JOptionPane.showMessageDialog(this, message);
	}

}
