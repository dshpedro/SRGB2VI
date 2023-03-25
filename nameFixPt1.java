import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;

import javax.swing.*;

public class nameFixPt1 {

	public static void main(String[] args) throws IOException {
		
		JButton btnAddSegment = new JButton();
		btnAddSegment.setText("Add segment");
		JButton btnReset = new JButton();
		btnReset.setText("Reset");

		JPanel pnlControlPanel = new JPanel();
		pnlControlPanel.setBackground(Color.DARK_GRAY);
		pnlControlPanel.setPreferredSize(new Dimension(800, 60));
		pnlControlPanel.add(btnAddSegment);
		pnlControlPanel.add(btnReset);

		JFrame frame = new JFrame();
		frame.setTitle("SRGB2VI");
		frame.setSize(800, 600);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		frame.setResizable(false);
		frame.add(pnlControlPanel);
		frame.setVisible(true);

		btnAddSegment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				frame.add(new Segment());
				frame.revalidate();
				frame.repaint();
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				Component[] componentList = frame.getRootPane().getContentPane().getComponents();
				for(Component c : componentList){
					if(c instanceof Segment){
						frame.remove(c);
					}
				}
				frame.revalidate();
				frame.repaint();
			}
		});

	}

}
