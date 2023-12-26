import javax.swing.*;
import java.awt.*;

public class Test extends javax.swing.JFrame{

    private JPanel mainPanel;
    private JButton button1;


    public Test(){
        ButtonGradient buttonGradient1 = new ButtonGradient();
        ButtonGradient buttonGradient2 = new ButtonGradient();




    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Card Layout Example");
        frame.setContentPane(new Test().mainPanel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
