public class Counter {
    private int number;

    Counter(final int number) {
        this.number = number;
    }

    public synchronized void decreaseNumber() {


        this.number -= 1;
    }

    public int getNumber() {
        return number;
    }
}
