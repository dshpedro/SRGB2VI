import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Segment extends JPanel {

    ImageIcon segmentImage = new ImageIcon();
    JLabel imageLabel = new JLabel();
    

    Segment(){
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(800, 100));
        this.add(imageLabel);
    }

    void updateSegment(BufferedImage image){
        
        this.segmentImage.setImage(image);
		this.imageLabel.setIcon(segmentImage);
        
        System.out.println("the button was pressed");
    }
    
    
}
