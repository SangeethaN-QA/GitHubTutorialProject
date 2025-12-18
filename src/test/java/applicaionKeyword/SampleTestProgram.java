package applicaionKeyword;

public class SampleTestProgram {

    public static void main(String[] args) {

        checkValueInArray();
        checkValue();
    }

    public static void checkValueInArray() {

        int[] numbers = {10, 20, 30, 40, 50};
        int target = 30;

        //int target = 70 // not found error

        // Step 1: Assume we haven't found the number
        int index = -1;

        // Step 2: Search through the array
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                index = i;   // Found it! Update index
                break;
            }
        }

        // Step 3: Check if we found it or not
        if (index == -1) {
            System.out.println("Number " + target + " not found in array.");
        } else {
            System.out.println("Number " + target + " found at index: " + index);
        }
    }

    public static void checkValue()
    {
        for (int i = 1; i <= 5; i++) {
            if (i == 3) continue;
            System.out.println("check" +i);
        }

    }
}
