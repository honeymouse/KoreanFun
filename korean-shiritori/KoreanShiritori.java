

public class KoreanShiritori {

    boolean gameActive = false;
    private int numOfPlayers;
    private Player[] game;
    private String[] words;
    private int wordCount;
    private int timePerPlayer;
    private int roundNum;
    DualSound dSound;

    public KoreanShiritori(int num, boolean forceDualSoundRule) {
        numOfPlayers = num;
        game = new Player[num];
        wordCount = 0;
        timePerPlayer = 15;
        roundNum = 1;
        gameActive = true;
        dSound = new DualSound(forceDualSoundRule);
        words = new String[(num+3)*1000]; // TODO: add out of bounds exception later
    }

    public void increaseRoundNum() {
        roundNum++;
        if (roundNum % 2 == 0 && timePerPlayer > 5)
            timePerPlayer -= 3;
    }

    private int registeredPlayers = 0;
    public void addPlayer(String name) {
        game[registeredPlayers] = new Player(name);
        registeredPlayers++;
    }

    public void inputWord(String in, int playerID) {
        words[++wordCount] = in;
        game[playerID].inputWord(in);
        if (wordCount % numOfPlayers == 0)
            increaseRoundNum();
        if (wordCount == words.length) gameActive = false;
    }

    public boolean isValid(String in, int playerID) {
        if (wordCount == 1) return true;
        String lastWord = words[wordCount-1];
        char lastChar = lastWord.charAt(lastWord.length()-1);
        return game[playerID].isValid(lastChar);
    }
    
    public String getValidChar(String in) {
        if (in.equals("(없음)")) return "없음";
        dSound.checkWord(in.charAt(in.length()-1));
        return dSound.getOutputString();
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public String getName(int playerID) {
        return game[playerID].getName();
    }
    


    class Player {

        private String playerName;
        private String word;

        public Player(String name) {
            playerName = name;
        }

        private void inputWord(String in) {
            word = in;
        }

        public boolean isValid(char lastWord) {
            char currentChar = word.charAt(0);
            dSound.checkWord(lastWord);
            if (dSound.isRuleChangeForced()) {
                if (dSound.isApplied()) {
                    if (dSound.getCharResult() != currentChar) return false;
                }
                else {
                    if (lastWord != currentChar) return false;
                }
            }
            else {
                if (dSound.isApplied()) {
                    if (dSound.getCharResult() != currentChar && 
                        dSound.getOriginalChar() != currentChar)
                        return false;
                }
                else {
                    if (lastWord != currentChar) return false;
                }
            }
            // TODO: if not in dictionary, return false;
            return true;
        }

        public String getName() {
            return playerName;
        }

        public String getWord() {
            return word;
        }
    }

}