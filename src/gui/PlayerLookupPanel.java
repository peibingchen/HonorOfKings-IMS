package gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import service.SearchService;

public class PlayerLookupPanel extends JPanel {
    private final SearchService searchService;
    private final JTextField queryField = new JTextField(18);
    private final JTextArea outputArea = PanelFactory.createOutputArea(
            "Player lookup output will appear here after service actions are connected.");

    public PlayerLookupPanel(SearchService searchService) {
        this.searchService = searchService;
        JButton searchButton = new JButton("Search Player");
        PanelFactory.applyStandardLayout(this,
                PanelFactory.createSearchPanel("Player ID or name:", queryField, searchButton),
                outputArea);
    }

    public SearchService getSearchService() {
        return searchService;
    }
}
