import com.sun.tools.javac.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liu
 * @Date: 2022/9/17
 * @Description: 1624. 两个相同字符之间的最长子字符串
 *
 * 给你一个字符串 s，请你返回 两个相同字符之间的最长子字符串的长度 ，计算长度时不含这两个字符。如果不存在这样的子字符串，返回 -1 。
 *
 * 子字符串 是字符串中的一个连续字符序列。
 *
 * 示例 1：
 * 输入：s = "aa"
 * 输出：0
 * 解释：最优的子字符串是两个 'a' 之间的空子字符串。
 *
 * 示例 2：
 * 输入：s = "abca"
 * 输出：2
 * 解释：最优的子字符串是 "bc" 。
 *
 * 示例 3：
 * 输入：s = "cbzxy"
 * 输出：-1
 * 解释：s 中不存在出现出现两次的字符，所以返回 -1 。
 *
 * 示例 4：
 * 输入：s = "cabbac"
 * 输出：4
 * 解释：最优的子字符串是 "abba" ，其他的非最优解包括 "bb" 和 "" 。
 *
 */
public class Num1624 {

    public int maxLengthBetweenEqualCharacters(String s) {
        Map<Character, Integer> char2Idx = new HashMap<>();
        int n = s.length();
        int max = -1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (char2Idx.containsKey(c)) {
                max = Math.max(max, i - char2Idx.get(c) - 1);
            } else {
                char2Idx.put(c, i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Num1624 num1624 = new Num1624();
        Assert.check(num1624.maxLengthBetweenEqualCharacters("aa") == 0);
        Assert.check(num1624.maxLengthBetweenEqualCharacters("abca") == 2);
        Assert.check(num1624.maxLengthBetweenEqualCharacters("cbzxy") == -1);
        Assert.check(num1624.maxLengthBetweenEqualCharacters("cabbac") == 4);
    }

}
