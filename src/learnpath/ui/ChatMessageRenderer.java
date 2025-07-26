package learnpath.ui;

import javax.swing.*;
import java.awt.*;

public class ChatMessageRenderer implements ListCellRenderer<Object> {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        
        ChatMessagePanel messagePanel = (ChatMessagePanel) value;

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
        wrapper.add(messagePanel);
        
        if (isSelected) {
            wrapper.setBackground(list.getSelectionBackground());
        } else {
            wrapper.setBackground(list.getBackground());
        }
        
        return wrapper;
    }
}
