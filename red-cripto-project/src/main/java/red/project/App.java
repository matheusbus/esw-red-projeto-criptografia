/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import red.project.algorithms.SHA256;
import red.project.controller.LoginController;

/**
 *
 * @author Matheus
 */
public class App {
    
    public static void main(String[] args) {
        
        //new LoginController();
        
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome de usuário: ");
        String usuario = sc.nextLine();
        
        // Nome de usuário gerando OK
        try {
            System.out.println(SHA256.getHashValue(usuario));
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
