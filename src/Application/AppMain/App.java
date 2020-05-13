package Application.AppMain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<String> listOfProgrammableLogicControllerStatuses = new ArrayList<>(
                Collections.nCopies(1100, "RUN"));
        for (int workerNumber = 2; workerNumber <= 1100; workerNumber++) {
            for (int pLCNumber = 1; pLCNumber <= listOfProgrammableLogicControllerStatuses.size(); pLCNumber++) {
                if (pLCNumber % workerNumber == 0) {
                    listOfProgrammableLogicControllerStatuses.set(pLCNumber - 1,
                            togglePLCStatus(listOfProgrammableLogicControllerStatuses.get(pLCNumber - 1)));
                }
            }
        }
        printAmountOfPLCInStatusRun(listOfProgrammableLogicControllerStatuses);
    }

    private static String togglePLCStatus(String currentStatus) {
        if (currentStatus.equals("RUN")) {
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
