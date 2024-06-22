package com.example.demo.util;

public final class StringUtil {
    private StringUtil() {}

    /**
     * 문자열을 마스킹합니다. 시작 위치부터 끝 위치까지 마스킹합니다.
     * @param plain 마스킹할 문자열
     * @param inclusiveStartIndex 시작 위치 (포함)
     * @param exclusiveEndIndex 끝 위치 (포함 안됨)
     * @return 마스킹된 이름
     */
    public static String mask(
            String plain,
            int inclusiveStartIndex,
            int exclusiveEndIndex
    ) {
        StringBuilder builder = new StringBuilder(plain);

        int maskLength = exclusiveEndIndex - inclusiveStartIndex;
        String masked = "*".repeat(maskLength);

        builder.replace(inclusiveStartIndex, exclusiveEndIndex, masked);

        return builder.toString();
    }

}
