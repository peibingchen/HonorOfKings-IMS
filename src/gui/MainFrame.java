package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import service.AuthenticationService;
import service.RankingService;
import service.SearchService;

public class MainFrame extends JFrame {
    public MainFrame(AuthenticationService authenticationService, SearchService searchService, RankingService rankingService) {
        setTitle("Honor of Kings IMS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Login", new LoginPanel(authenticationService));
        tabs.addTab("Player Lookup", new PlayerLookupPanel(searchService));
        tabs.addTab("Team Overview", new TeamOverviewPanel(searchService));
        tabs.addTab("Hero Details", new HeroDetailsPanel(searchService));
        tabs.addTab("Leaderboard", new LeaderboardPanel(rankingService));
        add(tabs);
    }
}
