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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BinaryTreeNode {
    private String data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public String getData() {
        return data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setLeft(BinaryTreeNode node) {
        left = node;
    }

    public void setRight(BinaryTreeNode node) {
        right = node;
    }
}

public class BinaryTreeVisualization extends JPanel {
    private static final int NODE_WIDTH = 100;
    private static final int NODE_HEIGHT = 50;
    private static final int H_GAP = 80;
    private static final int V_GAP = 100;

    private BinaryTreeNode root;
    private BinaryTreeNode selectedNode;
    private JTextField userInputField;

    public BinaryTreeVisualization() {
        root = null;
        selectedNode = null;

        setPreferredSize(new Dimension(800, 600));

        userInputField = new JTextField(15);
        JButton addButton = new JButton("Add Node");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = userInputField.getText();
                if (!userInput.isEmpty()) {
                    addNode(userInput);
                    userInputField.setText("");
                }
            }
        });
        
        userInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = userInputField.getText();
                if (!userInput.isEmpty()) {
                    addNode(userInput);
                    userInputField.setText("");
                }
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Node Name: "));
        inputPanel.add(userInputField);
        inputPanel.add(addButton);
        setBackground(new Color(165, 153, 255));
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.SOUTH);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectNode(evt.getX(), evt.getY());
            }
        });
    }

    private void selectNode(int x, int y) {
        selectedNode = findNode(root, x, y);
        repaint();
    }

    private BinaryTreeNode findNode(BinaryTreeNode node, int x, int y) {
        if (node != null) {
            int nodeX = getWidth() / 2;
            int nodeY = 30;
            if (x >= nodeX && x <= nodeX + NODE_WIDTH && y >= nodeY && y <= nodeY + NODE_HEIGHT) {
                return node;
            } else {
                BinaryTreeNode leftChild = findNode(node.getLeft(), x, y);
                BinaryTreeNode rightChild = findNode(node.getRight(), x, y);
                if (leftChild != null) {
                    return leftChild;
                } else if (rightChild != null) {
                    return rightChild;
                }
            }
        }
        return null;
    }

    private void addNode(String nodeName) {
        if (root == null) {
            root = new BinaryTreeNode(nodeName);
        } else {
            insertNode(root, nodeName);
        }
        repaint();
    }

    private void insertNode(BinaryTreeNode node, String nodeName) {
        if (node.getLeft() == null) {
            node.setLeft(new BinaryTreeNode(nodeName));
        } else if (node.getRight() == null) {
            node.setRight(new BinaryTreeNode(nodeName));
        } else {
            java.util.Queue<BinaryTreeNode> queue = new java.util.LinkedList<>();
            queue.add(node);

            while (!queue.isEmpty()) {
                BinaryTreeNode current = queue.poll();

                if (current.getLeft() == null) {
                    current.setLeft(new BinaryTreeNode(nodeName));
                    return;
                } else if (current.getRight() == null) {
                    current.setRight(new BinaryTreeNode(nodeName));
                    return;
                } else {
                    queue.add(current.getLeft());
                    queue.add(current.getRight());
                }
            }
        }
    }

    private void drawTree(Graphics g, BinaryTreeNode node, int x, int y, int xOffset) {
        if (node == null) return;

        g.setColor(Color.WHITE);
        g.fillOval(x, y, NODE_WIDTH, NODE_HEIGHT);
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(node.getData());
        int textHeight = fm.getHeight();
        int textX = x + (NODE_WIDTH - textWidth) / 2;
        int textY = y + (NODE_HEIGHT - textHeight) / 2 + fm.getAscent();
        g.drawString(node.getData(), textX, textY);

        int childXLeft = x - xOffset;
        int childXRight = x + xOffset;
        int childY = y + V_GAP;

        if (node.getLeft() != null) {
            g.setColor(Color.WHITE);
            g.drawLine(x + NODE_WIDTH / 2, y + NODE_HEIGHT, childXLeft + NODE_WIDTH / 2, childY);
            drawTree(g, node.getLeft(), childXLeft, childY, xOffset / 2);
        }
        if (node.getRight() != null) {
            g.setColor(Color.WHITE);
            g.drawLine(x + NODE_WIDTH / 2, y + NODE_HEIGHT, childXRight + NODE_WIDTH / 2, childY);
            drawTree(g, node.getRight(), childXRight, childY, xOffset / 2);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (root != null) {
            drawTree(g, root, getWidth() / 2, 30, 200);
        }
    }
}



