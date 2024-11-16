package Common;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class myCharacterClass {
    //
    private static myCharacterClass instance;

    public static myCharacterClass getInstance() {
        if(myCharacterClass.instance == null)
            myCharacterClass.instance = new myCharacterClass();
        return myCharacterClass.instance;
    }

    // Kiểm tra trong chuỗi có ít nhất 1 ký tự là chữ cái hay không ?
    public boolean hasOneCharacterIsLetter(String s) {
        for(int i = 0; i < s.length(); i++) {
            if(Character.isLetter(s.charAt(i))) return true;
        }
        return false;
    }
    // Kiểm tra trong chuỗi có ít nhất 1 ký tự không phải là chữ số hay không ?
    public boolean hasOneCharacterIsNotNumber(String s) {
        for(int i = 0; i < s.length(); i++) {
            if(!Character.isDigit(s.charAt(i))) return true;
        }
        return false;
    }
    // Chuyển đổi ký tự có dấu thành ký tự thường
    public String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
    // Xoá các ký tự đặc biệt (không phải là ký tự chữ cái, chữ số)
    public String removeSpecialCharacter(String s) {
        return s.replaceAll("[^a-zA-Z0-9]", " ");
    }
    // Kết hợp cả 2 hàm trên
    public String normalCharacterFormat(String s) {
        return removeSpecialCharacter(removeAccent(s)
);
    }
}