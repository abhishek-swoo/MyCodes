package library;

class matrix_Traversal {

    boolean RightdiagCheck(char arr[][], int r, int c, int k) {
        for (int i = 0; i <= r + c - 1; i++) {
            int u = 0;
            int v = i;
//            this while loop is travelling a column
            while (v >= 0) {
                if (u < r && v < c) {
                    System.out.println(u + " " + v);
                }
                u++;
                v--;
            }
        }
//        set return according to need
        return false;
    }

    boolean LeftDiagCheck(char arr[][], int r, int c, int k) {
        for (int i = 0; i <= r + c - 1; i++) {
            int u = i;
            int v = Math.max(r, c) - 1;
//            this while loop is travelling a column
            while (u >= 0) {
                if (u >= 0 && v >= 0 && u < r && v < c) {
                    System.out.println(u + " " + v);
                }
                u--;
                v--;
            }
        }
//        set return according to need
        return false;
    }

}
