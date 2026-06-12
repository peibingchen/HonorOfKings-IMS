package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import service.AuthenticationService;
import service.CombatSimulator;
import service.RankingService;
import service.RecommendationEngine;
import service.SearchService;

public class MainFrame extends JFrame {
    public MainFrame(AuthenticationService authenticationService,
                     SearchService searchService,
                     RankingService rankingService,
                     CombatSimulator combatSimulator,
                     RecommendationEngine recommendationEngine) {
        setTitle("Honor of Kings IMS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Login", new LoginPanel(authenticationService));
        tabs.addTab("Player Lookup", new PlayerLookupPanel(searchService));
        tabs.addTab("Team Overview", new TeamOverviewPanel(searchService));
        tabs.addTab("Hero Details", new HeroDetailsPanel(searchService, recommendationEngine));
        tabs.addTab("Leaderboard", new LeaderboardPanel(rankingService));
        tabs.addTab("Combat", new CombatSimulationPanel(searchService, combatSimulator));
        tabs.addTab("Recommendation", new RecommendationPanel(searchService, recommendationEngine));
        add(tabs);
    }
}
