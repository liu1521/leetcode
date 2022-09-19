import com.sun.tools.javac.util.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liu
 * @Date: 2022/9/19
 * @Description: 1636. 按照频率将数组升序排序
 *
 * 给你一个整数数组nums，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。
 *
 * 请你返回排序后的数组。
 *
 * 示例 1：
 * 输入：nums = [1,1,2,2,2,3]
 * 输出：[3,1,1,2,2,2]
 * 解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
 *
 * 示例 2：
 * 输入：nums = [2,3,1,3,2]
 * 输出：[1,3,3,2,2]
 * 解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
 *
 * 示例 3：
 * 输入：nums = [-1,1,-6,4,5,-6,1,4,1]
 * 输出：[5,-1,4,4,-6,-6,1,1,1]
 *
 * 提示：
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 */
public class Num1636 {

    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        return Arrays.stream(nums)
                .boxed()
                .sorted((a, b) -> {
                    int frequency1 = frequencyMap.get(a);
                    int frequency2 = frequencyMap.get(b);
                    if (frequency1 == frequency2) {
                        return b - a;
                    } else {
                        return frequency1 - frequency2;
                    }
                })
                .mapToInt(i -> i)
                .toArray();
    }

    public static void main(String[] args) {
        Num1636 num1636 = new Num1636();

        int[] nums = {1, 1, 2, 2, 2, 3};
        Assert.check(Arrays.toString(num1636.frequencySort(nums)).equals("[3, 1, 1, 2, 2, 2]"));

        nums = new int[]{2,3,1,3,2};
        Assert.check(Arrays.toString(num1636.frequencySort(nums)).equals("[1, 3, 3, 2, 2]"));

        nums = new int[]{-1,1,-6,4,5,-6,1,4,1};
        Assert.check(Arrays.toString(num1636.frequencySort(nums)).equals("[5, -1, 4, 4, -6, -6, 1, 1, 1]"));
    }

}
