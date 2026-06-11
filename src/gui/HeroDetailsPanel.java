package gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Equipment;
import model.Hero;
import model.Player;
import service.SearchService;

public class HeroDetailsPanel extends JPanel {
    private final SearchService searchService;
    private final JTextField queryField = new JTextField(18);
    private final JTextArea outputArea = PanelFactory.createOutputArea(
            "Hero details output will appear here after service actions are connected.");

    public HeroDetailsPanel(SearchService searchService) {
        this.searchService = searchService;
        JButton searchButton = new JButton("Search Hero");
        searchButton.addActionListener(event -> searchHero());
        PanelFactory.applyStandardLayout(this,
                PanelFactory.createSearchPanel("Hero ID or name:", queryField, searchButton),
                outputArea);
    }

    public SearchService getSearchService() {
        return searchService;
    }

    private void searchHero() {
        Hero hero = searchService.findHero(queryField.getText());
        if (hero == null) {
            outputArea.setText("Hero not found.");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(hero.getReport()).append('\n');
        builder.append("Compatible equipment:\n");
        for (Equipment item : hero.getCompatibleEquipment()) {
            builder.append("- ").append(item.getReport()).append('\n');
        }
        builder.append("Players owning this hero:\n");
        for (Player player : searchService.findPlayersOwningHero(hero)) {
            builder.append("- ").append(player.getName()).append('\n');
        }
        outputArea.setText(builder.toString());
    }
}
