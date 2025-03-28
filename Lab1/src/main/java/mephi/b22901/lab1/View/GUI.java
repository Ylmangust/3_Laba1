/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.b22901.lab1.View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import mephi.b22901.lab1.Controller.Controller;

/**
 *
 * @author Регина
 */
public class GUI {

    private Controller controller;

    ;

    public GUI(Controller controller) {
        this.controller = controller;
        JFrame frame = new JFrame("XLSX Data Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 300);
        frame.setLayout(new BorderLayout());

        JButton processButton = new JButton("Import file and start process");
        processButton.setFont(new Font("Lato", Font.BOLD, 15));
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = getPathForReading();
                if (path != null) {
                    controller.read(path);
                    controller.process();
                }
            }
        });

        JButton exportButton = new JButton("Export xlsx file");
        exportButton.setFont(new Font("Lato", Font.BOLD, 15));
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = getPathForExport();
                if (path != null) {
                    controller.export(path);
                }
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Lato", Font.BOLD, 15));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(processButton);
        panel.add(exportButton);
        panel.add(exitButton);

        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public String getPathForReading() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel file(*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        String path = null;
        int ret = fileChooser.showOpenDialog(null);
        if (ret == JFileChooser.APPROVE_OPTION) {
            path = fileChooser.getSelectedFile().getAbsolutePath();
        } else if (ret == JFileChooser.CANCEL_OPTION) {
            return null;
        }
        return path;
    }

    public int giveAnswer(Integer[] num) {
        int selected = JOptionPane.showOptionDialog(null, "Choose a sheet number", "Dailog window", 0, JOptionPane.QUESTION_MESSAGE, null, num, null);
        return selected;
    }

    public String getPathForExport() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel file(*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        String path = null;
        int ret = fileChooser.showSaveDialog(null);
        if (ret == JFileChooser.APPROVE_OPTION) {
            path = fileChooser.getSelectedFile().getAbsolutePath();
        } else if (ret == JFileChooser.CANCEL_OPTION) {
            return null;
        }
        return path;
    }
    
    public void exportMistake(){
        JOptionPane.showMessageDialog(null, "No data to export!", null, JOptionPane.ERROR_MESSAGE);
    }

}
