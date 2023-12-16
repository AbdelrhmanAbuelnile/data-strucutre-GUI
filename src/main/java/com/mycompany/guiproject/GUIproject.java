/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.guiproject;

/**
 *
 * @author Samoka
 */
import javax.swing.*;

public class GUIproject extends JFrame {
    public GUIproject() {
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);

        JPanel homePanel = new HomePanel(this); // Default: HomePanel

        getContentPane().add(homePanel);
        setVisible(true);
    }
    public void changeTitle(String newTitle) {
        setTitle(newTitle);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIproject();
            }
        });
    }
}


