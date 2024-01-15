package CPS2231.FinalProj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User extends HaluoBike {
    public User() {
    }

    public void CreatNewAccount(String newName, String newPassword) throws IOException {
        File account = new File("src/CPS2231/FinalProj/UserData.txt");
        //If the newName has conflict with the exist one.
        Scanner file = new Scanner(account);
        boolean whetherSame = false;
        while (file.hasNext()) {
            if (file.next().equals(newName)) {
                whetherSame = true;
                System.out.println("The name has already been registered!");
            }
        }
        if (users[999] != null) {
            System.out.println("Database is full, can't register, please contact with administrator!");
        } else if (account.exists() && account.canWrite() && whetherSame == false) {
            FileWriter pw = new FileWriter(account, true);
            pw.write("\n");
            pw.write(newName);
            pw.write("\n");
            pw.write(newPassword);
            System.out.println("Successfully registered!");
            System.out.println("Last modified on " + new java.util.Date(account.lastModified()));
            pw.close();
        }
    }

    public boolean Login(String account, String key) {
        boolean check = false;
        try {
        for (int i = 1; i <= 1000; i++) {
            if (users[i].getName().equals(account)) {
                if (users[i].getPassword().equals(key)) {
                    check = true;
                } else {
                    check = false;
                }
                break;
            } else {
                check = false;
            }
        }
        }catch (NullPointerException ex){
            check =false;
        }
        return check;
    }
}