import com.sun.tools.javac.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liu
 * @Date: 2022/9/7
 * @Description:
 * 1592. 重新排列单词间的空格
 * 给你一个字符串 text ，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，
 * 并且两个单词之间至少存在一个空格。题目测试用例保证 text 至少包含一个单词 。

 * 请你重新排列空格，使每对相邻单词之间的空格数目都 相等 ，并尽可能 最大化 该数目。如果不能重新平均分配所有空格，请
 * 将多余的空格放置在字符串末尾 ，这也意味着返回的字符串应当与原 text 字符串的长度相等。
 *
 * 返回 重新排列空格后的字符串 。
 *
 * 示例 1：
 * 输入：text = "  this   is  a sentence "
 * 输出："this   is   a   sentence"
 * 解释：总共有 9 个空格和 4 个单词。可以将 9 个空格平均分配到相邻单词之间，相邻单词间空格数为：9 / (4-1) = 3 个。
 *
 * 示例 2：
 * 输入：text = " practice   makes   perfect"
 * 输出："practice   makes   perfect "
 * 解释：总共有 7 个空格和 3 个单词。7 / (3-1) = 3 个空格加上 1 个多余的空格。多余的空格需要放在字符串的末尾。
 *
 * 示例 3：
 * 输入：text = "hello   world"
 * 输出："hello   world"
 *
 * 示例 4：
 * 输入：text = "  walks  udp package   into  bar a"
 * 输出："walks  udp  package  into  bar  a "
 *
 * 示例 5：
 * 输入：text = "a"
 * 输出："a"
 */
public class Num1592 {

    public String reorderSpaces(String text) {
        int cnt = 0;
        // 0 - 上一个字符为空格； 1 - 上一个字符不为空格
        int state = 0;
        int pre = -1;
        List<String> word = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (state == 0 && c != ' ') {
                // 单词开始
                pre = i;
                state = 1;
            } else if (state == 0 && c == ' ') {
                // 连续空格
                cnt++;
            } else if (state == 1 && c != ' ') {
                // 连续单词
            } else if (state == 1 && c == ' ') {
                // 单词结束
                cnt++;
                state = 0;
                word.add(text.substring(pre, i));
            }
        }
        if (state == 1) {
            // 字符最后非空格，结算单词
            word.add(text.substring(pre));
        }
        int midBlankCnt = cnt / Math.max((word.size() - 1), 1);
        int lastBlankCnt = cnt - midBlankCnt * (Math.max(word.size() - 1,  1));
        String midBlank = "";
        String lastBlank = "";
        while (midBlankCnt-- > 0) midBlank += " ";
        while (lastBlankCnt-- > 0) lastBlank += " ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.size(); i++) {
            sb.append(word.get(i));
            if (i == word.size() - 1 && word.size() != 1) {
                sb.append(lastBlank);
            } else {
                sb.append(midBlank);
            }
        }
        return sb.toString();
    }

    public String reorderSpaces2(String text) {
        List<String> word = new ArrayList<>();
        int blankCount = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                blankCount++;
                if (sb.length() > 0) {
                    word.add(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            word.add(sb.toString());
        }
        int wordCount = word.size();
        sb = new StringBuilder();
        if (wordCount == 1) {
            sb.append(word.get(0));
            for (int i = 0; i < blankCount; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
        int blankNum = blankCount / (wordCount - 1);
        int lastBlankNum = blankCount - blankNum * (wordCount - 1);
        StringBuilder midBlank = new StringBuilder();
        StringBuilder lastBlank = new StringBuilder();
        for (int i = 0; i < blankNum; i++) {
            midBlank.append(" ");
        }
        for (int i = 0; i < lastBlankNum; i++) {
            lastBlank.append(" ");
        }
        for (int i = 0; i < wordCount; i++) {
            sb.append(word.get(i));
            if (i == wordCount - 1) {
                sb.append(lastBlank.toString());
            } else {
                sb.append(midBlank.toString());
            }
        }
        return sb.toString();
    }

    public String reorderSpaces1(String text) {
        String[] word = text.trim().split("\\s+");
        String chars = text.replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();
        int blankCount = text.length() - chars.length();

        if (word.length == 1) {
            sb.append(word[0]);
            for (int i = 0; i < blankCount; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
        int blankNum = blankCount / (word.length-1);
        for (int i = 0; i < word.length; i++) {
            sb.append(word[i]);
            if (i == word.length - 1) {
                for (int j = 0; j < blankCount - blankNum * (word.length - 1); j++) {
                    sb.append(" ");
                }
            } else {
                for (int j = 0; j < blankNum; j++) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Num1592 num1592 = new Num1592();
        String text1 = "  this   is  a sentence ";
        Assert.check(num1592.reorderSpaces(text1).equals("this   is   a   sentence"));

        String text2 = " practice   makes   perfect";
        Assert.check(num1592.reorderSpaces(text2).equals("practice   makes   perfect "));

        String text3 = "hello   world";
        Assert.check(num1592.reorderSpaces(text3).equals("hello   world"));

        String text4 = "  walks  udp package   into  bar a";
        Assert.check(num1592.reorderSpaces(text4).equals("walks  udp  package  into  bar  a "));

        String text5 = "a";
        Assert.check(num1592.reorderSpaces(text5).equals("a"));

        String text6 = "  hello";
        Assert.check(num1592.reorderSpaces(text6).equals("hello  "));
    }

}
