public class BubbleSort {
    public static void main(String[] args) {
        int[] specimens = new int[10];
        for (int i = 0; i < specimens.length; i++) {
            specimens[i] = (int) Math.round(Math.random() * 100);
            System.out.println(specimens[i] + "	");
        }
        Sort1 sort = new Sort1(specimens);
        specimens = sort.bubbleSort();
        System.out.println();
        for (int i = 0; i < specimens.length; i++)
            System.out.print(specimens[i] + " ");
    }

    static class Sort1 {
        private int[] specimens;

        public Sort1(int[] specimens) {
            this.specimens = specimens;
        }

        private void swap(int[] specimens, int index) {
            int temp = specimens[index - 1];
            specimens[index - 1] = specimens[index];
            specimens[index] = temp;
        }

        public int[] bubbleSort() {
            int[] resSpecimens = specimens;
            for (int i = 1; i < resSpecimens.length; i++) {
                for (int j = resSpecimens.length - 1; j >= i; j--) {
                    if (resSpecimens[j - 1] > resSpecimens[j]) swap(resSpecimens, j);
                }
            }
            return resSpecimens;
        }
    }
}
