public class RotateLeft {

    // Given an array of ints, return an array with the elements
    // â€œrotated left" so {1, 2, 3} yields {2, 3, 1}.
    //
    // rotateLeft({1, 2, 3}) -> {2, 3, 1}
    // rotateLeft({5, 11, 9}) -> {11, 9, 5}
    // rotateLeft({7, 0, 0}) -> {0, 0, 7}
    public int[] rotateLeft(int[] numbers) {
        int holder = numbers[0];

        for(int i=0; i<numbers.length-1; i++) {
            numbers[i] = numbers[i+1];
        }
        numbers[numbers.length-1] = holder;
        return numbers;
    }
}
