package gui;

import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Hero;
import model.Player;
import model.Recommendation;
import model.Team;
import service.RecommendationEngine;
import service.SearchService;

public class RecommendationPanel extends JPanel {
    private final SearchService searchService;
    private final RecommendationEngine recommendationEngine;
    private final JComboBox<String> recommendationType = new JComboBox<>(new String[] {
            "Equipment for hero",
            "Heroes for player",
            "Team composition"
    });
    private final JTextField queryField = new JTextField(14);
    private final JTextField limitField = new JTextField("5", 5);
    private final JTextArea outputArea = PanelFactory.createOutputArea(
            "Select a recommendation type and enter a hero, player, or team ID/name.");

    public RecommendationPanel(SearchService searchService, RecommendationEngine recommendationEngine) {
        this.searchService = searchService;
        this.recommendationEngine = recommendationEngine;

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton recommendButton = new JButton("Recommend");
        controls.add(recommendationType);
        controls.add(new JLabel("Query:"));
        controls.add(queryField);
        controls.add(new JLabel("Limit:"));
        controls.add(limitField);
        controls.add(recommendButton);

        recommendButton.addActionListener(event -> recommend());
        PanelFactory.applyStandardLayout(this, controls, outputArea);
    }

    private void recommend() {
        String type = (String) recommendationType.getSelectedItem();
        int limit = parseLimit();
        if ("Heroes for player".equals(type)) {
            Player player = searchService.findPlayer(queryField.getText());
            showRecommendations(player == null ? List.of() : recommendationEngine.recommendHeroes(player, limit));
        } else if ("Team composition".equals(type)) {
            Team team = searchService.findTeam(queryField.getText());
            showRecommendations(team == null ? List.of() : recommendationEngine.recommendTeamComposition(team));
        } else {
            Hero hero = searchService.findHero(queryField.getText());
            showRecommendations(hero == null ? List.of() : recommendationEngine.recommendEquipment(hero, limit));
        }
    }

    private void showRecommendations(List<Recommendation> recommendations) {
        if (recommendations.isEmpty()) {
            outputArea.setText("No recommendations available.");
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (Recommendation recommendation : recommendations) {
            builder.append("- ").append(recommendation.getReport()).append('\n');
        }
        outputArea.setText(builder.toString());
    }

    private int parseLimit() {
        try {
            return Math.max(1, Integer.parseInt(limitField.getText().trim()));
        } catch (NumberFormatException ex) {
            limitField.setText("5");
            return 5;
        }
    }
}
