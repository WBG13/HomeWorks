public class Boxes {
     public static void main(String [] argm) {
        Boxes b = new Boxes();
        int[] arr = b.createRanNum(new int[10]);
        b.print(arr);
        arr = sort(arr);
        b.print(arr);
    }

    public static int[] sort(int[] input) {
        int temp;
        for (int x = 1; x < input.length; x++) {
            for (int j = x; j > 0; j--) {
                if (input[j] < input[j - 1]) {
                    temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
            }
        }
        return input;
    }

    public void print(int[] massive) {
        for (int i : massive) {
            System.out.print(i != massive[massive.length - 1] ? i + ", " : i + ".");
        }
        System.out.println();
    }

    public int[] createRanNum(int[] massive) {
        for (int i = 0; i < massive.length; i++) {
            massive[i] = (int) Math.round(Math.random() * 100);
        }
        return massive;
    }
}
