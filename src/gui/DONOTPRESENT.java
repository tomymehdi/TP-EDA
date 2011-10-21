package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DONOTPRESENT extends JFrame{
	private JPanel container;
	private boolean timed;
	private JButton time, level, l1,l2,l3,l4,l5,l6;

	public DONOTPRESENT(){
		super("REVERSI - set up");
		setBackground(new Color(0, 100,0));
		setResizable(false);
		setPreferredSize(new Dimension(300,100));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		container=new JPanel();
		container.setLayout(new FlowLayout());
		time = new JButton("Timed");
		level = new JButton("Leveled");
		time.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timed=true;
				container.remove(time);
				container.remove(level);
				container.add(l1);
				container.add(l2);
				container.add(l3);
				container.add(l4);
				container.add(l5);
				container.add(l6);
				validate();

			}
		});
		level.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				timed=false;
				container.remove(time);
				container.remove(level);
				container.add(l1);
				container.add(l2);
				container.add(l3);
				container.add(l4);
				container.add(l5);
				container.add(l6);
				validate();

			}
		});
		container.add(level);
		container.add(time);
		validate();


		l1 = new JButton("1");
		l2 = new JButton("2");
		l3 = new JButton("3");
		l4 = new JButton("4");
		l5 = new JButton("5");
		l6 = new JButton("6");

		l1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				DONOTPRESENT.this.setVisible(false);
				new Window(1, true, timed);
			}
		});
		l2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				DONOTPRESENT.this.setVisible(false);
				new Window(2, true, timed);
			}
		});
		l3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				DONOTPRESENT.this.setVisible(false);
				new Window(3, true, timed);
			}
		});
		l4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				DONOTPRESENT.this.setVisible(false);
				new Window(4, true, timed);
			}
		});
		l5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				DONOTPRESENT.this.setVisible(false);
				new Window(5, true, timed);
			}
		});
		l6.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				DONOTPRESENT.this.setVisible(false);
				new Window(6, true, timed);
			}
		});
		this.
		add(container);
		pack();
		setVisible(true);
	}
public static void main(String[] args) {
	DONOTPRESENT d = new DONOTPRESENT();
}

}	

