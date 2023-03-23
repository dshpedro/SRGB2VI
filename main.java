import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.*;


public class main {

	public static void main(String[] args) throws IOException {
		

		JComboBox cmbNumberOfSegments = new JComboBox<>(new Integer[]{1, 2});
		cmbNumberOfSegments.setBounds(300, 50, 50, 25);
		cmbNumberOfSegments.setSelectedIndex(1);

		int numberOfSegments = Integer.parseInt(cmbNumberOfSegments.getSelectedItem().toString());
		int segmentSize = 100; 
		int imgWidth = numberOfSegments * segmentSize;
		int imgHeight = numberOfSegments * segmentSize;
		BufferedImage image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB); 

		int redI=100, greenI=0, blueI=0;
		int red, green, blue;
		int currentSegment = 1;

		for (int x = 0; x <= imgWidth - 1; x++) {
			if (x >= 100)
				currentSegment = 2;
			if (x == 0 || x == 100){
				switch (currentSegment) {
					case 1:
						redI=100; 
						greenI=0;
						blueI=0;
						break;
					case 2:
						redI=0; 
						greenI=100;
						blueI=0;
						break;
					case 3:
						redI=100; 
						greenI=0;
						blueI=0;				
						break;
					case 4:
						redI=100; 
						greenI=0;
						blueI=0;				
						break;
				}
			}
			
			for (int y = 0; y < imgHeight; y++) {
				red = Math.round( redI * 255 / 100 );
				green = Math.round( greenI * 255 / 100 );
				blue = Math.round( blueI * 255 / 100 );

				int rgb = red;
				rgb = (rgb << 8) + green; 
				rgb = (rgb << 8) + blue;

				image.setRGB(x, y, rgb);
			}
			
			if (x <= segmentSize - 1){
				redI--;
				blueI++;
			}

			if (currentSegment==2){
				redI++;
				greenI--;
				blueI++;
			}
			
		}
        
		System.out.println("Done");

		File outputFile = new File("output.bmp");
		outputFile.createNewFile();
        ImageIO.write(image, "bmp", outputFile);

		
		JLabel lbNumberOfSegments = new JLabel();
		lbNumberOfSegments.setText("Number of segments");
		lbNumberOfSegments.setForeground(Color.WHITE);
		lbNumberOfSegments.setHorizontalAlignment(JLabel.CENTER);
		lbNumberOfSegments.setVerticalAlignment(JLabel.TOP);
		lbNumberOfSegments.setBounds(300, 0, 200, 200);


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
