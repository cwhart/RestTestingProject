public class FrontTimes {

    // Given a String and a non-negative int n, we'll say that the
    // front of the String is the first 3 chars, or whatever is there
    // if the String is less than length 3. Return n copies of the front;
    //
    // frontTimes("Chocolate", 2) -> "ChoCho"
    // frontTimes("Chocolate", 3) -> "ChoChoCho"
    // frontTimes("Abc", 3) -> "AbcAbcAbc"
    public String frontTimes(String str, int n) {
        String returnString = "";
        String substr = "";
        if (str.length()>=3) {
            substr = str.substring(0,3);

        } else substr = str;

        for (int i=0; i<n; i++) {
            returnString = returnString + substr;
        }

        return returnString;
    }

}
