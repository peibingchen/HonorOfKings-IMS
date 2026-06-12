package gui;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.BattleResult;
import model.Hero;
import service.CombatSimulator;
import service.SearchService;

public class CombatSimulationPanel extends JPanel {
    private final SearchService searchService;
    private final CombatSimulator combatSimulator;
    private final JTextField firstHeroField = new JTextField(12);
    private final JTextField secondHeroField = new JTextField(12);
    private final JTextArea outputArea = PanelFactory.createOutputArea(
            "Choose two heroes and run a combat simulation.");

    public CombatSimulationPanel(SearchService searchService, CombatSimulator combatSimulator) {
        this.searchService = searchService;
        this.combatSimulator = combatSimulator;

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton simulateButton = new JButton("Simulate");
        controls.add(new JLabel("First hero:"));
        controls.add(firstHeroField);
        controls.add(new JLabel("Second hero:"));
        controls.add(secondHeroField);
        controls.add(simulateButton);

        simulateButton.addActionListener(event -> simulate());
        PanelFactory.applyStandardLayout(this, controls, outputArea);
    }

    private void simulate() {
        Hero first = searchService.findHero(firstHeroField.getText());
        Hero second = searchService.findHero(secondHeroField.getText());
        if (first == null || second == null) {
            outputArea.setText("Both heroes must exist before combat simulation can run.");
            return;
        }

        BattleResult result = combatSimulator.simulate(first, second);
        StringBuilder builder = new StringBuilder();
        builder.append(result.getReport()).append('\n');
        for (String entry : result.getCombatLog()) {
            builder.append("- ").append(entry).append('\n');
        }
        outputArea.setText(builder.toString());
    }
}
