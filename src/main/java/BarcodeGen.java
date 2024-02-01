import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;
import java.nio.file.Files;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import java.awt.Font;
import java.awt.GridLayout;

public class BarcodeGen implements ActionListener {

    JFrame frame = new JFrame("Barcode Generator");
    JLabel welcomeLabel = new JLabel("Barcode Generator");

    JPanel welcomePanel = new JPanel();
    JPanel selectionPanel = new JPanel();
    JPanel examplePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel resultsPanel = new JPanel();

    JTextField addToQRField = new JTextField();
    JTextField pathField = new JTextField();

    JLabel addToQRLabel = new JLabel("What to Add to QR:");
    JLabel pathLabel = new JLabel("Enter your path: ");
    JLabel resultLabel = new JLabel("<html>If no path entered, Path = [file name].[file type] <br> File will be placed in this application directory</html>");
    JLabel pathExampleLabel = new JLabel("Example Path Format: C:/Users/Downloads/[File Name]");

    JButton generateButton = new JButton("QR Code");
    JButton clearButton = new JButton("Clear");
    JButton barcodeButton = new JButton("Barcode");

    GridLayout selectionGridLayout = new GridLayout(2,2,-30,5);
    GridLayout buttonGridLayout = new GridLayout(1,3,20,0);
    GridLayout examplePanelLayout = new GridLayout(1,1,0,0);


    public void qrGeneratorFrame(){

        //panels config
        //welcomePanel.setBackground(Color.red);
        welcomePanel.setBounds(85,10,250,50);

        //selectionPanel.setBackground(Color.green);
        selectionPanel.setBounds(20,90,365,70);

        //examplePanel.setBackground(Color.blue);
        examplePanel.setBounds(20,180,365,50);

        //buttonPanel.setBackground(Color.ORANGE);
        buttonPanel.setBounds(20, 245, 365,30);

        //resultsPanel.setBackground(Color.black);
        resultsPanel.setBounds(20,290,365,150);

        //components properties
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 26));

        addToQRLabel.setFont(new Font(null, Font.PLAIN, 14));
        addToQRField.setBounds(0,0,300,10);

        pathLabel.setFont(new Font(null, Font.PLAIN, 14));
        pathField.setBounds(0,0,300,10);

        pathExampleLabel.setFont(new Font(null, Font.PLAIN, 15));

        resultLabel.setFont(new Font(null, Font.PLAIN, 14));
        resultLabel.setVisible(true);

        clearButton.addActionListener(this);
        generateButton.addActionListener(this);
        barcodeButton.addActionListener(this);


        //add components to panels
        welcomePanel.add(welcomeLabel);

        selectionPanel.setLayout(selectionGridLayout);
        selectionPanel.add(addToQRLabel);
        selectionPanel.add(addToQRField);
        selectionPanel.add(pathLabel);
        selectionPanel.add(pathField);

        examplePanel.add(pathExampleLabel);

        buttonPanel.setLayout(buttonGridLayout);
        buttonPanel.add(clearButton);
        buttonPanel.add(generateButton);
        buttonPanel.add(barcodeButton);

        resultsPanel.add(resultLabel);

        //frame config
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(420,420);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //add panels to frame
        frame.add(welcomePanel);
        frame.add(selectionPanel);
        frame.add(examplePanel);
        frame.add(resultsPanel);
        frame.add(buttonPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clearButton){
            addToQRField.setText("");
            pathField.setText("");
            resultLabel.setText("<html>If no path entered, Path = [file name].[file type] <br> File will be placed in this application directory</html>");

        }


        String data = addToQRField.getText();
        String path = pathField.getText();
        Path pathDir = Paths.get(path);

        if(e.getSource() == generateButton){
            if(Files.exists(pathDir)){
                JOptionPane.showMessageDialog(selectionPanel, "Path entered is invalid or filename exists", "Error", JOptionPane.WARNING_MESSAGE);
            }else{
                try{
                    BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
                    MatrixToImageWriter.writeToPath(matrix,"jpg", Paths.get(path));
                    resultLabel.setText("QR Code Successfully Generated!");

                }catch (WriterException ex1){
                    JOptionPane.showMessageDialog(selectionPanel, "Unable to Create File", "Error", JOptionPane.WARNING_MESSAGE);
                }catch(IOException ex2){
                    JOptionPane.showMessageDialog(selectionPanel, "Invalid Entry.", "Error", JOptionPane.WARNING_MESSAGE);
                }catch(IllegalArgumentException ex3){
                    JOptionPane.showMessageDialog(selectionPanel, "Invalid Entry.", "Error", JOptionPane.WARNING_MESSAGE);
                }

            }//end ifExist

        }//end generate button action


        if(e.getSource() == barcodeButton){
            if(Files.exists(pathDir)){
                JOptionPane.showMessageDialog(selectionPanel, "Path entered is invalid or filename exists", "Error", JOptionPane.WARNING_MESSAGE);
            }else{
                try{
                    Code128Writer writer = new Code128Writer();
                    BitMatrix matrix = writer.encode(data, BarcodeFormat.CODE_128, 500, 300);
                    MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
                    resultLabel.setText("Barcode Successfully Generated!");

                }catch(IOException ex4){
                    JOptionPane.showMessageDialog(selectionPanel, "Invalid Entry.", "Error", JOptionPane.WARNING_MESSAGE);
                }catch(IllegalArgumentException ex5){
                    JOptionPane.showMessageDialog(selectionPanel, "Invalid Entry.", "Error", JOptionPane.WARNING_MESSAGE);
                }

            }//end if exists

        }//end barcode button action

    }//end action performed
}
