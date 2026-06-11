package gui;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import service.RankingService;

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
        controls.add(metricBox);
        controls.add(limitField);
        controls.add(new JButton("Show Leaderboard"));
        PanelFactory.applyStandardLayout(this, controls, outputArea);
    }

    public RankingService getRankingService() {
        return rankingService;
    }
}
