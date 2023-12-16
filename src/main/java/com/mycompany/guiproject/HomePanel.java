/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guiproject;

/**
 *
 * @author Samoka
 */
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel {
    private GUIproject guiProject;
    public HomePanel(GUIproject guiProject) {
        JButton goToScreen1Button = new JButton("Stack");
        JButton goToScreen2Button = new JButton("Queue");
        JButton goToScreen3Button = new JButton("Linked List");
        JButton goToScreen4Button = new JButton("Tree");
//        JButton goToScreen5Button = new JButton("Linked List");

        add(goToScreen3Button);
        add(goToScreen1Button);
        add(goToScreen2Button);
        add(goToScreen4Button);


        goToScreen1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel parent = (JPanel) getParent();
                parent.removeAll();
                parent.add(new Screen1(guiProject));
                parent.revalidate();
                parent.repaint();
                guiProject.changeTitle("Stack"); // Change the title here
            }
        });

        goToScreen2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel parent = (JPanel) getParent();
                parent.removeAll();
                parent.add(new Screen2(guiProject));
                parent.revalidate();
                parent.repaint();
                guiProject.changeTitle("Queue"); // Change the title here
            }
        });
        
        goToScreen3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel parent = (JPanel) getParent();
                parent.removeAll();
                parent.add(new Screen3(guiProject));
                parent.revalidate();
                parent.repaint();
                guiProject.changeTitle("Linked List");
            }
        });

        goToScreen4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel parent = (JPanel) getParent();
                parent.removeAll();
                parent.add(new Screen4(guiProject));
                parent.revalidate();
                parent.repaint();
                guiProject.changeTitle("Tree");
            }
        });
        
        this.setBackground(new Color(165, 153, 255));

    }
}
