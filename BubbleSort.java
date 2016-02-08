public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort  b = new BubbleSort ();
        int[] specimens = t.create();
        b.print(specimens);
        b.print(b.bubbleSort(specimens));
}

    public int[] create() {
        int[] j = new int[10];
        for (int i = 0; i < j.length; i++) {
            j[i] = (int) Math.round(Math.random() * 100);
        }
        return j;
    }

    public void print(int input[]) {
        for (int i : input) System.out.print(i + " ");
        System.out.println();
    }

    private void swap(int[] specimens, int index) {
        int temp = specimens[index - 1];
        specimens[index - 1] = specimens[index];
        specimens[index] = temp;
    }

    public int[] bubbleSort(int input[]) {
        for (int i = 1; i < input.length; i++) {
            for (int j = input.length - 1; j >= i; j--) {
                if (input[j - 1] > input[j]) swap(input, j);
            }
        }
        return input;
    }
}
