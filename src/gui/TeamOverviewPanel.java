package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import service.SearchService;

public class TeamOverviewPanel extends JPanel {
    private final SearchService searchService;

    public TeamOverviewPanel(SearchService searchService) {
        this.searchService = searchService;
        add(new JLabel("Team overview GUI framework placeholder"));
    }

    public SearchService getSearchService() {
        return searchService;
    }
}
