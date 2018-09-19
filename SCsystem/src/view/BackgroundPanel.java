package view;

import java.awt.*;  
import javax.swing.JPanel;  
  
  
public class BackgroundPanel extends JPanel {  
    private static final long serialVersionUID = -6352788025440244338L;  
    private Image image = null;  
    public BackgroundPanel(Image image) {  
        this.image = image;  
    }  
    // �̶�����ͼƬ���������JPanel������ͼƬ������������  
    protected void paintComponent(Graphics g) {  
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
    }  
}  