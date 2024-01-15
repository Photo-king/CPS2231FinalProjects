package CPS2231.FinalProj;

import java.io.*;
import java.util.Scanner;

public class Client extends HaluoBike {
    public static void main(String[] args) throws IOException, NullPointerException {
        HaluoBike halo = new HaluoBike(); //Import the data to the Ram
        User user = new User();
        Bike bike = new Bike();
        halo.Reload();
        System.out.println("Data has been loaded!");
        while (true) {//Keep on working until the application was shut down.
            System.out.println();
            System.out.println("CreatNewAccount OR Login");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            if (input.equals("CreatNewAccount")) {
                System.out.println("Please input your account");
                String newName = sc.next();
                System.out.println("Please input your password");
                String newPassword = sc.next();
                user.CreatNewAccount(newName, newPassword);
                halo.Reload();//If the file has been modified, needed to be flushed.
            } else if (input.equals("Login")) {
                System.out.println("Please input your account");
                String account = sc.next();
                System.out.println("Please input your password");
                String key = sc.next();
                if ((key.equals("ljl20021112") && account.equals("linjinle123")) || (account.equals("lc123") && key.equals("lc123li"))) {//Admin entrance to background
                    System.out.println("Dear administrator, here are the bike's background data:");//Show the BikeData
                    try {
                        bike.Display();
                    } catch (NullPointerException e) {
                    }
                    System.out.println("Create OR Delete? If you want to quit, just enter anything.");
                    String admin = sc.next();
                    if (admin.equals("Create")) {
                        System.out.println("Please enter the ID of the bike");
                        admin = sc.next();
                        bike.CreatNewBike(admin);
                    } else if (admin.equals("Delete")) {
                        try {
                            System.out.println("Please enter the ID of the bike");
                            admin = sc.next();
                            bike.DeleteOldBike(admin);
                        } catch (NullPointerException e) {
                        }
                    } else {
                        System.out.println("Quiting");
                    }
                } else {
                    if (!user.Login(account, key)) {
                        System.out.println("Un-correct password OR Invalid account!");
                    } else {//Valid user
                        System.out.println("Borrow or Return ?");
                        String bOrR = sc.next();
                        if (bOrR.equals("Borrow")) {
                            System.out.println("Please enter the ID of the bike");
                            bOrR = sc.next();
                            try {
                                bike.Borrow(bOrR);
                            } catch (NullPointerException e) {
                            }
                        } else if (bOrR.equals("Return")) {
                            System.out.println("Please enter the ID of the bike");
                            bOrR = sc.next();
                            try {
                                bike.Return(bOrR);
                            } catch (NullPointerException e) {
                            }
                        } else {
                            System.out.println("Please input a valid command!");
                        }
                    }
                }
            } else {
                System.out.println("Please input a valid command!");
            }
        }
    }
}