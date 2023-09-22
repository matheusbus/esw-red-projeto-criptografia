/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.controller;

import red.project.view.BaseForm;

/**
 *
 * @author matheus
 */
public abstract class BaseController {

    public abstract void initButtons();

    public abstract void showWindow();

    public abstract void showMessage(String message, String title);

    public abstract void closeWindow();

    
    
}
