// ShiritoriGame.java
// 기본 끝말잇기

class Player {
    private String name;
    private String[] wordList;
    private int count; // word count
    private int round; // round number
    private boolean alive;
    public static int global_count;
    public static String[] global_wordList;

    public Player(String playerName) {
        name = playerName;
        wordList = new String[5000];
        count = 0;
        global_count = 0;
        global_wordList = new String[100000000];
        alive = true;
    }

    public void inputWord(String in) {
        wordList[count++] = in;
        global_wordList[global_count++] = in;
    }

    public boolean toggleAlive() {
        return alive = !alive;
    }

    public boolean isAlive() {
        return alive;
    }

    // 사전조건: count > 0
    public String getLastWord() {
        return wordList[count-1];
    }

    public String getName() {
        return name;
    }

}

public class ShiritoriGame {

    private int playerCount;
    private int playerDeadCount;
    private Player[] player;
    private String[] words;
    private int time; // 플레이어가 이 시간 내에 단어를 내지 않으면 탈락
    private boolean timeDecay; // 라운드 끝날 때마다 시간 줄어들기
    private int lastPlayerIndex;
    private int nextPlayerIndex;
    private int round;

    // 하우스룰

    /* 두음규칙 */
    // 한자음 녀, 뇨, 뉴, 니 → 여, 요, 유, 이
    // 한자음 랴, 려, 례, 료, 류, 리 → 야, 여, 예, 요, 유, 이
    // 한자음 라, 래, 로, 뢰, 루, 르 → 나, 내, 노, 뇌, 누, 느
    private boolean dualSoundRule;
    DualSound dSoundRule;

    // /* 한방단어 막기 */
    // // 다음 사람이 못 이으면 마지막 단어를 낸 사람이 이어갈 말을 스스로 제시하게 해서,
    // // 못 이으면 마지막 단어를 낸 사람이 진 걸로 하는 룰
    // private boolean blockHanBangWordRule;

    // /* 앞말잇기 */
    // // 기존의 끝말잇기와는 반대로 '수영복→박수→조롱박'과 같은 식으로 이어나가야 하는 룰
    // private boolean backwardShiritoriRule;

    // /* 스토리 텔링 규칙 */
    // // 스토리 텔링처럼 문장으로 이어가 이야기 흐름에 어긋나는 문장으로 탈락
    // // 문장마다 투표가 필요함
    // private boolean storyTellingRule;

    // /* 말도 안 되는 말을 만들어 내기 */
    // // 사전에 수록되어 있는 단어를 내면 탈락
    // private boolean nonsenseWordRule;

    public ShiritoriGame(int numOfPlayers, int maxTime, boolean timeDec, boolean dueum) { //,
                    // boolean hanbang, boolean backward, boolean story, boolean nonsense) {
        playerCount = numOfPlayers;
        playerDeadCount = 0;
        player = new Player[playerCount];
        lastPlayerIndex = 0;
        time = maxTime;
        timeDecay = timeDec;
        dualSoundRule = dueum;
        dSoundRule = new DualSound();
        round = 0;
        words = new String[100000000];
        // blockHanBangWordRule = hanbang;
        // backwardShiritoriRule = backward;
        // storyTellingRule = story;
        // nonsenseWordRule = nonsense;
    }

    // 플레이어의 이름과 인덱스 입력
    public void inputName(String in, int id) {
        player[id] = new Player(in);
    }

    public boolean isValid(String in) {
        char currentChar = in.charAt(0);
        String lastWord = Player.global_wordList[Player.global_count-1]; // fucking hacked so hard.
        // i'm lazy as fuck so i'm not going to test last player index and shit. ain't gonna
        // fix it 'til it's broke

        char lastChar = lastWord.charAt(lastWord.length()-1);

        dSoundRule.checkWord(lastChar);

            if (dualSoundRule) {
                if (dSoundRule.getCharResult() != currentChar && 
                    dSoundRule.getOriginalChar() != currentChar)
                    return false;
            } else {
                if (lastChar != currentChar) return false;
            }
            // TODO: if not in dictionary, return false;
            return true;
    }


    // 사전조건: isValid == true
    public void playTurn(String in, int id) {
        player[id].inputWord(in);
        lastPlayerIndex = id;
        System.out.println("패스! " + getName(id) + "님, 다음 단어를 입력하세요!");
    }

    public String getName(int id) {
        return player[id].getName();
    }

    public int getNextPlayerIndex(int id) {
        int index = id;
        while (true) {
            index++;
            if (index == playerCount) index = 0;
            if (player[index].isAlive()) return index;
            if (index == id) return -1;
        }
    }
    
    public void toggleAlive(int id) {
        player[id].toggleAlive();
        playerDeadCount++;
    }

    public void increaseRound() {
        if (timeDecay && ++round % 2 == 0 && time > 5)
            time -= 3;
    }

    public int getTime() {
        return time;
    }

    public boolean winnerExists() {
        if (playerCount - playerDeadCount == 1) return true;
        return false;
    }

    public int getWinner() {
        if (winnerExists()) {
            for (int i=0; i<playerCount; i++) {
                if (player[i].isAlive()) return i;
            }
        }
        return -1;
    }

    public static String[] getGlobalWordList() {
        return Player.global_wordList;
    }

    public static void fuckit() {
        for (int i=0; i<Player.global_count; i++) {
            System.out.print(Player.global_wordList[i] + ",");
        }
    }
}
