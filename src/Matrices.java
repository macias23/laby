import java.util.Arrays;


public class Matrices {
    public static int [][] matrixReconstruction(int firstRowSum, int secondRowSum, int[] columnSum){
        int [][]reconstrucedMatrix = new int[2][columnSum.length];
        for (int i=0; i<columnSum.length;i++) {
        if ((firstRowSum>0&&secondRowSum>0)&&columnSum[i]==2) {
            reconstrucedMatrix[0][i] = 1;
            reconstrucedMatrix[1][i] = 1;
            firstRowSum--;
            secondRowSum--;
        }
            if ((columnSum[i]==1)) {
                if(firstRowSum>secondRowSum) {
                    reconstrucedMatrix[0][i] = 1;
                    reconstrucedMatrix[1][i] = 0;
                    firstRowSum--;
                }
                else{
                    reconstrucedMatrix[0][i] = 0;
                    reconstrucedMatrix[1][i] = 1;
                    secondRowSum--;
                }
            }
            if(columnSum[i]==0) {
                reconstrucedMatrix[0][i] = 0;
                reconstrucedMatrix[1][i] = 0;
            }
        }
        int[][]empty={{}};
        if (firstRowSum!=0||secondRowSum!=0) return empty;
        return reconstrucedMatrix;
    }
    public static void printArray(int[][]toBePrinted){
        for (int[]i:toBePrinted) {
            System.out.println(Arrays.toString(i));
        }
    }

    public static void main(String[] args) {
        int firstRowSum = 4;
        int secondRowSum = 3;
        int[] columnSum = {0,1,2,1,1,2,0};// rozwiązanie: [[1, 1, 1, 1, 0],[0, 1, 0, 0, 1]]
        int[][] toPrint = matrixReconstruction(firstRowSum,secondRowSum,columnSum);
        printArray(toPrint);
    }
}
