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


public class Screen2 extends JPanel {
    public Screen2(GUIproject guiProject) {
        
        QueueVisualization queueVisualization = new QueueVisualization(guiProject);

        JButton backToHomeFromScreen2Button = new JButton("Back to Home");
        add(backToHomeFromScreen2Button);

        backToHomeFromScreen2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel parent = (JPanel) getParent();
                parent.removeAll();
                parent.add(new HomePanel(guiProject));
                parent.revalidate();
                parent.repaint();
                guiProject.setTitle("Home Page");
            }
        });

        setLayout(new BorderLayout());
        add(queueVisualization, BorderLayout.CENTER);
        add(backToHomeFromScreen2Button, BorderLayout.SOUTH);
    }
}

