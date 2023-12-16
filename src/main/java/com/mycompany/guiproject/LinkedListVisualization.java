package com.mycompany.guiproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class LinkedListVisualization extends JPanel {
    private LinkedList<Integer> linkedList;
    private DefaultListModel<String> listModel;
    private JList<String> linkedListDisplay;

    public LinkedListVisualization(GUIproject guiProject) {
        linkedList = new LinkedList<>();
        listModel = new DefaultListModel<>();
        linkedListDisplay = new JList<>(listModel);
        linkedListDisplay.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        linkedListDisplay.setVisibleRowCount(1); // Display elements in a single row
        linkedListDisplay.setBackground(new Color(165, 153, 255));
        JButton backButton = new JButton("Back to Home");
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JTextField inputField = new JTextField(10);

        backButton.addActionListener(e -> {
            JPanel parent = (JPanel) getParent();
            parent.removeAll();
            parent.add(new HomePanel(guiProject));
            parent.revalidate();
            parent.repaint();
        });

        addButton.addActionListener(e -> {
            try {
                int element = Integer.parseInt(inputField.getText());
                linkedList.add(element);
                updateList();
                inputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
            }
        });

        removeButton.addActionListener(e -> {
            if (!linkedList.isEmpty()) {
                linkedList.removeFirst();
                updateList();
            } else {
                JOptionPane.showMessageDialog(null, "List is empty.");
            }
        });
        
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButton.doClick(); // Simulate a click on the "Add" button when Enter is pressed
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Enter element:"));
        controlPanel.add(inputField);
        controlPanel.add(addButton);
        controlPanel.add(removeButton);

        setLayout(new BorderLayout());
//        add(backButton, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.NORTH);
        add(new JScrollPane(linkedListDisplay), BorderLayout.CENTER);

        setBackground(new Color(165, 153, 255)); // Set background color

        linkedListDisplay.setCellRenderer(new PointerListRenderer()); // Set custom cell renderer

        setPreferredSize(new Dimension(500, 100)); // Adjust height for the single row

        // Set the JList layout to FlowLayout for left alignment
        linkedListDisplay.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); // Adjust spacing here
        linkedListDisplay.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Adjust margins
    }

    private void updateList() {
        listModel.clear();
        for (int element : linkedList) {
            listModel.addElement(String.valueOf(element));
        }
        linkedListDisplay.repaint(); // Repaint the JList to reflect changes
    }

    private class PointerListRenderer extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Adjust spacing here
            panel.setBackground(Color.WHITE); // Set background color for the panel
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add borders

            JLabel elementLabel = new JLabel(String.valueOf(value));

            if (index < listModel.size() - 1) {
                JLabel arrowLabel = new JLabel(" -> ");
                panel.add(elementLabel);
                panel.add(arrowLabel);
            } else {
                JLabel arrowLabel = new JLabel(" -> null");
                panel.add(elementLabel);
                panel.add(arrowLabel);
            }

            return panel;
        }
    }
}
