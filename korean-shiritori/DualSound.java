// DualSound.java
// 두음법칙

public class DualSound {

    // 두음법칙 적용 전
    private char originalChar;

    // 두음법칙 적용 후
    private char charResult;

    public DualSound() {
    }

    // 자(字)를 입력 받아 두음법칙에 해당되든 안 되든 charResult에 저장
    public void checkWord(char in) {
        originalChar = in;
        int result = in;
        charResult = in;
        int cons2 = (in - 44032) % 588 % 28;
        int vowel = (in - cons2 - 44032) % 588 / 28;
        int cons1 = (in - 44032 - cons2 - vowel*28) / 588; // wrong value
        if (cons1 == 2) {
            if (vowel==6 || vowel==12 || vowel==17 || vowel==20)
                result = 50500 + vowel*28 + cons2;
        } else if (cons1 == 5) {
            if (vowel==2 || vowel==6 || vowel==7 || vowel==12 || vowel==17 || vowel==20)
                result = 50500 + vowel*28 + cons2;
            else if (vowel==0 || vowel==1 || vowel==8 || vowel==11 || vowel==13 || vowel==18)
                result = 45208 + vowel*28 + cons2;
        }
        charResult = (char)result;
    }

    public boolean isApplied() {
        return originalChar != charResult;
    }

    public char getCharResult() {
        return charResult;
    }

    public char getOriginalChar() {
        return originalChar;
    }
}