import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class HelperFunctions {
    public void paintRadioButton(JRadioButton radioButton, JLabel label){
        radioButton.setBackground(new Color(40,44,52));
        radioButton.setForeground(label.getForeground());
        radioButton.setFocusable(false);
        radioButton.setFont(label.getFont());
    }

    public void paintBackground(JPanel panel){
        panel.setBackground(new Color(40,44,52));
    }

    public HashMap<String, String> getButtonStyleHashMap() {
        HashMap<String, String> buttonStyle = new HashMap<>();
        buttonStyle.put("green", "src/images/darkGreenButton.png");
        buttonStyle.put("dGreen", "src/images/greenButton.png");
        return buttonStyle;
    }

    public void hoverEffectButtonDefaultSize(JButton button, String hoverEnterPath, String hoverExitPath, int width, int height) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                paintButtonDefaultSize(button, hoverEnterPath, width, height);
            }

            public void mouseExited(MouseEvent evt) {
                paintButtonDefaultSize(button, hoverExitPath, width, height);
            }
        });
    }

    public void paintButtonDefaultSize(JButton button, String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);

        button.setIcon(newIcon);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setForeground(Color.WHITE);
    }

    public void paintTextField(JTextField textField, JLabel labelForFont){
        textField.setBackground(new Color(40,44,52));
        textField.setFont(labelForFont.getFont());
        textField.setForeground(labelForFont.getForeground());
    }

    public HttpResponse<String> sendApiGet(String uri) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        return response;
    }
}
