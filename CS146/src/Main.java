public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[] A = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        //average case (random): 14,20,6,12,1,15,3,7,4,11,5,16,19,8,18,13,17,2,10,9,
        //best case (evenly partition): 1,3,4,2,6,8,9,7,5,11,13,14,12,16,17,19,20,18,15,10,
        //worst case (most unbalanced partition): 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20
        printA(A);
        long startTime = System.nanoTime();
        QuickSort(A, 0, A.length - 1);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        printA(A);
        System.out.println("Performance: " + duration);
    }

    public static void QuickSort(int[] A, int p, int r) {
        if (p < r) {
            int q = Partition(A, p, r);
            QuickSort(A, p, q - 1);
            QuickSort(A, q + 1, r);
        }
    }

    public static int Partition(int[] A, int p, int r) {
        int x = A[r];
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            if (A[j] <= x) {
                i = i + 1;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, r);
        return i + 1;
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void printA(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();

    }
}
