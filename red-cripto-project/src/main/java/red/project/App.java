/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HexFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import red.project.algorithms.SHA256;
import red.project.controller.LoginController;
import red.project.controller.RegisterController;
import red.project.model.User;

/**
 *
 * @author Matheus
 */
public class App {
    
    public static void main(String[] args) {
        
        /*
        Path path = Paths.get(System.getProperty("user.dir"));
        path = path.resolve(path.toString() + "/users");
        System.out.println(path);
        if(!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            System.out.println("File does not exists");
        } else {
            System.out.print("Path exists: " + path);
        }
        */

        new LoginController();
//        new RegisterController();
        /*
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

           */ 
            /*
            byte[] myBytes;
            myBytes = usuario.getBytes();
            System.out.println(HexFormat.of().formatHex(myBytes));
            */
        
        
    }
    
}
