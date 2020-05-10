package Application.objects;

import java.util.ArrayList;
import java.util.List;

public class PlantLine {

    private List<ProgrammableLogicController> pLCList;
    private int amountOfPLCs;

    public PlantLine(int amountOfPLCs) {
        this.amountOfPLCs = amountOfPLCs;
        this.pLCList = setPLCInDefaultState(amountOfPLCs);
    }

    private List<ProgrammableLogicController> setPLCInDefaultState(int amountOfPLCs) {
        List<ProgrammableLogicController> list = new ArrayList<>();
        for (int i = 1; i < amountOfPLCs + 1; i++) {
            ProgrammableLogicController tempPLC = new ProgrammableLogicController(i);
            list.add(tempPLC);
        }
        return list;
    }

    public int getAmountOfPLCInStatus(PLCState stateOfPLC) {
        int amountOfPLCInState = 0;
        for (ProgrammableLogicController programmableLogicController : pLCList) {
            if (programmableLogicController.getState().equals(stateOfPLC)) {
                amountOfPLCInState++;
            }
        }
        return amountOfPLCInState;
    }

    private String getAmountOfPLCByState(int iteration) {
        String prefix = "Amount of Programmable Logic Controllers iteration NO " + iteration + " PLC are: \n";
        String amountOfInitial = "initial state: " + getAmountOfPLCInStatus(PLCState.INITIAL_MODE) + "\n";
        String amountOfRun = "run state: " + getAmountOfPLCInStatus(PLCState.RUN_MODE) + "\n";
        String amountOfProgram = "program state: " + getAmountOfPLCInStatus(PLCState.PROGRAM_MODE) + "\n";
        return prefix + amountOfInitial + amountOfProgram + amountOfRun;
    }

    public void getAmountOfPLCByState() {
        String prefix = "Amount of Programmable Logic Controllers are: \n";
        String amountOfInitial = "initial state: " + getAmountOfPLCInStatus(PLCState.INITIAL_MODE) + "\n";
        String amountOfRun = "run state: " + getAmountOfPLCInStatus(PLCState.RUN_MODE) + "\n";
        String amountOfProgram = "program state: " + getAmountOfPLCInStatus(PLCState.PROGRAM_MODE) + "\n";
        System.out.println((prefix + amountOfInitial + amountOfProgram + amountOfRun));
    }

    public int getTotalAmountOfPLCs() {
        return amountOfPLCs;
    }

    /**
     * @param amountOfWorkers - amount of workers for performing initialization
     *
     *                        perform basic initialization of Programmable Logic Controllers
     */
    public void initializePlant(int amountOfWorkers) {
        for (int i = 1; i < amountOfWorkers + 1; i++) {
            iterateAllPLCAndChangeStateIfMeetRequirements(i);
        }
    }

    /**
     * @param amountOfWorkers - amount of workers for performing initialization
     * @param iterationsList - iteration that would be listed during the process of
     *                       Programmable Logic Controllers initialization
     *
     *   Additionally to base initialization function prints all Programmable Logic Controllers
     *   states in console result for each iteration number in list
     */
    public void initializePlantAndPrintStatesForIterationsInList(int amountOfWorkers,
                                                                 List<Integer> iterationsList) {
        for (int i = 1; i < amountOfWorkers + 1; i++) {
            iterateAllPLCAndChangeStateIfMeetRequirements(i);
            printStatesOfPLCsForEachIterationInList(i, iterationsList);
        }
    }

    private void iterateAllPLCAndChangeStateIfMeetRequirements(int iterationNO) {
        for (ProgrammableLogicController programmableLogicController : pLCList) {
            if (meetRequirementsForStateChange(iterationNO, programmableLogicController.getpLCNumber())) {
                programmableLogicController.changeState(getNewStatusName(iterationNO, programmableLogicController.getState()));
            }
        }
    }

    private void printStatesOfPLCsForEachIterationInList(Integer iterationNO,
                                                         List<Integer> iterationsList) {
        for (Integer iterationFromList : iterationsList) {
            if (iterationNO.equals(iterationFromList)) {
                System.out.println(getAmountOfPLCByState(iterationNO));
            }
        }
    }

    private boolean meetRequirementsForStateChange(int workerNumber, int pLCNumber) {
        return (pLCNumber % workerNumber) == 0;
    }

    private String getNewStatusName(int workerNumber, PLCState plcState) {
        if (workerNumber == 1) {
            return "RUN";
        } else if (workerNumber == 2) {
            return "PROGRAM";
        } else if (workerNumber >= 3) {
            return getStatusAfterSecondWorker(plcState);
        } else {
            throw new IllegalArgumentException("Worker number can not be less then 1, passed value is "
                    + workerNumber);
        }
    }

    /**
     * @param stateOfPLC - enum value before status change
     * @return - returns value of new Programmable Logic Controller state
     */
    private String getStatusAfterSecondWorker(PLCState stateOfPLC) {
        if (stateOfPLC.equals(PLCState.PROGRAM_MODE)) {
            return "RUN";
        } else if (stateOfPLC.equals(PLCState.RUN_MODE)) {
            return "PROGRAM";
        }
        throw new IllegalArgumentException("Mode of PLC can not be other then PROGRAM or RUN"
                + " after the stage of third worker, passed value is "
                + stateOfPLC.toString());
    }
}
