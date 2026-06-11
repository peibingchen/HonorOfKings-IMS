package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Person;
import service.AuthenticationService;

public class LoginPanel extends JPanel {
    private final AuthenticationService authenticationService;
    private final JTextField userIdField = new JTextField(12);
    private final JPasswordField passwordField = new JPasswordField(12);
    private final JTextArea outputArea = PanelFactory.createOutputArea(
            "Login controls are ready. Authentication actions will be connected in the next GUI stage.");

    public LoginPanel(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.add(new JLabel("User ID:"));
        controls.add(userIdField);
        controls.add(new JLabel("Password:"));
        controls.add(passwordField);
        JButton loginButton = new JButton("Login");
        JButton logoutButton = new JButton("Logout");
        controls.add(loginButton);
        controls.add(logoutButton);

        loginButton.addActionListener(event -> login());
        logoutButton.addActionListener(event -> logout());

        add(controls, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    private void login() {
        String userId = userIdField.getText().trim();
        String password = new String(passwordField.getPassword());
        if (authenticationService.login(userId, password)) {
            Person user = authenticationService.getCurrentUser();
            outputArea.setText("Login successful.\n"
                    + "Current user: " + user.getName() + " (" + user.getRole() + ")");
        } else {
            outputArea.setText("Login failed. Check ID and password.");
        }
    }

    private void logout() {
        authenticationService.logout();
        outputArea.setText("Logged out.");
    }
}
