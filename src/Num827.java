import com.sun.tools.javac.util.Assert;

import java.util.*;

/**
 * @Author: liu
 * @Date: 2022/9/18
 * @Description: 827. 最大人工岛
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格0 变成1 。
 *
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 *
 * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
 *
 * 示例 1:
 * 输入: grid = [[1, 0], [0, 1]]
 * 输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 *
 * 示例 2:
 * 输入: grid = [[1, 1], [1, 0]]
 * 输出: 4
 * 解释: 将一格0变成1，岛屿的面积扩大为 4。
 *
 * 示例 3:
 * 输入: grid = [[1, 1], [1, 1]]
 * 输出: 4
 * 解释: 没有0可以让我们变成1，面积依然为 4。
 *
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] 为 0 或 1
 */
public class Num827 {

    int[][] dirs = new int[][] {{-1, 0}, {1,0}, {0,-1}, {0,1}};

    int[][] grid;

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        int island = 2;
        int maxSize = 0;
        Map<Integer, Integer> island2Size = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(i, j, island);
                    maxSize = Math.max(size, maxSize);
                    island2Size.put(island, size);
                    island++;
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    int totalSize = 1;
                    Set<Integer> connected = new HashSet<>();
                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x < 0 || x >= grid.length || y < 0 || y >= grid[i].length || connected.contains(grid[x][y])) continue;
                        totalSize += island2Size.getOrDefault(grid[x][y], 0);
                        connected.add(grid[x][y]);
                    }
                    maxSize = Math.max(totalSize, maxSize);
                }
            }
        }
        return maxSize;
    }

    private int dfs(int x, int y, int island) {
        int size = 1;
        grid[x][y] = island;
        for (int[] dir : dirs) {
            int i = x + dir[0];
            int j = y + dir[1];
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) continue;
            if (grid[i][j] == 1) {
                size += dfs(i, j, island);
            }
        }
        return size;
    }

    public static void main(String[] args) {
        Num827 num827 = new Num827();

        int[][] gird = new int[][]{{1, 0}, {0, 1}};
        Assert.check(num827.largestIsland(gird) == 3);

        gird = new int[][]{{1, 1}, {1, 0}};
        Assert.check(num827.largestIsland(gird) == 4);

        gird = new int[][]{{1, 1}, {1, 1}};
        Assert.check(num827.largestIsland(gird) == 4);

        gird = new int[][]{
                {0,0,0,0,0,0,0},
                {0,1,1,1,1,0,0},
                {0,1,0,0,1,0,0},
                {1,0,1,0,1,0,0},
                {0,1,0,0,1,0,0},
                {0,1,0,0,1,0,0},
                {0,1,1,1,1,0,0}};
        Assert.check(num827.largestIsland(gird) == 18);


    }

}
