/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.b22901.lab1.Controller;

import mephi.b22901.lab1.Model.ExportClass;
import mephi.b22901.lab1.View.GUI;
import mephi.b22901.lab1.Model.ImportClass;
import mephi.b22901.lab1.Model.ProcessClass;

/**
 *
 * @author ??????
 */
public class Controller {

    private ImportClass importedData;
    private ProcessClass process;
    private ExportClass exportInfo;
    private GUI gui;

    public Controller() {
        gui = new GUI(this);
    }

    public void read(String path) {
        importedData = new ImportClass(this);
        importedData.ChecknRead(path);
    }

    public void process() {
        if (importedData.getData()!=null) {
            process = new ProcessClass(importedData.getData());
        }
    }

    public int getSheet(Integer[] num) {
        return gui.giveAnswer(num);
    }

    public void export(String path) {
        exportInfo = new ExportClass(this);
        if (!process.returnData().isEmpty()) {
            exportInfo.exportData(path, process.returnData(), process.returnConfInterval());
        }
    }

}
