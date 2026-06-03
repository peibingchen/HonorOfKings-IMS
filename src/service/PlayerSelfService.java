package service;

import model.Hero;
import model.MatchRecord;
import model.Person;
import model.Player;

import java.util.List;

public class PlayerSelfService {
    private final GameDataManager dataManager;
    private final MatchHistoryService matchHistoryService;
    private final PermissionService permissionService;
    private final DataValidationService validationService;

    public PlayerSelfService(GameDataManager dataManager, MatchHistoryService matchHistoryService,
                             PermissionService permissionService, DataValidationService validationService) {
        this.dataManager = dataManager;
        this.matchHistoryService = matchHistoryService;
        this.permissionService = permissionService;
        this.validationService = validationService;
    }

    public Player getOwnProfile(Person user) {
        if (!permissionService.isPlayer(user)) {
            throw new SecurityException("Only player users have player profiles.");
        }
        Player player = dataManager.getPlayer(user.getId());
        validationService.requireFound(player, "Player", user.getId());
        return player;
    }

    public boolean updateOwnName(Person user, String newName) {
        Player player = getOwnProfile(user);
        player.setName(newName);
        return true;
    }

    public boolean updateOwnPassword(Person user, String newPassword) {
        Player player = getOwnProfile(user);
        player.setPassword(newPassword);
        return true;
    }

    public List<Hero> getOwnHeroes(Person user) {
        return getOwnProfile(user).getOwnedHeroes();
    }

    public List<MatchRecord> getOwnMatchHistory(Person user, int limit) {
        return matchHistoryService.getRecentMatchesForPlayer(getOwnProfile(user), limit);
    }
}
