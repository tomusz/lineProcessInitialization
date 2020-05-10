package Application.AppMain;

import Application.objects.PlantLine;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        int amountOfPLC = 1100;
        int amountOfWorkers = 1100;

        PlantLine plantLine = new PlantLine(amountOfPLC);
        System.out.println("Total amount of Programmable Logic Controllers " + plantLine.getTotalAmountOfPLCs());
        plantLine.getAmountOfPLCByState();
        plantLine.initializePlant(amountOfWorkers);

//      Initialization with validation
        List<Integer> iterationNumbersForValidation = new ArrayList<>();
        iterationNumbersForValidation.add(1);
        iterationNumbersForValidation.add(2);
        iterationNumbersForValidation.add(3);
        iterationNumbersForValidation.add(1100);
        plantLine.initializePlantAndPrintStatesForIterationsInList(amountOfWorkers,iterationNumbersForValidation);
    }

}
