public class Checker {
    public int rounding(double number){
        int wholeNumber = (int) number;
        return number - wholeNumber >= 0.5 ? 1 : 0;
    }
}
