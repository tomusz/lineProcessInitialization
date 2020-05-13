package Application.AppMain;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        int amountOfPLC = 1100;
        int amountOfWorkers = 1100;
        List<String> listOfProgrammableLogicControllerStatuses = new ArrayList<>();
        for (int i=1;i<=amountOfWorkers;i++) {
            for (int j=1;j<=amountOfPLC;j++) {
                if (j%i==0) {
                    if (i == 1) {
                        listOfProgrammableLogicControllerStatuses.add("RUN");
                    } else {
                        listOfProgrammableLogicControllerStatuses.set(j-1,
                                togglePLCStatus(listOfProgrammableLogicControllerStatuses.get(j-1)));
                    }
                }
            }
        }
        printAmountOfPLCInStatusRun(listOfProgrammableLogicControllerStatuses);
    }
    private static String togglePLCStatus(String currentStatus) {
        if (currentStatus.equals("RUN")){
            return "PROGRAM";
        } else {
            return "RUN";
        }
    }
    private static void printAmountOfPLCInStatusRun(List<String> PLCList) {
        int counter = 0;
        for (String plc : PLCList) {
            if (plc.equals("RUN")) {
                counter++;
            }
        }
        System.out.println("Amount of Programmable Logic Controller in status RUN is " + counter);
    }

}
