package view;

import javax.swing.*;


import java.awt.event.*;


public class ViewAdmin extends JFrame  {
	
	private JMenu menu=new JMenu("Type Product");
	private JMenuBar menuBar=new JMenuBar(); 
	private JMenuItem bpItem=new JMenuItem("Base Products");
	private JMenuItem cpItem=new JMenuItem("Composite Products");
	private JMenuItem viewItem=new JMenuItem("Show menu");
	private JFrame frame=new JFrame("Adimnistration work");
	

	
	public ViewAdmin () 
	{
		
		  
		menu.add(bpItem);
		menu.add(cpItem);
		menu.add(viewItem);
		menuBar.add(menu);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);
		frame.setSize(800,300);
		frame.setVisible(true);
		
		
	}
	
	
	
	public JFrame getFrame() {
		return frame;
	}

	public void menuBpListener(ActionListener menu)
	{
		bpItem.addActionListener(menu);
	}
	public void menuCpListener(ActionListener menu)
	{
		cpItem.addActionListener(menu);
	}
	
	public void showListener(ActionListener menu)
	{
		viewItem.addActionListener(menu);
	}

	
	

	
	
	

}
