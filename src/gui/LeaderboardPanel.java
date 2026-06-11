package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import service.RankingService;

public class LeaderboardPanel extends JPanel {
    private final RankingService rankingService;

    public LeaderboardPanel(RankingService rankingService) {
        this.rankingService = rankingService;
        add(new JLabel("Leaderboard GUI framework placeholder"));
    }

    public RankingService getRankingService() {
        return rankingService;
    }
}
