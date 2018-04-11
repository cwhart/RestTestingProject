public class Sum {

    // Given an array of ints, return the sum of all the elements.
    //
    // sum({1, 2, 3}) -> 6
    // sum({5, 11, 2}) -> 18
    // sum({7, 0, 0}) -> 7
    public int sum(int[] numbers) {
        int retSum = 0;

        for(int i=0; i< numbers.length; i++) {
            retSum = retSum + numbers[i];
        } return retSum;
    }
}
