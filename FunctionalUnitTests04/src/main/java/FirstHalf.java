public class FirstHalf {

    // Given a String of even length, return the first half.
    // So the String "WooHoo" yields "Woo".
    //
    // firstHalf("WooHoo") -> "Woo"
    // firstHalf("HelloThere") -> "Hello"
    // firstHalf("abcdef") -> "abc"
    public String firstHalf(String str) {
        return str.substring(0, str.length()/2);
    }

}
