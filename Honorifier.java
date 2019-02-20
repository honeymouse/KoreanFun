// Honorifier.java

public class Honorifier {
    public static void main(String args[]) {
        // test cases
        System.out.println(honorify("아프다"));
        System.out.println(honorify("굽다"));
        System.out.println(honorify("움직이다"));
        System.out.println(honorify("놀다"));
        System.out.println(honorify("괜찮다"));
        System.out.println(honorify("드시다"));
    }

// 형용사 및 동사만 받음
    private static String honorify(String input) {
        char critBlock = input.charAt(input.length()-2);
        int finalConsonant = (critBlock - 44032) % 588 % 28; // 받침 값

        // System.out.println(finalConsonant);
        char noBatchim;
        String output;
        
        if (critBlock != '시' && input.charAt(input.length()-1) == '다') {
            // 특수 케이스
            if(!input.equals(specialWord(input))) {
                output = specialWord(input);
            }

            // 받침이 니은이나 리을일 경우
            else if(finalConsonant == 4 || finalConsonant == 8) {
                noBatchim = (char)removeBatchim(critBlock, finalConsonant);
                output = input.substring(0,input.length()-2) + noBatchim + "시다";
            }

            // 받침이 비읍일 경우
            else if(finalConsonant == 17) {
                noBatchim = (char)removeBatchim(critBlock, finalConsonant);
                output = input.substring(0,input.length()-2) + noBatchim + "우시다";
            }

            // 받침 있을 경우
            else if(finalConsonant > 0) {
                output = input.substring(0,input.length()-1) + "으시다";
            }

            // 받침 없을 경우
            else {
                output = input.substring(0,input.length()-1) + "시다";
            }
        } else {
            output = input;
        }
        return output;
    }

    private static int removeBatchim(char critBlock, int finalConsonant) {
        return critBlock - finalConsonant;
    }

    private static String specialWord(String input) {
        String output;
        switch(input) {
        case "갔다오다":
            output = "다녀오시다";
            break;
        case "먹다":
            output = "드시다/잡수시다";
            break;
        case "있다":
            output = "있으시다/계시다";
            break;
        case "자다":
            output = "주무시다";
            break;
        case "아프다":
            output = "편찮으시다";
            break;
        case "죽다":
            output = "돌아가시다";
            break;
        case "말하다":
            output = "말씀하시다";
            break;
        case "마시다":
            output = "드시다";
            break;
        case "데리다":
            output = "모시다";
            break;
        case "걷다":
            output = "걸으시다";
            break;
        case "깨닫다":
            output = "깨달으시다";
            break;
        case "듣다":
            output = "들으시다";
            break;
        case "묻다":
            output = "물으시다/묻으시다";
            break;
        case "싣다":
            output = "실으시다";
            break;
        default:
            output = input;
            break;
        }
        return output;
    }
}