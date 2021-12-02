
package pers.hywel.algorithm.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * 46. Permutations
 * Medium
 *
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * @author RobertZhang
 * Created on 2020-05-20
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTracking(res, new LinkedList<>(), nums, 0);
        return res;
    }

    private void backTracking(List<List<Integer>> res, List<Integer> tempRes, int[] nums, int start) {
        if (start == nums.length) res.add(new ArrayList<>(tempRes));
        else {
            for (int i = start; i < nums.length; i++) {
                swap(nums, start, i);
                tempRes.add(nums[start]);

                backTracking(res, tempRes, nums, start + 1);

                swap(nums, i, start);
                tempRes.remove(tempRes.size() - 1);
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static List<List<Integer>> permuteBrute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length < 1) return res;
        List<Integer> firstList = new LinkedList<>();
        firstList.add(nums[0]);
        res.add(firstList);
        for (int i = 1; i < nums.length; i++) {
            List<List<Integer>> subRes = new LinkedList<>();
            for (List subList : res) {
                for (int k = 0; k <= subList.size(); k++) {
                    List<Integer> list = new LinkedList<>(subList);
                    list.add(k, nums[i]);
                    subRes.add(list);
                }
            }
            res.addAll(subRes);
            int currentLength = i + 1;
            res = res.stream().filter(list -> list.size() == currentLength).collect(Collectors.toList());
        }
        return res;
    }

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        int[] array = new int[]{1, 2, 3};
        List<List<Integer>> res = permutations.permute(array);
        for (List<Integer> list : res) {
            for (Integer i : list) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }
}
