// DualSound.java
// 두음법칙
/*
한자음 녀, 뇨, 뉴, 니 → 여, 요, 유, 이
한자음 랴, 려, 례, 료, 류, 리 → 야, 여, 예, 요, 유, 이
한자음 라, 래, 로, 뢰, 루, 르 → 나, 내, 노, 뇌, 누, 느
*/

public class DualSound {

    // 두음법칙 강제 적용을 하지 않으면 '리튬'과 비슷한 단어로 게임이 끝날 수도 있음.
    private boolean forceChangeRule = false;

    // 두음법칙 적용 전
    private char originalChar;

    // 두음법칙 적용 후
    private char charResult;
    private int result;

    public DualSound(boolean forceChange) {
        forceChangeRule = forceChange;
    }

    // 자(字)를 입력 받아 두음법칙에 해당되든 안 되든 charResult에 저장
    public void checkWord(char in) {
        originalChar = in;
        result = originalChar;
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
        if (originalChar != charResult) return true;
        return false;
    }

    public boolean isRuleChangeForced() {
        if (forceChangeRule) return true;
        return false;
    }

    public int getResult() {
        return result;
    }

    public char getCharResult() {
        return charResult;
    }

    public char getOriginalChar() {
        return originalChar;
    }

    public String getOutputString() {
        if (originalChar != charResult && !forceChangeRule)
            return originalChar + "/" + charResult + "";
        else if (originalChar != charResult)
            return charResult + "";
        else
            return originalChar + "";
    }
}