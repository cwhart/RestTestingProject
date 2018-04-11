public class MultipleEndings {
    // Given a String, return a new String made of 3 copies
    // of the last 2 chars of the original String. The String
    // length will be at least 2.
    //
    // multipleEndings("Hello") -> "lololo"
    // multipleEndings("ab") -> "ababab"
    // multipleEndings("Hi") -> "HiHiHi"
    public String multipleEndings(String str) {
        String substring = str.substring(str.length()-2, str.length());
        return substring + substring + substring;
    }

}
