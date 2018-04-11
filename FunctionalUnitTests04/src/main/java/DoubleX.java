public class DoubleX {
    // Given a String, return true if the first instance
    // of "x" in the String is immediately followed by
    // another "x".
    //
    // doubleX("axxbb") -> true
    // doubleX("axaxxax") -> false
    // doubleX("xxxxx") -> true
    public boolean doubleX(String str) {

        for(int i=0; i<str.length()-2; i++) {
            if(str.substring(i,i+1).equals("x")) {
                if(str.substring(i+1,i+2).equals("x")) {
                    return true;
                } else return false;
            }
        } return false;
    }
}

