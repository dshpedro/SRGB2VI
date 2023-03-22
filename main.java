import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.*;


public class main {

	public static void main(String[] args) throws IOException {
		
		BufferedImage image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB); 
		int[] rgbValues = new int[]{100, 100, 100};
		image.getRaster().setPixels(0, 0, 1, 1, rgbValues);
		image.getRaster().setPixels(0, 1, 1, 1, rgbValues);
		image.getRaster().setPixels(1, 0, 1, 1, rgbValues);
		image.getRaster().setPixels(1, 1, 1, 1, rgbValues);
        
		File outputFile = new File("output.bmp");
		outputFile.createNewFile();
        ImageIO.write(image, "bmp", outputFile);

		
		JLabel lbNumberOfSegments = new JLabel();
		lbNumberOfSegments.setText("Number of segments");
		lbNumberOfSegments.setForeground(Color.WHITE);
		lbNumberOfSegments.setHorizontalAlignment(JLabel.CENTER);
		lbNumberOfSegments.setVerticalAlignment(JLabel.TOP);
		lbNumberOfSegments.setBounds(300, 0, 200, 200);


		JComboBox cmbNumberOfSegments = new JComboBox<>(new Integer[]{1});
		cmbNumberOfSegments.setBounds(300, 50, 50, 25);


		JButton btnPrintSegment = new JButton();
		btnPrintSegment.setText("Print Segment(s)");
		btnPrintSegment.addActionListener(e -> System.out.println("a"));


		JPanel pnlControlPanel = new JPanel();
		pnlControlPanel.setBackground(Color.DARK_GRAY);

		pnlControlPanel.add(lbNumberOfSegments);
		pnlControlPanel.add(cmbNumberOfSegments);
		pnlControlPanel.add(btnPrintSegment);


		JFrame frame = new JFrame();
		frame.setTitle("SRGB2VI");
		frame.setSize(800, 600);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.add(pnlControlPanel);

		frame.setVisible(true);
		
	}

}
