/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project;

import java.io.UnsupportedEncodingException;
import java.util.HexFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import red.project.algorithms.SHA256;
import red.project.controller.LoginController;
import red.project.model.User;

/**
 *
 * @author Matheus
 */
public class App {
    
    public static void main(String[] args) {
        
        //new LoginController();
        
        // Nome de usuário gerando OK
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome de usuário: ");
        String username = sc.nextLine();
        try {
            User user = new User(username, "null");
            System.out.println(user.toString());
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

            
            /*
            byte[] myBytes;
            myBytes = usuario.getBytes();
            System.out.println(HexFormat.of().formatHex(myBytes));
            */
        
        
    }
    
}
