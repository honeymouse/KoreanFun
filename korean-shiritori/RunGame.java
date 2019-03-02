import java.util.Scanner;

public class RunGame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("플레이어 수 입력: ");
        int num = scan.nextInt();
        boolean forceDualSoundRule = false;
        scan.nextLine();
        // System.out.print("Force Sound Change Rule? (0 or 1): ");
        // boolean force = scan.nextInt();
        KoreanShiritori game = new KoreanShiritori(num, forceDualSoundRule);
        for (int i=0; i<num; i++) {
            System.out.print("플레이어 " + i + "번의 이름을 입력하세요: ");
            game.addPlayer(scan.nextLine());
        }
        String input = "(없음)";
        while (true) {
            boolean quitGame = false;
            if (!game.isGameActive()) break;
            for (int i=0; i<num; i++) {
                System.out.print(game.getName(i) + "의 차례입니다. [" + game.getValidChar(input) + "] 입력: ");
                input = scan.nextLine();
                game.inputWord(input, i);
                if (!game.isValid(input, i)) {
                    System.out.println(game.getName(i) + "님이 졌습니다! 메롱.");
                    quitGame = true;
                    break;
                }
            }
            if (quitGame) break;
        }
        scan.close();
    }
}