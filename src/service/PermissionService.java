package service;

import model.Person;
import model.Role;

public class PermissionService {
    public boolean isAdmin(Person user) {
        return user != null && user.getRole() == Role.ADMIN;
    }

    public boolean isPlayer(Person user) {
        return user != null && user.getRole() == Role.PLAYER;
    }

    public void requireAdmin(Person user) {
        if (!isAdmin(user)) {
            throw new SecurityException("Only admins can perform this action.");
        }
    }

    public boolean canEditPlayer(Person user, String playerId) {
        return isAdmin(user) || (isPlayer(user) && user.getId().equals(playerId));
    }
}
