import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.*;

import javax.swing.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		JComboBox<Integer> cmbNumberOfSegments = new JComboBox<>(new Integer[]{1, 2});
		cmbNumberOfSegments.setBounds(300, 50, 50, 25);
		cmbNumberOfSegments.setSelectedIndex(1);

		int numberOfSegments = Integer.parseInt(cmbNumberOfSegments.getSelectedItem().toString());
		int segmentSize = 100; 
		int imgWidth = numberOfSegments * segmentSize;
		int imgHeight = numberOfSegments * segmentSize;
		BufferedImage image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB); 
		
		int redI=0, greenI=0, blueI=0;
		int red, green, blue;
		int currentSegment = 1;
		int[] EndingRGBI = {0, 0, 0};
		int[] InitialRGBI = getCurrentSegmentInitialRGBI(currentSegment);
		redI = InitialRGBI[0];
		greenI = InitialRGBI[1];
		blueI = InitialRGBI[2];
		EndingRGBI = getCurrentSegmentEndingRGBI(currentSegment);

		for (int x = 0; x < imgWidth; x++) {
			
			if (x == segmentSize * currentSegment) {
				currentSegment++;
				InitialRGBI = getCurrentSegmentInitialRGBI(currentSegment);
				redI = InitialRGBI[0];
				greenI = InitialRGBI[1];
				blueI = InitialRGBI[2];
				EndingRGBI = getCurrentSegmentEndingRGBI(currentSegment);
				
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
			
			if (redI < EndingRGBI[0]) {
				redI++;
			}
			else if (redI > EndingRGBI[0]) {
				redI--;
			}
			
			if (greenI < EndingRGBI[1]) {
				greenI++;
			}
			else if (greenI > EndingRGBI[1]) {
				greenI--;

			}if (blueI < EndingRGBI[2]) {
				blueI++;
			}
			else if (blueI > EndingRGBI[2]) {
				blueI--;
			}

		}
        
		System.out.println("Done");

		File outputFile = new File("output.bmp");
		outputFile.createNewFile();
        ImageIO.write(image, "bmp", outputFile);


		Segment segment1 = new Segment();


		JLabel lbNumberOfSegments = new JLabel();
		lbNumberOfSegments.setText("Number of segments");
		lbNumberOfSegments.setForeground(Color.WHITE);
		lbNumberOfSegments.setHorizontalAlignment(JLabel.CENTER);
		lbNumberOfSegments.setVerticalAlignment(JLabel.TOP);
		lbNumberOfSegments.setBounds(300, 0, 200, 200);

		
		JButton btnPrintSegment = new JButton();
		btnPrintSegment.setText("Print Segment(s)");
        btnPrintSegment.addActionListener(e -> segment1.updateSegment(image));

		JPanel pnlControlPanel = new JPanel();
		pnlControlPanel.setBackground(Color.DARK_GRAY);
		pnlControlPanel.setPreferredSize(new Dimension(800, 100));
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
		frame.add(segment1);

		frame.setVisible(true);
		
	}

	static int[] getCurrentSegmentInitialRGBI(int currentSegment) {
		int[] rgbIArray = {0, 0, 0};

		switch (currentSegment) {
			case 1:
				rgbIArray[0] = 100;
				rgbIArray[1] = 0;
				rgbIArray[2] = 100;
				break;
			case 2:
				rgbIArray[0] = 100;
				rgbIArray[1] = 100;
				rgbIArray[2] = 0;
				break;
			default:
				rgbIArray[0] = 0;
				rgbIArray[1] = 0;
				rgbIArray[2] = 0;
				break;
		}

		return rgbIArray;
	}

	static int[] getCurrentSegmentEndingRGBI(int currentSegment) {
		int[] rgbIArray = {0, 0, 0};

		switch (currentSegment) {
			case 1:
				rgbIArray[0] = 0;
				rgbIArray[1] = 100;
				rgbIArray[2] = 0;
				break;
			case 2:
				rgbIArray[0] = 0;
				rgbIArray[1] = 0;
				rgbIArray[2] = 100;
				break;
			default:
				rgbIArray[0] = 0;
				rgbIArray[1] = 0;
				rgbIArray[2] = 0;
				break;
		}

		return rgbIArray;
	}	
}
