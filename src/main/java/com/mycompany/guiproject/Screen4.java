/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guiproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Screen4 extends JPanel {
    public Screen4(GUIproject guiProject) {
        BinaryTreeVisualization BinaryTreeVisualization = new BinaryTreeVisualization();

        JButton backToHomeFromScreen4Button = new JButton("Back to Home");
        add(backToHomeFromScreen4Button);

        backToHomeFromScreen4Button.addActionListener(e -> {
            JPanel parent = (JPanel) getParent();
            parent.removeAll();
            parent.add(new HomePanel(guiProject));
            parent.revalidate();
            parent.repaint();
            guiProject.setTitle("Home Page");
        });

        setLayout(new BorderLayout());
        add(BinaryTreeVisualization, BorderLayout.CENTER);
        add(backToHomeFromScreen4Button, BorderLayout.SOUTH);
    }
}
