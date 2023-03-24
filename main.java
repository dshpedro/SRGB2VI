import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;

import javax.swing.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		JComboBox<Integer> cmbNumberOfSegments = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
		cmbNumberOfSegments.setBounds(300, 50, 50, 25);
		cmbNumberOfSegments.setSelectedIndex(1);
		int numberOfSegments = Integer.parseInt(cmbNumberOfSegments.getSelectedItem().toString());

		JLabel lbNumberOfSegments = new JLabel();
		lbNumberOfSegments.setText("Number of segments");
		lbNumberOfSegments.setForeground(Color.WHITE);
		lbNumberOfSegments.setHorizontalAlignment(JLabel.CENTER);
		lbNumberOfSegments.setVerticalAlignment(JLabel.TOP);
		lbNumberOfSegments.setBounds(300, 0, 200, 200);
		
		JButton btnAddSegment = new JButton();
		btnAddSegment.setText("Add segment");
		JButton btnReset = new JButton();
		btnReset.setText("Reset");

		JPanel pnlControlPanel = new JPanel();
		pnlControlPanel.setBackground(Color.DARK_GRAY);
		pnlControlPanel.setPreferredSize(new Dimension(800, 100));
		pnlControlPanel.add(lbNumberOfSegments);
		pnlControlPanel.add(cmbNumberOfSegments);
		pnlControlPanel.add(btnAddSegment);
		pnlControlPanel.add(btnReset);

		JFrame frame = new JFrame();
		frame.setTitle("SRGB2VI");
		frame.setSize(800, 600);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		frame.setResizable(false);
		frame.add(pnlControlPanel);
		btnAddSegment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				frame.add(new Segment());
				frame.revalidate();
				frame.repaint();
			}
		});
		for (int i = 0; i < numberOfSegments; i++) {
			frame.add(new Segment());
		}
		frame.setVisible(true);

		cmbNumberOfSegments.addActionListener(e -> frame.add(new Segment()));

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
