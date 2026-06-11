package gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Player;
import model.Team;
import service.SearchService;

public class TeamOverviewPanel extends JPanel {
    private final SearchService searchService;
    private final JTextField queryField = new JTextField(18);
    private final JTextArea outputArea = PanelFactory.createOutputArea(
            "Team overview output will appear here after service actions are connected.");

    public TeamOverviewPanel(SearchService searchService) {
        this.searchService = searchService;
        JButton searchButton = new JButton("Search Team");
        searchButton.addActionListener(event -> searchTeam());
        PanelFactory.applyStandardLayout(this,
                PanelFactory.createSearchPanel("Team ID or name:", queryField, searchButton),
                outputArea);
    }

    public SearchService getSearchService() {
        return searchService;
    }

    private void searchTeam() {
        Team team = searchService.findTeam(queryField.getText());
        if (team == null) {
            outputArea.setText("Team not found.");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(team.getReport()).append('\n');
        builder.append("Members:\n");
        for (Player player : team.getMembers()) {
            builder.append("- ").append(player.getReport()).append('\n');
        }
        outputArea.setText(builder.toString());
    }
}
