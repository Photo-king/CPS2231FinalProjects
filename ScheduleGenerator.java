package CPS1231;

import java.util.Scanner;

//Background: Sometimes, we have problems to make our own timetable quickily and effectively
//So we need a new method to generate our timetable quickily and beautifully
//Purpose: Input the class time and output the class timetable back to the user.
public class ScheduleGenerator {
    public static void main(String[] args) {
        System.out.println("Enter your course and timetable following the from: \"ABCD\"|(1,2)|Tues\"|\"also between classes");
        System.out.println("While (1,2) means the time is from One O'clock to Two O'clock");
        System.out.println("To enter some courses and some different times, please enter the data according to this form below");
        System.out.println("Such as: ENG|(8.30,11.15)|Mon,Wed|CPS|(13,14.45)|Tues,Fri|MATH|(11.30,13.15)|Wed,Fri|COMM|(15.30,16.30)|Thur|CNCC|(17.40,18)|Fri");
        System.out.println("The limits of the classes is 5!(Because the limit of the 4years' planning is 5)");
        //Let the user to enter the course following this form:"ABCD"|(8.30,11.15)|
        //(8.30,11.15)means the time is from half of 8 O'clock to a quarter past 11 O'clock
        Scanner input = new Scanner(System.in);
        String timeTable = input.nextLine() + "| ";
        //Marked out the "|" point, and use this point to devide out those pierods.
        int breakPosition[] = new int[timeTable.length()];
        for (int i = 0; i < timeTable.length() - 1; i++) {
            while (timeTable.substring(i, i + 1).equals("|")) {
                breakPosition[i]++;
                break;
            }
        }
        /*---------------------------------------------------------------------------------------------------------------------------*/
        //Store class times, dates, and names in the array[5](Input 5 courses) of className/classTime/classDate
        int c = 0;//c is a location mark has no more means
        String[] className = new String[5];
        String[] classTime = new String[5];
        String[] classDate = new String[5];
        for (int i = 0; i < className.length; i++) {
            className[i] = storeClassName(timeTable, breakPosition, c);
            classTime[i] = storeClassTime(timeTable, breakPosition, c);
            classDate[i] = storeClassDate(timeTable, breakPosition, c);
            c++;
        }
        //Make five days' time into the form of array
        // ENG|(2.30,3.40)|Mon,Fri|CPS|(3,4)|Tues,Thur
        int[] Mon = new int[1440];
        int[] Tues = new int[1440];
        int[] Wed = new int[1440];
        int[] Thur = new int[1440];
        int[] Fri = new int[1440];
        c = 0;
        String startTime = "";//Large enough for time spaces
        String stopTime = "";
        if (classDate[4] != null && classDate[4] != "") {
            for (int j = 0; j < 5; j++) {
                //These bench of code I failed to use the method to simplify them.
                if (classDate[j].indexOf("Mon") != -1) {
                    startTime = classTime[j].substring(classTime[j].indexOf("(") + 1, classTime[j].indexOf(","));
                    stopTime = classTime[j].substring(classTime[j].indexOf(",") + 1, classTime[j].indexOf(")"));
                    int start = (int) (((int) Float.parseFloat(startTime) * 60) + (Float.parseFloat(startTime) - (int) Float.parseFloat(startTime)) * 100);
                    int stop = (int) (((int) Float.parseFloat(stopTime) * 60) + (Float.parseFloat(stopTime) - (int) Float.parseFloat(stopTime)) * 100);
                    for (int k = start; k < stop; k++) {
                        Mon[k]++;
                    }
                }
                if (classDate[j].indexOf("Tues") != -1) {
                    startTime = classTime[j].substring(classTime[j].indexOf("(") + 1, classTime[j].indexOf(","));
                    stopTime = classTime[j].substring(classTime[j].indexOf(",") + 1, classTime[j].indexOf(")"));
                    int start = (int) (((int) Float.parseFloat(startTime) * 60) + (Float.parseFloat(startTime) - (int) Float.parseFloat(startTime)) * 100);
                    int stop = (int) (((int) Float.parseFloat(stopTime) * 60) + (Float.parseFloat(stopTime) - (int) Float.parseFloat(stopTime)) * 100);
                    for (int k = start; k < stop; k++) {
                        Tues[k]++;
                    }
                }
                if (classDate[j].indexOf("Wed") != -1) {
                    startTime = classTime[j].substring(classTime[j].indexOf("(") + 1, classTime[j].indexOf(","));
                    stopTime = classTime[j].substring(classTime[j].indexOf(",") + 1, classTime[j].indexOf(")"));
                    int start = (int) (((int) Float.parseFloat(startTime) * 60) + (Float.parseFloat(startTime) - (int) Float.parseFloat(startTime)) * 100);
                    int stop = (int) (((int) Float.parseFloat(stopTime) * 60) + (Float.parseFloat(stopTime) - (int) Float.parseFloat(stopTime)) * 100);
                    for (int k = start; k < stop; k++) {
                        Wed[k]++;
                    }
                }
                if (classDate[j].indexOf("Thur") != -1) {
                    startTime = classTime[j].substring(classTime[j].indexOf("(") + 1, classTime[j].indexOf(","));
                    stopTime = classTime[j].substring(classTime[j].indexOf(",") + 1, classTime[j].indexOf(")"));
                    int start = (int) (((int) Float.parseFloat(startTime) * 60) + (Float.parseFloat(startTime) - (int) Float.parseFloat(startTime)) * 100);
                    int stop = (int) (((int) Float.parseFloat(stopTime) * 60) + (Float.parseFloat(stopTime) - (int) Float.parseFloat(stopTime)) * 100);
                    for (int k = start; k < stop; k++) {
                        Thur[k]++;
                    }
                }
                if (classDate[j].indexOf("Fri") != -1) {
                    startTime = classTime[j].substring(classTime[j].indexOf("(") + 1, classTime[j].indexOf(","));
                    stopTime = classTime[j].substring(classTime[j].indexOf(",") + 1, classTime[j].indexOf(")"));
                    int start = (int) (((int) Float.parseFloat(startTime) * 60) + (Float.parseFloat(startTime) - (int) Float.parseFloat(startTime)) * 100);
                    int stop = (int) (((int) Float.parseFloat(stopTime) * 60) + (Float.parseFloat(stopTime) - (int) Float.parseFloat(stopTime)) * 100);
                    for (int k = start; k < stop; k++) {
                        Fri[k]++;
                    }
                }
            }
            graphOutput(Mon, Tues, Wed, Thur, Fri, className, classDate);
        } else {
            System.out.println("Invalid Input!");
        }
    }

    //Method to get the class name
    public static String storeClassName(String timeTable, int[] breakPosition, int c) {
        String className[] = new String[5];
        int markBreak = 2, markBefore = 0, j = 0, k = 0;
        for (int i = 0; i < timeTable.length(); i++) {
            if (breakPosition[i] == 1) {
                markBreak++;
                if (markBreak == 2) {
                    markBefore = i;
                }
            }
            if (markBreak == 3 && j == 0) {
                className[k] = timeTable.substring(0, i);
                markBreak = 0;
                j++;
                k++;
            }
            if (markBreak == 3 && j != 0) {
                className[k] = timeTable.substring(markBefore + 1, i);
                markBreak = 0;
                k++;
            }
        }
        return className[c];
    }

    //Method to get the class time(String)
    public static String storeClassTime(String timeTable, int[] breakPosition, int c) {
        String classTime[] = new String[5];
        int markBreak = 1, markBefore = 0, k = 0;
        for (int i = 0; i < timeTable.length(); i++) {
            if (breakPosition[i] == 1) {
                markBreak++;
                if (markBreak == 2) {
                    markBefore = i;
                }
            }
            if (markBreak == 3) {
                classTime[k] = timeTable.substring(markBefore + 1, i);
                markBreak = 0;
                k++;
            }
        }
        return classTime[c];
    }

    //Method to get the class Date
    public static String storeClassDate(String timeTable, int[] breakPosition, int c) {
        String classDate[] = new String[5];
        int markBreak = 0, markBefore = 0, k = 0;
        for (int i = 0; i < timeTable.length(); i++) {
            if (breakPosition[i] == 1) {
                markBreak++;
                if (markBreak == 2) {
                    markBefore = i;
                }
            }
            if (markBreak == 3) {
                classDate[k] = timeTable.substring(markBefore + 1, i);
                markBreak = 0;
                k++;
            }
        }
        return classDate[c];
    }

    //Method breakLine means that this method will output the "-" to distinguish different times
    public static void breakLine() {
        for (int i = 0; i < 183; i++) {
            System.out.print("-");
        }
        System.out.println("");
        System.out.print("--");
        for (int i = 8; i < 12; i++) {
            System.out.print(i + ":00AM");
            for (int k = 0; k < 11; k++) {
                System.out.print("-");
            }
        }
        for (int i = 12; i < 18; i++) {
            System.out.print(i + ":00PM");
            for (int k = 0; k < 11; k++) {
                System.out.print("-");
            }
        }
        System.out.println("18:00PM");
    }

    //This method is to mark out whether there is some classes in the timetable or not
    public static String mark(String classDate[], String className[], int j) {
        String temp = "";
        String a;
        if (j == 0)
            a = "Mon";
        else if (j == 1)
            a = "Tues";
        else if (j == 2)
            a = "Wed";
        else if (j == 3)
            a = "Thur";
        else
            a = "Fri";
        System.out.print("   ");
        for (int i = 0; i < 5; i++) {
            while (classDate[i].indexOf(a) != -1) {
                System.out.print(className[i] + " ");
                temp += (className[i] + " ");
                break;
            }
        }
        return temp;
    }

    //Method to output the result back to the user
    public static void graphOutput(int Mon[], int Tues[], int Wed[], int Thur[], int Fri[], String className[], String classDate[]) {
        System.out.println("Considering those classes are start later than 8AM and finish before 6PM, we can graph that:");
        breakLine();
        int j = 0;
        int[] MonX = new int[180], TuesX = new int[180], WedX = new int[180], ThurX = new int[180], FriX = new int[180];
        for (int i = 0; i < 180; i++) {
            MonX[i] = Mon[480 + (int) (3.333333333333 * i)];
        }
        for (int i = 0; i < 180; i++) {
            TuesX[i] = Tues[480 + (int) (3.333333333333 * i)];
        }
        for (int i = 0; i < 180; i++) {
            WedX[i] = Wed[480 + (int) (3.333333333333 * i)];
        }
        for (int i = 0; i < 180; i++) {
            ThurX[i] = Thur[480 + (int) (3.333333333333 * i)];
        }
        for (int i = 0; i < 180; i++) {
            FriX[i] = Fri[480 + (int) (3.333333333333 * i)];
        }
        //Check whether the time has the mark and then out put the visible data back to the user equal to the mark
        for (int i = mark(classDate, className, j).length(); i < 180; i++) {
            if (MonX[i] == 1) {
                System.out.print("|");
            } else {
                System.out.print("-");
            }
        }
        System.out.println();
        j++;
        for (int i = mark(classDate, className, j).length(); i < 180; i++) {
            if (TuesX[i] == 1) {
                System.out.print("|");
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
        j++;
        for (int i = mark(classDate, className, j).length(); i < 180; i++) {
            if (WedX[i] == 1) {
                System.out.print("|");
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
        j++;
        for (int i = mark(classDate, className, j).length(); i < 180; i++) {
            if (ThurX[i] == 1) {
                System.out.print("|");
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
        j++;
        for (int i = mark(classDate, className, j).length(); i < 180; i++) {
            if (FriX[i] == 1) {
                System.out.print("|");
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
        for (int i = 0; i < 183; i++) {
            System.out.print("-");
        }
    }
}