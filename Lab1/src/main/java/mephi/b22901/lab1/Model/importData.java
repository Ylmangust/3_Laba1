/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.b22901.lab1.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author Регина
 */
public class importData {

    public importData() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel file(*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        String name = "";
        String path = "";
        int ret = fileChooser.showDialog(null, "Open file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            name = fileChooser.getSelectedFile().getName();
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
        if (isExcel(name)) {
            List<List<Double>> data = readFile(path);
                   int num = 1;
            for (List<Double> row : data){
                System.out.println(num); 
                System.out.println(row); 
                num ++;
                }
        } else {
            JOptionPane.showMessageDialog(null, "File format can be only xlsx!", "OK", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isExcel(String name) {
        boolean result = false;
        int dotIndex = name.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : name.substring(dotIndex);
        if (extension.equals(".xlsx")) {
            result = true;
        }
        return result;
    }

    private List<List<Double>> readFile(String path) {
        List<List<Double>> writtenFile = new ArrayList<>();
        try {
            FileInputStream in = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getRow(0).getLastCellNum();
            for (Row row : sheet) {
                List<Double> tempData = new ArrayList<>();
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        tempData.add(cell.getNumericCellValue());
                    }
                }
                if (tempData.size() == rows) {
                    writtenFile.add(tempData);
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException ex) {

        }
        return writtenFile;
    }
}
