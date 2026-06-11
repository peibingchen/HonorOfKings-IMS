package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import service.SearchService;

public class PlayerLookupPanel extends JPanel {
    private final SearchService searchService;

    public PlayerLookupPanel(SearchService searchService) {
        this.searchService = searchService;
        add(new JLabel("Player lookup GUI framework placeholder"));
    }

    public SearchService getSearchService() {
        return searchService;
    }
}
