package Application.objects;

public class ProgrammableLogicController {

    private int pLCNumber;
    private PLCState state;

    public ProgrammableLogicController(int pLCNumber) {
        this.pLCNumber = pLCNumber;
        this.state = PLCState.INITIAL_MODE;
    }

    public int getpLCNumber() {
        return pLCNumber;
    }

    public PLCState getState() {
        return state;
    }

    public void changeState(String stateName) {
        switch (stateName) {
            case "RUN":
                this.state = PLCState.RUN_MODE;
                break;
            case "PROGRAM":
                this.state =PLCState.PROGRAM_MODE;
                break;
            default:
                break;
        }

    }
}
