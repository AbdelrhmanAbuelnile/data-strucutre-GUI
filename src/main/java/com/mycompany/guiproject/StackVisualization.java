package com.mycompany.guiproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class StackVisualization extends JPanel {
    private Stack<Integer> stack;
    private DefaultListModel<String> stackListModel;
    private JList<String> stackList;

    public StackVisualization(GUIproject guiProject) {
        stack = new Stack<>();
        stackListModel = new DefaultListModel<>();
        stackList = new JList<>(stackListModel);
        stackList.setCellRenderer(new CustomListCellRenderer()); // Set custom cell renderer

        stackList.setBackground(new Color(165, 153, 255)); // Setting background color

        JButton backButton = new JButton("Back to Home");
        JButton pushButton = new JButton("Push");
        JButton popButton = new JButton("Pop");
        JButton clearButton = new JButton("Clear Stack");
        JTextField inputField = new JTextField(10);
        
        

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel parent = (JPanel) getParent();
                parent.removeAll();
                parent.add(new HomePanel(guiProject));
                parent.revalidate();
                parent.repaint();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stack.clear();  // Clear the stack
                updateStackList();  // Update the stack list to reflect the change
            }
        });

        pushButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int element = Integer.parseInt(inputField.getText());
                    stack.push(element);
                    updateStackList();
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                }
            }
        });
        
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pushButton.doClick(); // Simulate a click on the "Add" button when Enter is pressed
            }
        });

        popButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!stack.isEmpty()) {
                    stack.pop();
                    updateStackList();
                } else {
                    JOptionPane.showMessageDialog(null, "Stack is empty.");
                }
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Enter element:"));
        controlPanel.add(inputField);
        controlPanel.add(pushButton);
        controlPanel.add(popButton);
        controlPanel.add(clearButton);
        // controlPanel.add(backButton);
        
        setLayout(new BorderLayout());
        // add(backButton, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.NORTH);
        add(stackList, BorderLayout.CENTER);

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500, 500));
        JScrollPane scrollPane = new JScrollPane(stackList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

    }

    private void updateStackList() {
        stackListModel.clear();
        for (int i = stack.size() - 1; i >= 0; i--) {
            stackListModel.addElement(String.valueOf(stack.toArray()[i]));
        }
        stackList.setCellRenderer(new CustomListCellRenderer());
        stackList.repaint();
        stackList.setBackground(new Color(165, 153, 255)); // Resetting background color after update
    }

    // Custom cell renderer for the JList
    class CustomListCellRenderer extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;

        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setOpaque(true);
            label.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK),
                    BorderFactory.createEmptyBorder(30, 40, 30, 40)// Increased padding to create more space between items
            ));
            label.setPreferredSize(new Dimension(50, 50));
            label.setBackground(new Color(78, 59, 224));
            label.setHorizontalAlignment(SwingConstants.CENTER);

            return label;
        }
    }
}
