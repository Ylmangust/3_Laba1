/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.b22901.lab1.Model;

import java.io.*;
import java.util.List;
import javax.swing.JOptionPane;
import mephi.b22901.lab1.Controller.Controller;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Регина
 */
public class ExportClass {

    private Controller controller;

    public ExportClass(Controller controller) {
        this.controller = controller;
    }

    public void exportData(String path, List<double[]> data) {
        if (!path.endsWith(".xlsx")) {
            path = path + ".xlsx";
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet1 = workbook.createSheet("List1");
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet1.createRow(i);
            for (int j = 0; j < data.get(0).length; j++) {
                Cell cell = row.createCell(j);
               // if (cell.getColumnIndex() != 0 & cell.getRowIndex() != 0) {
                    cell.setCellValue(data.get(i)[j]);
               // }
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File(path));
            workbook.write(out);
            JOptionPane.showMessageDialog(null, "File has been read successfully", "OK", JOptionPane.INFORMATION_MESSAGE);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
