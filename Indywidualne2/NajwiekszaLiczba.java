import java.util.Arrays;

public class NajwiekszaLiczba {
    public static int[] greatestNumber (int [] toBeSorted){
        //Two loops for bubble sorting the elements
        for (int i = 0; i < toBeSorted.length ; i++) {
            for (int j = 1; j < toBeSorted.length-i ; j++) {
                //Casting ints to string
                String xString = Integer.toString(toBeSorted[j]);
                String prev = Integer.toString(toBeSorted[j-1]);
                int comparator; //compare two numbers and their digits, limit of loop is quantity of digits in shorter number
                if(xString.length()>=prev.length()) comparator=prev.length();
                else comparator=xString.length();
                for (int k = 0; k <comparator ; k++) {
                    //casting chars to int
                    String xDigitString = "" + xString.charAt(k);
                    String prevDigitString = "" + prev.charAt(k);
                    if (Integer.parseInt(xDigitString) > Integer.parseInt(prevDigitString)) {
                        swap(j,toBeSorted);
                        break;
                    } else if (Integer.parseInt(xDigitString) < Integer.parseInt(prevDigitString)) {
                        break;
                    } //when one number is shorter and it ends with the same number as the one in comparison
                    //comparator==xString.length() to compare them only once
                    else if (k==comparator-1&&Integer.parseInt(xDigitString) == Integer.parseInt(prevDigitString)&&comparator==xString.length()) {
                    swap(j,toBeSorted);
                    }
                }
            }


        }
        return toBeSorted;
    }
    public static int[] swap (int j, int[] toSwap){
        int temp = toSwap[j];
        toSwap[j] = toSwap[j - 1];
        toSwap[j - 1] = temp;
        return toSwap;
    }

    public static void main(String[] args) {
        int[] test = {9999999, 991,992,993,994,99};
        System.out.println(Arrays.toString(greatestNumber(test)));
    }
}
