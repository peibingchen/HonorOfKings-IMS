package gui;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Player;
import service.RankingService;

import java.util.List;

public class LeaderboardPanel extends JPanel {
    private final RankingService rankingService;
    private final JComboBox<String> metricBox = new JComboBox<>(new String[] {
            "Win rate",
            "Level",
            "Number of matches"
    });
    private final JTextField limitField = new JTextField("5", 5);
    private final JTextArea outputArea = PanelFactory.createOutputArea(
            "Leaderboard output will appear here after service actions are connected.");

    public LeaderboardPanel(RankingService rankingService) {
        this.rankingService = rankingService;
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton showButton = new JButton("Show Leaderboard");
        controls.add(metricBox);
        controls.add(limitField);
        controls.add(showButton);
        showButton.addActionListener(event -> showLeaderboard());
        PanelFactory.applyStandardLayout(this, controls, outputArea);
    }

    public RankingService getRankingService() {
        return rankingService;
    }

    private void showLeaderboard() {
        int limit = parseLimit();
        String metric = (String) metricBox.getSelectedItem();
        List<Player> players;
        if ("Level".equals(metric)) {
            players = rankingService.topPlayersByLevel(limit);
        } else if ("Number of matches".equals(metric)) {
            players = rankingService.topPlayersByMatches(limit);
        } else {
            players = rankingService.topPlayersByWinRate(limit);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Ranked by: ").append(metric).append('\n');
        builder.append("Tie handling: selected metric, then win rate, level, and name.\n");
        for (int i = 0; i < players.size(); i++) {
            builder.append(i + 1).append(". ").append(players.get(i).getReport()).append('\n');
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
