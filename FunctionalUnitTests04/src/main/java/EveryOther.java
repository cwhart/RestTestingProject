public class EveryOther {

    // Given a String, return a new String made of every other
    // char starting with the first, so "Hello" yields "Hlo".
    //
    // everyOther("Hello") -> "Hlo"
    // everyOther("Hi") -> "H"
    // everyOther("Heeololeo") -> "Hello"
    public String everyOther(String str) {
        String retString = "";
        for (int i=0; i<str.length(); i+=2) {
            retString = retString + str.charAt(i);
        } return retString;
    }

}
