import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Segment extends JPanel {

    ImageIcon segmentImage = new ImageIcon();
    JLabel imageLabel = new JLabel();
    JTextField tbxSegmentInitialRGBI = new JTextField();
    

    Segment(){
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(800, 100));

        this.tbxSegmentInitialRGBI.setPreferredSize(new Dimension(40, 20));
        this.tbxSegmentInitialRGBI.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(tbxSegmentInitialRGBI);
        this.add(imageLabel);
    }

    void updateSegment(BufferedImage image){
        
        this.segmentImage.setImage(image);
		this.imageLabel.setIcon(segmentImage);
        
        System.out.println("the button was pressed");
    }
    
    
}
