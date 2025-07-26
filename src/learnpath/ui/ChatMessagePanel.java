package learnpath.ui;

import javax.swing.*;
import java.awt.*;

public class ChatMessagePanel extends JPanel {

    private JLabel lblSender;
    private JTextArea txtMessage;

    public ChatMessagePanel() {
        setLayout(new BorderLayout());
        setOpaque(false);

        lblSender = new JLabel();
        lblSender.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSender.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));

        txtMessage = new JTextArea();
        txtMessage.setWrapStyleWord(true);
        txtMessage.setLineWrap(true);
        txtMessage.setEditable(false);
        txtMessage.setFocusable(false);
        txtMessage.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMessage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtMessage.setOpaque(true); // <-- penting agar warna terlihat

        JPanel textBubble = new JPanel(new BorderLayout());
        textBubble.setOpaque(true);
        textBubble.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10)); // Padding bubble ke kiri/kanan
        textBubble.add(txtMessage, BorderLayout.CENTER);

        add(lblSender, BorderLayout.NORTH);
        add(textBubble, BorderLayout.CENTER);
    }

    public void setData(String sender, String message, Color bubbleColor) {
        lblSender.setText(sender);
        txtMessage.setText(message);
        txtMessage.setBackground(bubbleColor);
    }
}
