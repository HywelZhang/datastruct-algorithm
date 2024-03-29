
package pers.hywel.algorithm.dynamic_programming;

/**
 * Description:
 * 72. Edit Distance
 * Hard
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 * @author RobertZhang
 * Created on 2020-05-18
 */
public class EditDistance {
    public static int minDistance(String word1, String word2) {
        // dp[i][j] 为i位和j位为末尾匹配时的最小距离
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++)
            dp[i][0] = i;
        for (int j = 0; j < word2.length() + 1; j++)
            dp[0][j] = j;
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                // s1.chartAt(i) == s2.chartAt(j) ——> dp[i][j] = dp[i - 1][j - 1]
                if (word1.charAt(i) == word2.charAt(j))
                    dp[i + 1][j + 1] = dp[i][j];
                // s1.charAt(i) != s2.charAt(j) ——> dp[i][j] = dp[i - 1][j] (s1删除最后一位)
                //                                  dp[i][j] = dp[i][j - 1] (s1增加一位)
                //                                  dp[i][j] = dp[i - 1][j - 1] (s1替换最后一位)
                else
                    dp[i + 1][j + 1] = 1 +
                            Math.min(dp[i + 1][j], Math.min(dp[i][j + 1], dp[i][j]));
            }
        }
        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        String s1 = "intention";
        String s2 = "execution";
        System.out.println(minDistance(s1, s2));
    }
}
