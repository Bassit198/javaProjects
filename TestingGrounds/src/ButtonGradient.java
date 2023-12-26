import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class ButtonGradient extends JButton {

    private Color color1 = Color.decode("#0099F7");
    private Color color2 = Color.decode("#F11712");

    public ButtonGradient(){
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(10, 20, 10, 20));
    }

    @Override
    protected void paintComponent(Graphics graphics){
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // create gradients color
        GradientPaint gra = new GradientPaint(0,0,color1, width, 0, color2);
        g2.setPaint(gra);
        g2.fillRoundRect(0,0,width,height,height,height);
        //Add style
        createStyle(g2);
        graphics.drawImage(img, 0, 0, null);
        super.paintComponent(graphics);
    }

    private void createStyle(Graphics2D g2){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3f));
        int width = getWidth();
        int height = getHeight();
        GradientPaint gra = new GradientPaint(0, 0, Color.WHITE, 0, height, new Color(255, 255, 255, 60));
        g2.setPaint(gra);
        Path2D.Float f = new Path2D.Float();
        f.moveTo(0,0);
        int controll = height + height/2;
        f.curveTo(0, 0,width/2, controll, width, 0);
        g2.fill(f);
    }

}
