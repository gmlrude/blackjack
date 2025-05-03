package org.example.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public final class Validator {
    private Validator() {}

    public static List<String> parseAndValidateNames(String line) {
        if (line.isBlank()) {
            throw new IllegalArgumentException("이름을 한 명 이상 입력해야 합니다.");
        }
        List<String> names = Arrays.stream(line.split(","))
                .map(String::trim)
                .toList();
        validatePlayerCount(names);
        names.forEach(Validator::validatePlayerName);
        return names;
    }

    public static void validatePlayerCount(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("최소 1명 이상의 플레이어가 필요합니다.");
        }
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("중복된 플레이어 이름이 있습니다.");
        }
    }

    public static void validatePlayerName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("플레이어 이름은 비어 있을 수 없습니다.");
        }
        if (name.equalsIgnoreCase("딜러")) {
            throw new IllegalArgumentException("'딜러'라는 이름은 사용할 수 없습니다.");
        }
    }

    public static BigDecimal validateBet(String input) {
        BigDecimal bet;
        try {
            bet = new BigDecimal(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액은 숫자로만 입력해야 합니다.");
        }
        validateBet(bet);
        return bet;
    }

    public static void validateBet(BigDecimal bet) {
        if (bet == null) {
            throw new IllegalArgumentException("베팅 금액은 필수 입력입니다.");
        }
        if (bet.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 커야 합니다.");
        }
    }
}
