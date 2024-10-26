package Common;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class CharacterFormat {
    // Chuyển đổi ký tự có dấu thành ký tự thường
    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
    // Xoá các ký tự đặc biệt (không phải là ký tự chữ cái, chữ số)
    public static String removeSpecialCharacter(String s) {
        return s.replaceAll("[^a-zA-Z0-9]", " ");
    }
    // Kết hợp cả 2 hàm trên
    public static String normalCharacterFormat(String s) {
        return CharacterFormat.removeSpecialCharacter(
            CharacterFormat.removeAccent(s)
        );
    }
}