package Application.AppMain;

import Application.objects.PLCState;
import Application.objects.PlantLine;

public class App {

    public static void main(String[] args) {

        int amountOfPLC = 1100;
        int amountOfWorkers = 1100;

        PlantLine plantLine = new PlantLine(amountOfPLC);
        System.out.println("Total amount of PLC " + plantLine.getAmountOfPLCs());
//        System.out.println("Amount of PLC in state INITIAL MODE is : " + plantLine.getAmountOfPLCInStatus(PLCState.INITIAL_MODE));
        plantLine.initializePlant(amountOfWorkers);
//        System.out.println("Amount of PLC in state RUN MODE is : " + plantLine.getAmountOfPLCInStatus(PLCState.RUN_MODE));
//        System.out.println("Amount of PLC in state PROGRAM MODE is : " + plantLine.getAmountOfPLCInStatus(PLCState.PROGRAM_MODE));
//        System.out.println("Amount of PLC in state INITIAL MODE is : " + plantLine.getAmountOfPLCInStatus(PLCState.INITIAL_MODE));
    }

}
