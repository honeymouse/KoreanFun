
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("플레이어 수: ");
        int playerCount = scan.nextInt();
        System.out.print("최대 시간(초 단위): ");
        int sec = scan.nextInt();
        String name;
        scan.nextLine();
        ShiritoriGame game = new ShiritoriGame(playerCount,sec,true,true);
        for (int i=0; i<playerCount; i++) {
            System.out.print("플레이어 " + (i+1) + "번의 이름: ");
            name = scan.nextLine();
            game.inputName(name, i);
        }
        System.out.println("안녕하세요 " + game.getName(0) + "님. 단어를 내서 게임을 시작하세요!");
        String word = scan.nextLine();
        game.playTurn(word, 0);
        int count = 0;
        int index = 0;
        while (!game.winnerExists()) {
            sec = game.getTime();
            index = game.getNextPlayerIndex(index);
            long startTime = System.currentTimeMillis();
            long currentTime = System.currentTimeMillis();
            long timeElapsed = currentTime - startTime;
            while (timeElapsed < sec*1000) {
                //ShiritoriGame.fuckit();
                word = scan.nextLine();
                if (word.equals("\"종료\"")) break;
                if (game.isValid(word))  break; 
                else {
                    System.out.println("아야! \"" + word + "\"은/는 안 돼! 아직" + (Math.round(sec - timeElapsed/1000.0)) + "초 남았으니 얼른 딴 걸 입력하렴!");
                }
                currentTime = System.currentTimeMillis();
                timeElapsed = currentTime - startTime;
            }
            if (game.isValid(word)) {
                game.playTurn(word, index);
            }
            else if (word.equals("\"종료\"")) {
                break;
            }
            else {
                System.out.println(game.getName(index) + "님이 탈락되었습니다.");
                game.toggleAlive(index);
            }
            count++;
            if (count % playerCount == 0) game.increaseRound();

        }
        if (game.winnerExists()) System.out.println(game.getName(game.getWinner()) + "님이 이겼습니다! 축하드립니다!");
        System.out.println("게임이 종료되었습니다.");
    }
}