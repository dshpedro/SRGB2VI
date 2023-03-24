import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

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

        lbR.setText("R");
        lbR.setPreferredSize(new Dimension(30, 20));;
        lbR.setHorizontalAlignment(SwingConstants.CENTER);
        lbR.setForeground(Color.RED);
        this.add(lbR);

        lbG.setText("G");
        lbG.setPreferredSize(new Dimension(30, 20));;
        lbG.setHorizontalAlignment(SwingConstants.CENTER);
        lbG.setForeground(Color.GREEN);
        this.add(lbG);

        lbB.setText("B");
        lbB.setPreferredSize(new Dimension(30, 20));;
        lbB.setHorizontalAlignment(SwingConstants.CENTER);
        lbB.setForeground(Color.BLUE);
        this.add(lbB);  

        this.tbxInitialRedI.setPreferredSize(new Dimension(30, 20));
        this.tbxInitialRedI.setHorizontalAlignment(SwingConstants.CENTER);
        this.tbxInitialRedI.addActionListener(e -> System.out.println("red pressed"));
        this.tbxInitialRedI.addActionListener(e -> this.updateSegment());
        this.add(tbxInitialRedI);

        this.tbxInitialGreenI.setPreferredSize(new Dimension(30, 20));
        this.tbxInitialGreenI.setHorizontalAlignment(SwingConstants.CENTER);
        this.tbxInitialGreenI.addActionListener(e -> System.out.println("green pressed"));
        this.tbxInitialGreenI.addActionListener(e -> this.updateSegment());
        this.add(tbxInitialGreenI);

        this.tbxInitialBlueI.setPreferredSize(new Dimension(30, 20));
        this.tbxInitialBlueI.setHorizontalAlignment(SwingConstants.CENTER);
        this.tbxInitialBlueI.addActionListener(e -> System.out.println("blue pressed"));
        this.tbxInitialBlueI.addActionListener(e -> this.updateSegment());
        this.add(tbxInitialBlueI);

        this.tbxEndingRedI.setPreferredSize(new Dimension(30, 20));
        this.tbxEndingRedI.setHorizontalAlignment(SwingConstants.CENTER);
        this.tbxEndingRedI.addActionListener(e -> System.out.println("red pressed"));
        this.tbxEndingRedI.addActionListener(e -> this.updateSegment());
        this.add(tbxEndingRedI);

        this.tbxEndingGreenI.setPreferredSize(new Dimension(30, 20));
        this.tbxEndingGreenI.setHorizontalAlignment(SwingConstants.CENTER);
        this.tbxEndingGreenI.addActionListener(e -> System.out.println("green pressed"));
        this.tbxEndingGreenI.addActionListener(e -> this.updateSegment());

        this.add(tbxEndingGreenI);

        this.tbxEndingBlueI.setPreferredSize(new Dimension(30, 20));
        this.tbxEndingBlueI.setHorizontalAlignment(SwingConstants.CENTER);
        this.tbxEndingBlueI.addActionListener(e -> System.out.println("blue pressed"));
        this.tbxEndingBlueI.addActionListener(e -> this.updateSegment());
        this.add(tbxEndingBlueI);

        this.add(lbImageLabel);
    }

    void updateSegment(){
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB); 

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
        

        int red, green, blue, redI=initialRGBI[0], greenI=initialRGBI[1], blueI=initialRGBI[2];
        for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				red = Math.round( redI * 255 / 100 );
				green = Math.round( greenI * 255 / 100 );
				blue = Math.round( blueI * 255 / 100 );

				int rgb = red;
				rgb = (rgb << 8) + green; 
				rgb = (rgb << 8) + blue;

				image.setRGB(x, y, rgb);
			}

            if (redI < endingRGBI[0]) {
				redI++;
			}
			else if (redI > endingRGBI[0]) {
				redI--;
			}
			
			if (greenI < endingRGBI[1]) {
				greenI++;
			}
			else if (greenI > endingRGBI[1]) {
				greenI--;

			}if (blueI < endingRGBI[2]) {
				blueI++;
			}
			else if (blueI > endingRGBI[2]) {
				blueI--;
			}
        }

        ImageIcon segmentImage = new ImageIcon();
        segmentImage.setImage(image);
		lbImageLabel.setIcon(segmentImage);
        System.out.println("the button was pressed");
    }
}
