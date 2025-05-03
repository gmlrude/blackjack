package org.example.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        while (true) {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            String line = scanner.nextLine().trim();
            try {
                List<String> names = Validator.parseAndValidateNames(line);
                return names;
            } catch (IllegalArgumentException e) {
                System.out.println("입력 오류: " + e.getMessage() + " 다시 시도해주세요.");
            }
        }
    }

    public static BigDecimal readBet(String playerName) {
        while (true) {
            System.out.printf("%s의 배팅 금액은?%n", playerName);
            String input = scanner.nextLine().trim();
            try {
                return Validator.validateBet(input);
            } catch (IllegalArgumentException e) {
                System.out.println("입력 오류: " + e.getMessage() + " 다시 입력해 주세요.");
            }
        }
    }

    public static boolean wantsToDraw(String playerName) {
        while (true) {
            System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
            String input = scanner.nextLine().trim().toLowerCase();
            if ("y".equals(input)) return true;
            if ("n".equals(input)) return false;
            System.out.println("입력 오류: 'y' 또는 'n' 만 입력 가능합니다. 다시 입력해주세요.");
        }
    }
}

