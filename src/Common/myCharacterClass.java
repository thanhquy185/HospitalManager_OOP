package Common;

public class myCharacterClass {
    // Properties
    private static myCharacterClass instance;

    // Setter - Getter
    public static myCharacterClass getInstance() {
        if(myCharacterClass.instance == null)
            myCharacterClass.instance = new myCharacterClass();
        return myCharacterClass.instance;
    }

    // Methods
    // - Kiểm tra trong chuỗi có toàn ký tự là chữ cái chữ cái hay không ?
    public boolean hasAllCharacterIsLetter(String s) {
        if(s.length() == 0) return false;
        String validChars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOASDFGHJKLZXCVBM";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(validChars.indexOf(c) == -1) return false;
        }
        return true;
    }
    // - Kiểm tra trong chuỗi có toàn ký tự chữ số hay không ?
    public boolean hasAllCharacterIsNumber(String s) {
        if(s.length() == 0) return false;
        String validChars = "1234567890";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(validChars.indexOf(c) == -1) return false;
        }
        return true;
    }
    // - Hàm kiểm tra cho phép một chuỗi chỉ có ký tự chữ cái và chữ số
    public boolean onlyHasLetterAndDigit(String s) {
        String validChars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOASDFGHJKLZXCVBM1234567890";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(validChars.indexOf(c) == -1) return false;
        }
        return true;
    }
    // - Hàm kiểm tra tên có hợp lệ hay không (bao gồm: ký tự chữ cái không dấu, chữ cái có dấu và khoảng trắng)
    public boolean isValidName(String s) {
        if (s.length() == 0) return false;
        String validChars = "aáàảãạăắằẳẵặâấầẩẫậbcdđeéèẻẽẹêếềểễệfghiíìỉĩịjklmnoóòỏõọôốồổỗộơớờởỡợpqrstuúùủũụưứừửữựvwxyýỳỷỹỵzAÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬBCDĐEÉÈẺẼẸÊẾỀỂỄỆFGHIÍÌỈĨỊJKLMNOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢPQRSTUÚÙỦŨỤƯỨỪỬỮỰVWXYÝỲỶỸỴZ ";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (validChars.indexOf(c) == -1) return false;
        }
        return true;
    }
}