package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import service.SearchService;

public class HeroDetailsPanel extends JPanel {
    private final SearchService searchService;

    public HeroDetailsPanel(SearchService searchService) {
        this.searchService = searchService;
        add(new JLabel("Hero details GUI framework placeholder"));
    }

    public SearchService getSearchService() {
        return searchService;
    }
}
