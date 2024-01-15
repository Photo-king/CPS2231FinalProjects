package CPS2231.FinalProj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bike extends HaluoBike {
    //Bike will:whether the storage is rented, number
    public Bike() {

    }

    public void CreatNewBike(String newID) throws IOException {
        File bike = new File("src/CPS2231/FinalProj/BikeData.txt");
        //If the newName has conflict with the exist one.
        Scanner file = new Scanner(bike);
        boolean whetherSame = false;
        while (file.hasNext()) {
            if (file.next().equals(newID)) {
                whetherSame = true;
                System.out.println("The ID has already been registered!");
            }
        }
        if (bike.exists() && bike.canWrite() && whetherSame == false) {
            FileWriter pw = new FileWriter(bike, true);
            pw.write("\n");
            pw.write(newID);
            pw.write("\n");
            pw.write("Not rent");
            System.out.println("Successfully registered this new number: " + newID + " bike!");
            pw.close();
            Reload();
        }
    }

    public void DeleteOldBike(String ID) throws IOException, NullPointerException {
        for (int i = 1; i <= 999; i++) {
            try {
                if (bikes[i].getBikeID().equals(ID)) {//Found that bike
                    System.out.println("Successfully deleted!");
                    bikes[i].setBikeRent("");
                    bikes[i].setBikeID("");
                    Rewrite();
                    Reload();
                    break;
                }
            } catch (NullPointerException exception) {
                System.out.println("Please enter a valid bike's ID!");
                break;
            }
        }
    }

    public void Borrow(String bOrR) throws IOException {
        for (int i = 1; i <= 999; i++) {
            try {
                if (bikes[i].getBikeID().equals(bOrR)) {//Found that bike
                    if (bikes[i].getBikeRent().equals("Not rent")) {
                        System.out.println("Successfully rented!");
                        bikes[i].setBikeRent("Rented");
                        Rewrite();
                        Reload();
                        break;
                    } else {
                        System.out.println("This bike has already been rented!");
                        break;
                    }
                }
            } catch (NullPointerException ex) {
                System.out.println("Please enter a valid bike ID!");
                System.out.println("You can try another valid ID to borrow.");
                break;
            }
        }
    }

    public void Return(String bOrR) throws IOException, NullPointerException {
        for (int i = 1; i <= 999; i++) {
            try {
                if (bikes[i].getBikeID().equals(bOrR)) {//Found that bike
                    if (bikes[i].getBikeRent().equals("Rented")) {
                        System.out.println("Successfully return!");
                        bikes[i].setBikeRent("Not rent");
                        Rewrite();
                        Reload();
                    } else {
                        System.out.println("This bike hasn't been rented!");
                    }
                    break;
                }
            } catch (NullPointerException x) {
                System.out.println("Please enter a valid bike ID!");
                System.out.println("You can try another valid ID to return.");
                break;
            }
        }
    }

    public void Display() throws NullPointerException {
        for (int i = 1; i < 1000; i++) {
            System.out.println("Bike number: " + bikes[i].getBikeID() + ", condition: " + bikes[i].getBikeRent());
        }
        System.out.println("Done!");
    }
}