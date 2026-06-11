package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import service.AuthenticationService;

public class LoginPanel extends JPanel {
    private final AuthenticationService authenticationService;

    public LoginPanel(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        add(new JLabel("Login GUI framework placeholder"));
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }
}
