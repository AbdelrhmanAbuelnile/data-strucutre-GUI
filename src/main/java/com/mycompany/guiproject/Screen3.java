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

public class Screen3 extends JPanel {
    public Screen3(GUIproject guiProject) {
        LinkedListVisualization linkedListVisualization = new LinkedListVisualization(guiProject);

        JButton backToHomeFromScreen3Button = new JButton("Back to Home");
        add(backToHomeFromScreen3Button);

        backToHomeFromScreen3Button.addActionListener(e -> {
            JPanel parent = (JPanel) getParent();
            parent.removeAll();
            parent.add(new HomePanel(guiProject));
            parent.revalidate();
            parent.repaint();
            guiProject.setTitle("Home Page");
        });

        setLayout(new BorderLayout());
        add(linkedListVisualization, BorderLayout.CENTER);
        add(backToHomeFromScreen3Button, BorderLayout.SOUTH);
    }
}

