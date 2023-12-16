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


public class Screen1 extends JPanel {
    public Screen1(GUIproject guiProject) {
        StackVisualization StackVisualization = new StackVisualization(guiProject);
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
        add(StackVisualization, BorderLayout.CENTER);
        add(backToHomeFromScreen2Button, BorderLayout.SOUTH);
    }
}



