/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.b22901.lab1.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import mephi.b22901.lab1.Controller.Controller;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author Регина
 */
public class ImportClass {

    private List<List<Double>> data;
    private Controller controller;

    public ImportClass(Controller controller) {
        this.controller = controller;
    }

    public void ChecknRead(String path) {
        if (isExcel(path)) {
            data = readFile(path);
            int num = 1;
            JOptionPane.showMessageDialog(null, "File has been read successfully", "OK", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "File format can be only xlsx!", "OK", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean isExcel(String name) {
        boolean result = false;
        //  int dotIndex = name.lastIndexOf('.');
        //String extension = (dotIndex == -1) ? "" : name.substring(dotIndex);
        // if (extension.equals(".xlsx")) {
        if (name.endsWith(".xlsx")) {
            result = true;
        }
        return result;
    }

    private List<List<Double>> readFile(String path) {
        List<List<Double>> writtenFile = new ArrayList<>();
        try {
            FileInputStream in = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(in);

            int sheets = workbook.getNumberOfSheets();
            Integer[] sheetsAvaliable = new Integer[sheets];
            for (int i = 0; i < sheets; i++) {
                sheetsAvaliable[i] = i + 1;
            }
            int answer = controller.getSheet(sheetsAvaliable);
            Sheet sheet = workbook.getSheetAt(answer);

            int rows = sheet.getRow(0).getLastCellNum();
            for (Row row : sheet) {
                List<Double> tempData = new ArrayList<>();
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.FORMULA) {
                        FormulaEvaluator formula = workbook.getCreationHelper().createFormulaEvaluator();
                        CellValue cellVal = formula.evaluate(cell);
                        if (cellVal.getCellType() == CellType.NUMERIC) {
                            tempData.add(cellVal.getNumberValue());
                        }
                    }
                    if (cell.getCellType() == CellType.NUMERIC) {
                        tempData.add(cell.getNumericCellValue());
                    }
                }
                if (tempData.size() == rows) {
                    writtenFile.add(tempData);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return writtenFile;
    }

    public List<List<Double>> getData() {
        return data;
    }
}
