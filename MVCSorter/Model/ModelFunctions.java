public class ModelFunctions {
        public int [] createRanNum(int [] massive){
            for (int i = 0; i < massive.length; i++) {
                massive[i] = (int) Math.round(Math.random() * 100);
            }
            return massive;
        }

        public int[] iSortData(int[] specimens) {
            for (int i = 1; i < specimens.length; i++) {
                for (int j = i; j > 0; j--) {
                    if (specimens[j] < specimens[j - 1]) {
                        int temp = specimens[j-1];
                        specimens[j-1] = specimens[j];
                        specimens[j] = temp;
                    }
                }
            }
            return specimens;
        }

        public int[] bSortData(int[] specimens) {
            for (int i = 1; i < specimens.length; i++) {
                for (int j = specimens.length - 1; j >= i; j--) {
                    if (specimens[j - 1] > specimens[j]) {
                        int temp = specimens[j - 1];
                        specimens[j - 1] = specimens[j];
                        specimens[j] = temp;
                    }
                }
            }
            return specimens;
        }

        public int[] quickSort(int low, int high, int[] specimens) {
            int i = low, j = high;
            int core = specimens[low + (high - low) / 2];
            while (i <= j) {
                while (specimens[i] < core) {
                    i++;
                }
                while (specimens[j] > core) {
                    j--;
                }
                if (i <= j) {
                    int temp = specimens[i];
                    specimens[i] = specimens[j];
                    specimens[j] = temp;
                    i++;
                    j--;
                }
                if (low < j)
                    quickSort(low, j, specimens);
                if (i < high)
                    quickSort(i, high, specimens);
            }
            return specimens;
        }
}
