import com.sun.tools.javac.util.Assert;

/**
 * @Author: liu
 * @Date: 2022/9/13
 * @Description: 670. 最大交换
 *
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 示例 1 :
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 *
 * 示例 2 :
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 *
 * 注意:
 * 给定数字的范围是[0, 10^8]
 */
public class Num670 {

    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int n = chars.length - 1;
        int maxIdx = n;
        int leftIdx = -1;
        int rightIdx = -1;
        for (int i = n; i >= 0; i--) {
            int temp = chars[i];
            if (temp > chars[maxIdx]) {
                maxIdx = i;
            } else if (temp < chars[maxIdx]) {
                leftIdx = i;
                rightIdx = maxIdx;
            }
        }
        if (rightIdx > 0) {
            char t = chars[leftIdx];
            chars[leftIdx] = chars[rightIdx];
            chars[rightIdx] = t;
        }
        return Integer.parseInt(new String(chars));
    }

    public int maximumSwap1(int num) {
        if (num < 10) {
            return num;
        }
        char[] chars = String.valueOf(num).toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            int left = chars[i] - 48;
            int max = left;
            int maxIdx = i;
            for (int j = i + 1; j < chars.length; j++) {
                int right = chars[j] - 48;
                if (right >= max) {
                    max = right;
                    maxIdx = j;
                }
            }
            if (max > left) {
                char t = chars[i];
                chars[i] = chars[maxIdx];
                chars[maxIdx] = t;
                break;
            }
        }
        return Integer.parseInt(new String(chars));
    }

    public static void main(String[] args) {
        Num670 num670 = new Num670();
        Assert.check(num670.maximumSwap(0) == 0);
        Assert.check(num670.maximumSwap(9) == 9);
        Assert.check(num670.maximumSwap(98368) == 98863);
        Assert.check(num670.maximumSwap(2736) == 7236);
        Assert.check(num670.maximumSwap(9973) == 9973);
    }
}
