package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

final class PanelFactory {
    private PanelFactory() {
    }

    static JTextArea createOutputArea(String placeholder) {
        JTextArea output = new JTextArea(placeholder);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        return output;
    }

    static JPanel createSearchPanel(String label, JTextField field, JButton button) {
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.add(new JLabel(label));
        controls.add(field);
        controls.add(button);
        return controls;
    }

    static void applyStandardLayout(JPanel panel, JPanel controls, JTextArea output) {
        panel.setLayout(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        panel.add(controls, BorderLayout.NORTH);
        panel.add(new JScrollPane(output), BorderLayout.CENTER);
    }
}
