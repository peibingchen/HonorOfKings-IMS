package service;

import model.Person;

public class AuthenticationService {
    private final GameDataManager dataManager;
    private Person currentUser;

    public AuthenticationService(GameDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public boolean login(String id, String password) {
        Person user = dataManager.getUser(id);
        if (user != null && user.checkPassword(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public Person getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
