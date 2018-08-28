package ee.sda.maven.hackerrank;

public class DiagonalDifference {
    //CMD+ Shift + T


    // Complete the diagonalDifference function below.
    public int diagonalDifference(int[][] arr) {

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == j) {
                    sum += arr[i][j] - arr[i][(arr[i].length - 1) - j];
                }
            }
        }
        return Math.abs(sum);
    }

//        //nested for loop to read in full matrix?
//        //read in first array of diagonals
//        for (int i = 0; i < arr.length ; i++) {
//            //read in second array of diagonals
//            for (int j = 0; j < arr[i].length ; j++) {
//                if (i == j) {
//                    sum += arr[i][j]] - arr[i][(arr[i])]
//                }
//                //produce sum of primary diagonal within array
//                diag1Sum  ;
//                //produce sum of secondary diagonal within array
//                diag2Sum ;
//                //produce absolute difference
//                diff;
//            }
//        }
//
//
//        return 0;
//    }
}


















































