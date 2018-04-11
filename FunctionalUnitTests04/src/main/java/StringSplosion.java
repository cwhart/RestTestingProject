public class StringSplosion {

    // Given a non-empty String like "Code" return a String like
    // â€œCCoCodCode".  (first char, first two, first 3, etc)
    //
    // stringSplosion("Code") -> "CCoCodCode"
    // stringSplosion("abc") -> "aababc"
    // stringSplosion("ab") -> "aab"
    public String stringSplosion(String str) {
        String retStr = "";

        for (int i=0; i<str.length(); i++) {
            retStr = retStr + str.substring(0,i+1);
        } return retStr;
    }

}
