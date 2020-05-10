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
        for (int i = 1;i<amountOfPLCs+1;i++) {
            ProgrammableLogicController tempPLC = new ProgrammableLogicController(i);
            list.add(tempPLC);
        }
        return list;
    }

    public int getAmountOfPLCInStatus(PLCState stateOfPLC) {
        int amountOfPLCInState = 0;
        for(ProgrammableLogicController programmableLogicController : pLCList) {
            if (programmableLogicController.getState().equals(stateOfPLC)) {
                amountOfPLCInState++;
            }
        }
        return amountOfPLCInState;
    }

    public String getAmountOfPLCByState(int iteration) {
        String prefix = "Amount of iteration NO " + iteration + " PLC are: \n";
        String amountOfInitial = "initial state: " + getAmountOfPLCInStatus(PLCState.INITIAL_MODE) + "\n";
        String amountOfRun = "run state: " + getAmountOfPLCInStatus(PLCState.RUN_MODE) + "\n";
        String amountOfProgram = "program state: " + getAmountOfPLCInStatus(PLCState.PROGRAM_MODE) + "\n";
        return prefix + amountOfInitial + amountOfProgram + amountOfRun;
    }

    public int getAmountOfPLCs() {
        return amountOfPLCs;
    }

    public void initializePlant(int amountOfWorkers) {
        for(int i = 1; i<amountOfWorkers+1;i++) {
            for (ProgrammableLogicController programmableLogicController : pLCList) {
                if (meetRequirementsForStateChange(i, programmableLogicController.getpLCNumber())) {
                    programmableLogicController.changeState(getProperNewStatusName(i, programmableLogicController.getState()));
                }
            }
            if (i == 1) {
                System.out.println(getAmountOfPLCByState(i));
            } else if (i == 2) {
                System.out.println(getAmountOfPLCByState(i));
            } else if (i == 3) {
                System.out.println(getAmountOfPLCByState(i));
            } else if (i == 1100) {
                System.out.println(getAmountOfPLCByState(i));
            }
        }
    }

    private boolean meetRequirementsForStateChange(int workerNumber, int pLCNumber) {
        return (pLCNumber%workerNumber) == 0;
    }

    private String getProperNewStatusName(int workerNumber, PLCState plcState) {
        if (workerNumber == 1) {
            return "RUN";
        } else if (workerNumber == 2) {
            return "PROGRAM";
        } else if (workerNumber >= 3) {
            return logicForStatusChangeAfterSecondWorker(plcState);
        } else {
            throw new IllegalArgumentException("Worker number can not be less then 1, passed value is "
                    + workerNumber);
        }
    }

    private String logicForStatusChangeAfterSecondWorker(PLCState stateOfPLC) {
        if (stateOfPLC.equals(PLCState.PROGRAM_MODE)) {
            return "RUN";
        }else if (stateOfPLC.equals(PLCState.RUN_MODE)) {
            return "PROGRAM";
        }
        throw new IllegalArgumentException("Mode of PLC can not be other then PROGRAM or RUN"
                + " after the stage of third worker, passed value is "
                + stateOfPLC.toString());
    }
}
