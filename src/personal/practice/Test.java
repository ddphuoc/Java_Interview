import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public static List<Integer> rightSideView(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        List<Integer> list = new ArrayList<>();
        while(deque.size() > 0) {
            TreeNode node = deque.poll();
            list.add(node.val);
            if(node.right != null)
                deque.offer(node.right);
        }
        return list;
    }

    public static int getSum(String str){
        int sum = 0;
        String num = "";
        for(int i = 0; i < str.length(); i++) {
            if(Character.isDigit(str.charAt(i))) {
                num += str.charAt(i);
            }
            else {
                if(num.length() > 0) {
                    sum += Integer.parseInt(num);
                    num = "";
                }
            }
        }
        return sum;
    }

    static class TreeNode{
        int val;
        TreeNode left , right;
        TreeNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    public static List<List<Integer>> getAllPaths(TreeNode root){
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        travel(results, list, root);
        return results;
    }

    public static void travel(List<List<Integer>> results, List<Integer> list, TreeNode root){
        if(root.left == null && root.right == null){
            results.add(list);
        }
        list.add(root.val);
        if(root.left != null) {
            List<Integer> leftList = new ArrayList<>(list);
            travel(results, leftList, root.left);
        }
        if(root.right != null){
            List<Integer> rightList = new ArrayList<>(list);
            travel(results, rightList, root.right);
        }
    }

    public static void main(String[] args){
        String str = "aaaaaaaaaaaa";
        String pattern = "00";
        System.out.println(binaryPatternMatching(pattern, str));
    }

    public static int binaryPatternMatching(String pattern, String s) {
        int count = 0;
        if(s.length() < pattern.length())
            return 0;
        for(int i = 0; i < s.length() - pattern.length();i++){
            int j = 0;
            while(j < pattern.length()){
                char c = s.charAt(i + j);
                if(pattern.charAt(j) == '0'){
                    if("aeiouy".indexOf(c) < 0){
                        break;
                    }
                }
                else {
                    if("aeiouy".indexOf(c) >= 0) {
                        break;
                    }
                }
                j++;
            }
            if(j == pattern.length())
                count++;
            j = 0;
        }
        return count;
    }
}

 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
