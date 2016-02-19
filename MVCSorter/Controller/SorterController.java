import ssr.MVCSorter.Model.ModelFunctions;
import ssr.MVCSorter.View.SorterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SorterController {
    public int[] getMassive() {
        return massive;
    }

    public void setMassive(int[] massive) {
        this.massive = massive;
    }

    private int[] massive = new int[10];
    public ModelFunctions sorter;
    public SorterView gui;

    public SorterController(ModelFunctions sorter, SorterView gui) {
        this.gui = gui;
        this.sorter = sorter;
        this.gui.addSorterListener(new SorterListener());
    }

    class SorterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            executeCommand(e.getActionCommand());
        }
    }

    public void executeCommand(String cmd) {
        switch (cmd) {
            case ("Create random numbers"):
                setMassive(sorter.createRanNum(massive));
                gui.sText(getMassive());
                break;
            case ("Insertion sorting"):
                setMassive(sorter.iSortData(massive));
                gui.sText(getMassive());
                break;
            case ("Bubble sorting"):
                setMassive(sorter.bSortData(massive));
                gui.sText(getMassive());
                break;
            case ("Quick sorting"):
                setMassive(sorter.quickSort(0, massive.length - 1, massive));
                gui.sText(getMassive());
                break;
        }
    }
}
