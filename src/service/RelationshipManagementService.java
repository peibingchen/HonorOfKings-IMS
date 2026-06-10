package service;

import model.Person;
import model.Equipment;
import model.Hero;
import model.Player;

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
        Player player = dataManager.getPlayer(playerId);
        validationService.requireFound(player, "Player", playerId);
        Hero hero = dataManager.getHero(heroId);
        validationService.requireFound(hero, "Hero", heroId);
        if (player.getOwnedHeroes().contains(hero)) {
            return false;
        }
        player.addHero(hero);
        return true;
    }

    public boolean removeHeroFromPlayer(Person user, String playerId, String heroId) {
        permissionService.requireAdmin(user);
        Player player = dataManager.getPlayer(playerId);
        validationService.requireFound(player, "Player", playerId);
        Hero hero = dataManager.getHero(heroId);
        validationService.requireFound(hero, "Hero", heroId);
        return player.removeHero(hero);
    }

    public boolean assignEquipmentToHero(Person user, String heroId, String equipmentId) {
        permissionService.requireAdmin(user);
        Hero hero = dataManager.getHero(heroId);
        validationService.requireFound(hero, "Hero", heroId);
        Equipment equipment = dataManager.getEquipment(equipmentId);
        validationService.requireFound(equipment, "Equipment", equipmentId);
        if (hero.getCompatibleEquipment().contains(equipment)) {
            return false;
        }
        hero.addCompatibleEquipment(equipment);
        return true;
    }

    public boolean removeEquipmentFromHero(Person user, String heroId, String equipmentId) {
        permissionService.requireAdmin(user);
        Hero hero = dataManager.getHero(heroId);
        validationService.requireFound(hero, "Hero", heroId);
        Equipment equipment = dataManager.getEquipment(equipmentId);
        validationService.requireFound(equipment, "Equipment", equipmentId);
        return hero.removeCompatibleEquipment(equipment);
    }
}
