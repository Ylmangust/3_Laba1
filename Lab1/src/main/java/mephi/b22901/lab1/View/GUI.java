/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.b22901.lab1.View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mephi.b22901.lab1.Controller.Controller;

/**
 *
 * @author Регина
 */
public class GUI {

    private Controller controller;
    private JFrame frame;
    private JButton importButton;
    private JButton processButton;
    private JButton exportButton;
    private JButton exitButton;

    public GUI(Controller controller) {
        this.controller = controller;
        frame = new JFrame("XLSX Data Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 300);
        frame.setLayout(new BorderLayout());

        importButton = new JButton("Import xlsx file");
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.read();
            }
        });

        processButton = new JButton("Start process");
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        exportButton = new JButton("Export xlsx file");
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(importButton);
        panel.add(processButton);
        panel.add(exportButton);
        panel.add(exitButton);

        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
