public class TrimOne {

    // Given a String, return a version without the first and
    // last char, so "Hello" yields "ell". The String length will be at least 2.
    //
    // trimOne("Hello") -> "ell"
    // trimOne("java") -> "av"
    // trimOne("coding") -> "odin"
    public String trimOne(String str) {
        String retStr = "";

        for (int i=1; i<str.length()-1; i++) {
            retStr = retStr + str.charAt(i);
        } return retStr;
    }

}
