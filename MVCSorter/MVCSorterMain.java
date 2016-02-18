import ssr.MVCSorter.Controller.SorterController;
import ssr.MVCSorter.Model.ModelFunctions;
import ssr.MVCSorter.View.SorterView;

public class MVCSorterMain {
    public static void main(String[] args) {
        ModelFunctions st = new ModelFunctions();
        SorterView gui = new SorterView();
        SorterController sc = new SorterController(st, gui);
    }
}
