package gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Equipment;
import model.Hero;
import model.Player;
import service.SearchService;

public class PlayerLookupPanel extends JPanel {
    private final SearchService searchService;
    private final JTextField queryField = new JTextField(18);
    private final JTextArea outputArea = PanelFactory.createOutputArea(
            "Player lookup output will appear here after service actions are connected.");

    public PlayerLookupPanel(SearchService searchService) {
        this.searchService = searchService;
        JButton searchButton = new JButton("Search Player");
        searchButton.addActionListener(event -> searchPlayer());
        PanelFactory.applyStandardLayout(this,
                PanelFactory.createSearchPanel("Player ID or name:", queryField, searchButton),
                outputArea);
    }

    public SearchService getSearchService() {
        return searchService;
    }

    private void searchPlayer() {
        Player player = searchService.findPlayer(queryField.getText());
        if (player == null) {
            outputArea.setText("Player not found.");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(player.getReport()).append('\n');
        builder.append("Owned heroes and compatible equipment:\n");
        for (Hero hero : player.getOwnedHeroes()) {
            builder.append("- ").append(hero.getReport()).append('\n');
            for (Equipment item : hero.getCompatibleEquipment()) {
                builder.append("  * ").append(item.getName()).append('\n');
            }
        }
        outputArea.setText(builder.toString());
    }
}
