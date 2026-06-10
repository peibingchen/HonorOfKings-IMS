package service;

import model.Person;

public class RelationshipManagementService {
    private final GameDataManager dataManager;
    private final PermissionService permissionService;
    private final DataValidationService validationService;

    public RelationshipManagementService(GameDataManager dataManager, PermissionService permissionService,
                                         DataValidationService validationService) {
        this.dataManager = dataManager;
        this.permissionService = permissionService;
        this.validationService = validationService;
    }

    public boolean assignHeroToPlayer(Person user, String playerId, String heroId) {
        permissionService.requireAdmin(user);
        validationService.requireFound(dataManager.getPlayer(playerId), "Player", playerId);
        validationService.requireFound(dataManager.getHero(heroId), "Hero", heroId);
        return false;
    }

    public boolean removeHeroFromPlayer(Person user, String playerId, String heroId) {
        permissionService.requireAdmin(user);
        validationService.requireFound(dataManager.getPlayer(playerId), "Player", playerId);
        validationService.requireFound(dataManager.getHero(heroId), "Hero", heroId);
        return false;
    }

    public boolean assignEquipmentToHero(Person user, String heroId, String equipmentId) {
        permissionService.requireAdmin(user);
        validationService.requireFound(dataManager.getHero(heroId), "Hero", heroId);
        validationService.requireFound(dataManager.getEquipment(equipmentId), "Equipment", equipmentId);
        return false;
    }

    public boolean removeEquipmentFromHero(Person user, String heroId, String equipmentId) {
        permissionService.requireAdmin(user);
        validationService.requireFound(dataManager.getHero(heroId), "Hero", heroId);
        validationService.requireFound(dataManager.getEquipment(equipmentId), "Equipment", equipmentId);
        return false;
    }
}
