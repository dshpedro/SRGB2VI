import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Segment extends JPanel {
    JLabel lbImageLabel = new JLabel();
    JLabel lbR = new JLabel();
    JLabel lbG = new JLabel();
    JLabel lbB = new JLabel();
    JTextField tbxInitialRedI = new JTextField();
    JTextField tbxInitialGreenI = new JTextField();   
    JTextField tbxInitialBlueI = new JTextField();
    JTextField tbxEndingRedI = new JTextField();
    JTextField tbxEndingGreenI = new JTextField();   
    JTextField tbxEndingBlueI = new JTextField();   

    Segment(){
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(99, 155));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        final int textBoxAndLabelWidth = 30, textBoxAndLabelHeight = 20;

        lbR.setText("R");
        lbR.setPreferredSize(new Dimension(textBoxAndLabelWidth, textBoxAndLabelHeight));
        lbR.setHorizontalAlignment(SwingConstants.CENTER);
        lbR.setForeground(Color.RED);
        this.add(lbR);

        lbG.setText("G");
        lbG.setPreferredSize(new Dimension(textBoxAndLabelWidth, textBoxAndLabelHeight));
        lbG.setHorizontalAlignment(SwingConstants.CENTER);
        lbG.setForeground(Color.GREEN);
        this.add(lbG);

        lbB.setText("B");
        lbB.setPreferredSize(new Dimension(textBoxAndLabelWidth, textBoxAndLabelHeight));
        lbB.setHorizontalAlignment(SwingConstants.CENTER);
        lbB.setForeground(Color.BLUE);
        this.add(lbB);  

        tbxInitialRedI.setPreferredSize(new Dimension(textBoxAndLabelWidth, textBoxAndLabelHeight));
        tbxInitialRedI.setHorizontalAlignment(SwingConstants.CENTER);
        tbxInitialRedI.addActionListener(e -> this.updateSegment());
        this.add(tbxInitialRedI);

        tbxInitialGreenI.setPreferredSize(new Dimension(textBoxAndLabelWidth, textBoxAndLabelHeight));
        tbxInitialGreenI.setHorizontalAlignment(SwingConstants.CENTER);
        tbxInitialGreenI.addActionListener(e -> this.updateSegment());
        this.add(tbxInitialGreenI);

        tbxInitialBlueI.setPreferredSize(new Dimension(textBoxAndLabelWidth, textBoxAndLabelHeight));
        tbxInitialBlueI.setHorizontalAlignment(SwingConstants.CENTER);
        tbxInitialBlueI.addActionListener(e -> this.updateSegment());
        this.add(tbxInitialBlueI);

        tbxEndingRedI.setPreferredSize(new Dimension(textBoxAndLabelWidth, textBoxAndLabelHeight));
        tbxEndingRedI.setHorizontalAlignment(SwingConstants.CENTER);
        tbxEndingRedI.addActionListener(e -> this.updateSegment());
        this.add(tbxEndingRedI);

        tbxEndingGreenI.setPreferredSize(new Dimension(textBoxAndLabelWidth, textBoxAndLabelHeight));
        tbxEndingGreenI.setHorizontalAlignment(SwingConstants.CENTER);
        tbxEndingGreenI.addActionListener(e -> this.updateSegment());
        this.add(tbxEndingGreenI);

        tbxEndingBlueI.setPreferredSize(new Dimension(textBoxAndLabelWidth, textBoxAndLabelHeight));
        tbxEndingBlueI.setHorizontalAlignment(SwingConstants.CENTER);
        tbxEndingBlueI.addActionListener(e -> this.updateSegment());
        this.add(tbxEndingBlueI);

        this.add(lbImageLabel);
    }

    void updateSegment(){
        final int imageSize=100;
        BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB); 
        int[] initialRGBI = {0, 0, 0};
		int[] endingRGBI = {0, 0, 0};

        try {
            initialRGBI[0] = Integer.parseInt(tbxInitialRedI.getText());
            initialRGBI[1] = Integer.parseInt(tbxInitialGreenI.getText());
            initialRGBI[2] = Integer.parseInt(tbxInitialBlueI.getText());
            endingRGBI[0] = Integer.parseInt(tbxEndingRedI.getText());
            endingRGBI[1] = Integer.parseInt(tbxEndingGreenI.getText());
            endingRGBI[2] = Integer.parseInt(tbxEndingBlueI.getText());
        } catch (Exception e) {
            return;
        }

        int RGBIDifference[] = new int[3];
        
        for (int i=0; i<3; i++) {    
            RGBIDifference[i] = Math.abs(initialRGBI[i]-endingRGBI[i]);
        }
        Arrays.sort(RGBIDifference);

        final int lastIndex = 2;
        final double pace = RGBIDifference[lastIndex], increment = pace/imageSize;
        final int maxColorValue = 255;
        System.out.println(pace);
        System.out.println(increment);
        int red, green, blue;
        float redIntensity=initialRGBI[0], greenIntensity=initialRGBI[1], blueIntensity=initialRGBI[2];
        
        for (int x = 0; x < imageSize; x++) {
			for (int y = 0; y < imageSize; y++) {
				red = Math.round( redIntensity * maxColorValue / imageSize );
				green = Math.round( greenIntensity * maxColorValue / imageSize );
				blue = Math.round( blueIntensity * maxColorValue / imageSize );

				int rgb = red;
				rgb = (rgb << 8) + green; // rotates one byte in 'rgb'
				rgb = (rgb << 8) + blue;  // rotates one byte in 'rgb'

				image.setRGB(x, y, rgb);
			}

            if (redIntensity < endingRGBI[0]) {
				redIntensity+=increment;
			}
			else if (redIntensity > endingRGBI[0]) {
				redIntensity-=increment;
			}
			
			if (greenIntensity < endingRGBI[1]) {
				greenIntensity+=increment;
			}
			else if (greenIntensity > endingRGBI[1]) {
				greenIntensity-=increment;

			}
            
            if (blueIntensity < endingRGBI[2]) {
				blueIntensity+=increment;
			}
			else if (blueIntensity > endingRGBI[2]) {
				blueIntensity-=increment;
			}

            // sets the min and max values for the intensity of each color
            redIntensity = Math.max(0, Math.min(redIntensity, 100));
            greenIntensity = Math.max(0, Math.min(greenIntensity, 100));
            blueIntensity = Math.max(0, Math.min(blueIntensity, 100));
        }
        ImageIcon segmentImage = new ImageIcon();

        segmentImage.setImage(image);
		lbImageLabel.setIcon(segmentImage);
    }
}
