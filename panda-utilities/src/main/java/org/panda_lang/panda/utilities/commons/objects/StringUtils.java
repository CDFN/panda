/*
 * Copyright (c) 2015-2018 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.panda.utilities.commons.objects;

public class StringUtils {

    /**
     * Instance of the empty string
     */
    public static final String EMPTY = "";

    /**
     * @param text             to check
     * @param exceptionMessage the message is used by {@link IllegalArgumentException}
     * @return the checked text
     */
    public static String isEmpty(String text, String exceptionMessage) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException(exceptionMessage);
        }

        return text;
    }

    /**
     * Faster alternative to str.replace
     */
    public static String replace(String text, String searchString, String replacement) {
        if (text == null || text.isEmpty() || searchString.isEmpty()) {
            return text;
        }

        if (replacement == null) {
            replacement = EMPTY;
        }

        int start = 0;
        int max = -1;
        int end = text.indexOf(searchString, start);

        if (end == -1) {
            return text;
        }

        int replaceLength = searchString.length();
        int increase = replacement.length() - replaceLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= 16;
        StringBuilder sb = new StringBuilder(text.length() + increase);

        while (end != -1) {
            sb.append(text.substring(start, end)).append(replacement);
            start = end + replaceLength;

            if (--max == 0) {
                break;
            }

            end = text.indexOf(searchString, start);
        }

        sb.append(text.substring(start));
        return sb.toString();
    }

    /**
     * @param str a string to search
     * @return whitespaces at the beginning of the specified string
     */
    public static String extractParagraph(String str) {
        int count = str.indexOf(str.trim());
        return str.substring(0, count);
    }

    /**
     * @param str     a string to search
     * @param findStr a searched string
     * @return amount of occurrences
     */
    public static int countOccurrences(String str, String findStr) {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {
            lastIndex = str.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }

        return count;
    }

    /**
     * @param string     a string to search
     * @param characters searched characters
     * @return true if the specified string contains any of the specified characters
     */
    public static boolean containsCharacter(String string, char... characters) {
        for (char c : string.toCharArray()) {
            for (char character : characters) {
                if (c == character) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @param str a string to search
     * @return true if the specified string contains a character other than a letter or a digit
     */
    public static boolean containsSpecialCharacters(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param str string to check
     * @return true if the specified text can be a number
     */
    public static boolean isNumber(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                continue;
            }

            if (c == 'x' || c == '.') {
                continue;
            }

            return false;
        }

        return true;
    }

    /**
     * @param str string to convert
     * @return char codes separated by spaces
     */
    @Deprecated
    public static String toCharCodes(String str) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : str.toCharArray()) {
            stringBuilder.append((int) c);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    /**
     * @param spaces number of spaces
     * @return generated indentation
     */
    public static String createIndentation(int spaces) {
        StringBuilder gapBuilder = new StringBuilder();

        for (int i = 0; i < spaces; ++i) {
            gapBuilder.append(" ");
        }

        return gapBuilder.toString();
    }

}
