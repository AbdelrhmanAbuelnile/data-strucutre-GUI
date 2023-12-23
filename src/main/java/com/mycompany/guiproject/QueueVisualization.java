/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guiproject;

/**
 *
 * @author Samoka
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;

public class QueueVisualization extends JPanel {
    
    private Queue<Integer> queue;
    private DefaultListModel<String> queueListModel;
    private JList<String> queueList;
    private int frontPointer = -1;
    private int rearPointer = -1;

    public QueueVisualization(GUIproject guiProject) {
        queue = new LinkedList<>();
        queueListModel = new DefaultListModel<>();
        queueList = new JList<>(queueListModel);
        queueList.setCellRenderer(new CustomListCellRenderer()); // Set custom cell renderer

        queueList.setBackground(new Color(165, 153, 255)); // Setting background color

        JButton backButton = new JButton("Back to Home");
        JButton enqueueButton = new JButton("Enqueue");
        JButton dequeueButton = new JButton("Dequeue");
        JButton clearButton = new JButton("Clear");
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
            public void actionPerformed(ActionEvent e){
                queue.clear();
                updateQueueList();
            }
        });

        enqueueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int element = Integer.parseInt(inputField.getText());
                    queue.add(element);
                    rearPointer = queue.size() - 1;
                    if (frontPointer == -1) {
                        frontPointer = 0;
                    }
                    updateQueueList();
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                }
            }
        });

        dequeueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!queue.isEmpty()) {
                    queue.poll();
                    if (frontPointer != -1) {
                        frontPointer++;
                        if (frontPointer > rearPointer) {
                            frontPointer = -1;
                            rearPointer = -1;
                        }
                    }
                    updateQueueList();
                } else {
                    JOptionPane.showMessageDialog(null, "Queue is empty.");
                }
            }
        });
        
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int element = Integer.parseInt(inputField.getText());
                    queue.add(element);
                    rearPointer = queue.size() - 1;
                    if (frontPointer == -1) {
                        frontPointer = 0;
                    }
                    updateQueueList();
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                }
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Enter element:"));
        controlPanel.add(inputField);
        controlPanel.add(enqueueButton);
        controlPanel.add(dequeueButton);
        controlPanel.add(clearButton);
//        controlPanel.add(backButton);
        
        setLayout(new BorderLayout());
//        add(backButton, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.NORTH);
        add(queueList, BorderLayout.CENTER);
    
        

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500, 500));
        JScrollPane scrollPane = new JScrollPane(queueList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void updateQueueList() {
        queueListModel.clear();
        for (int i = 0; i < queue.size(); i++) {
            queueListModel.addElement(String.valueOf(queue.toArray()[i]));
        }
        if (frontPointer != -1 && rearPointer != -1) {
            queueList.setCellRenderer(new CustomListCellRenderer());
            queueList.repaint();
        }
        queueList.setBackground(new Color(165, 153, 255)); // Resetting background color after update
    }

    // Custom cell renderer for the JList
    class CustomListCellRenderer extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;

        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setOpaque(true);
            label.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK), // Border color
                    BorderFactory.createEmptyBorder(5, 10, 5, 10) // Padding
            ));
            label.setPreferredSize(new Dimension(50,50)); // Fixed width of 50px
            label.setBackground(new Color(78, 59, 224)); // Background color (RGB: 78, 59, 224)
            label.setHorizontalAlignment(SwingConstants.CENTER); // Center align text

            return label;
        }
    }
}
