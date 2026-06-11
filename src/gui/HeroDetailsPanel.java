package gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import service.SearchService;

public class HeroDetailsPanel extends JPanel {
    private final SearchService searchService;
    private final JTextField queryField = new JTextField(18);
    private final JTextArea outputArea = PanelFactory.createOutputArea(
            "Hero details output will appear here after service actions are connected.");

    public HeroDetailsPanel(SearchService searchService) {
        this.searchService = searchService;
        JButton searchButton = new JButton("Search Hero");
        PanelFactory.applyStandardLayout(this,
                PanelFactory.createSearchPanel("Hero ID or name:", queryField, searchButton),
                outputArea);
    }

    public SearchService getSearchService() {
        return searchService;
    }
}
