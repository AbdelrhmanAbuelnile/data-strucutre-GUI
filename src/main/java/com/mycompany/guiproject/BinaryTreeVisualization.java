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
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.w3c.dom.Node;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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


class CustomCellRenderer extends DefaultTreeCellRenderer {
    private String foundNodeName;

    public void setFoundNodeName(String nodeName) {
        this.foundNodeName = nodeName;
    }
    public String getFoundNodeName() {
    return foundNodeName;
}

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        String nodeName = (String) node.getUserObject();
        if (nodeName.equals(foundNodeName)) {
            setBackgroundNonSelectionColor(Color.RED);
        } else {
            setBackgroundNonSelectionColor(Color.WHITE);
        }
        return this;
    }
}

public class BinaryTreeVisualization extends JPanel {
    private static final int NODE_WIDTH = 100;
    private static final int NODE_HEIGHT = 50;
    private static final int H_GAP = 80;
    private static final int V_GAP = 100;
    CustomCellRenderer renderer = new CustomCellRenderer();
    // void setCellRenderer(CustomCellRenderer renderer); // Fix: Add parameter name
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
        JButton sortButton = new JButton("Sort Tree");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortTree();
                repaint();
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
        // Create a new JTextField for search input
        JTextField searchInputField = new JTextField(15);

        // Create a new JButton for search
        JButton searchButton = new JButton("Search Node");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchInput = searchInputField.getText();
                if (!searchInput.isEmpty()) {
                    BinaryTreeNode foundNode = searchNode(root, searchInput);
                    if (foundNode != null) {
                        System.out.println("Node found: " + foundNode.getData());
                        renderer.setFoundNodeName(foundNode.getData());
                    } else {
                        JOptionPane.showMessageDialog(null, "Node not found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    }
                    searchInputField.setText("");
                    repaint();  // repaint the tree to apply the new color
                }
            }
        });
        JButton clearButton = new JButton("Clear Tree");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                root = null;  // Clear the tree
                repaint();  // Repaint the panel to reflect the change
            }
        });

// Add the search input field and button to the input panel

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Search Node: "));
        inputPanel.add(searchInputField);
        inputPanel.add(searchButton);
        inputPanel.add(new JLabel("Enter Node Name: "));
        inputPanel.add(userInputField);
        inputPanel.add(addButton);
        inputPanel.add(sortButton);
        inputPanel.add(clearButton);
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

    private ArrayList<String> nodes = new ArrayList<>();

    private void traverseTree(BinaryTreeNode node) {
        if (node != null) {
            nodes.add(node.getData());
            traverseTree(node.getLeft());
            traverseTree(node.getRight());
        }
    }
    
    private BinaryTreeNode createTreeFromSortedList(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        BinaryTreeNode node = new BinaryTreeNode(nodes.get(mid));
        node.setLeft(createTreeFromSortedList(start, mid - 1));
        node.setRight(createTreeFromSortedList(mid + 1, end));
        return node;
    }
    
    public void sortTree() {
        traverseTree(root);
        Collections.sort(nodes);
        root = createTreeFromSortedList(0, nodes.size() - 1);
        nodes.clear();
    }

    private BinaryTreeNode searchNode(BinaryTreeNode node, String nodeName) {
        if (node == null) {
            return null;
        }
        if (node.getData().equals(nodeName)) {
            return node;
        }
        BinaryTreeNode foundNode = searchNode(node.getLeft(), nodeName);
        if (foundNode == null) {
            foundNode = searchNode(node.getRight(), nodeName);
        }
        return foundNode;
    }

    private void drawTree(Graphics g, BinaryTreeNode node, int x, int y, int xOffset) {
        if (node == null) return;
    
        if (node.getData().equals(renderer.getFoundNodeName())) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.WHITE);
        }
    
        g.fillOval(x, y, NODE_WIDTH, NODE_HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20)); // Set new font size here
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



