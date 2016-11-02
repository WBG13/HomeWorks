public class Coordinates {

    private int[] interpolatedCoordinates;

    public Coordinates(final int size) {

        interpolatedCoordinates = new int[size];
    }

    synchronized void addCoordinates(int inputCoordinates, int position) {
        interpolatedCoordinates[position] = inputCoordinates;
    }

    public int[] getCoordinates() {
        return this.interpolatedCoordinates;
    }

}
