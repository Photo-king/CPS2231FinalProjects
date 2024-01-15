package CPS2231.FinalProj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HaluoBike {
    private String name;
    private String password;
    private String bikeID;
    private String bikeRent;
    private int group;
    //user  ID and Password
    public static User[] users = new User[1000];
    public static Bike[] bikes = new Bike[1000];

    public HaluoBike() {

    }

    public void Reload() throws FileNotFoundException {
        //Get data from the txt file
        System.out.println("Reloading Data...");
        try {//Create a new user
            int line = 0;
            File account = new File("src/CPS2231/FinalProj/UserData.txt");
            Scanner file = new Scanner(account);
            Scanner inputData = new Scanner(account);
            while (file.hasNext()) {
                file.next();
                line++;
            }
            setGroup(line / 2);
            line /= 2;
            for (int i = 1; i <= line; i++) {
                users[i] = new User();
                users[i].setName(inputData.nextLine());
                users[i].setPassword(inputData.nextLine());
            }
            file.close();
            inputData.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound!");
            throw new RuntimeException(e);
        }
        try {//Create a new bike
            int line = 0;
            File bike = new File("src/CPS2231/FinalProj/BikeData.txt");
            Scanner file = new Scanner(bike);
            Scanner inputData = new Scanner(bike);
            while (file.hasNext()) {
                file.next();
                line++;
            }
            setGroup(line / 2);
            line /= 2;
            for (int i = 1; i < 1000; i++) {
                bikes[i] = new Bike();
                bikes[i].setBikeID(inputData.nextLine());
                bikes[i].setBikeRent(inputData.nextLine());
            }
            file.close();
            inputData.close();
        } catch (RuntimeException e) {
        }
    }

    public void Rewrite() throws IOException, NullPointerException {
        File bike = new File("src/CPS2231/FinalProj/BikeData.txt");
        int temp = 0;
        if (bike.exists() && bike.canWrite()) {
            FileWriter print = new FileWriter(bike, false);//Totally rewrite the BikeData.txt file
            try {
                for (int i = 1; i < 1000; i++) {//Start rewriting until the NullPointerException to keep the file clean and logical.
                    if (i == 1) {
                        //Judge whether is deleted or...
                        if (bikes[i].getBikeID().equals("")) {
                            temp = 1;
                            continue;
                        }
                        print.write(bikes[i].getBikeID());
                        print.write("\n");
                        print.write(bikes[i].getBikeRent());
                        //Successfully run at here
                    } else {
                        if (bikes[i].getBikeID().equals("")) {
                            continue;
                        } else if (temp == 1) {
                            print.write(bikes[i].getBikeID());
                            print.write("\n");
                            print.write(bikes[i].getBikeRent());
                            temp = 0;
                        } else {
                            print.write("\n");
                            print.write(bikes[i].getBikeID());
                            print.write("\n");
                            print.write(bikes[i].getBikeRent());
                        }
                    }
                }
            } catch (RuntimeException e) {
                print.close();
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBikeID() {
        return bikeID;
    }

    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    public String getBikeRent() {
        return bikeRent;
    }

    public void setBikeRent(String bikeRent) {
        this.bikeRent = bikeRent;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}