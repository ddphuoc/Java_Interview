package personal.practice;

import java.net.SocketPermission;
import java.security.AccessController;
import java.security.Permission;
import java.sql.Timestamp;
import java.util.*;

class Solution {

    public static void main(String[] args){
        //System.out.println(isPalindrome("race Car"));
//        System.out.println(permute("abc"));
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(4);
//        root.right = new TreeNode(2);
//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(3);
//        System.out.println(isSymmetric(root));
        //System.out.println(camelCaseSeparation(new String[]{"is","valid","right"}, "isValid"));
        System.out.println(sumsDivisibleByK(new int[]{0,2,3,4,6}, 3));
    }

    /*
Given an array of integers, return an
list of integers which contains
[1st integer, Sum of next 2 integers
(2nd, 3rd), Sum of next 3 integers (4th, 5th, 6th)] and so on
so on means sum up next 4 , next 5, next 6, next 7

Input
[1,6,8,5,9,4,7,2]
Output
[1,14,18,9]

in the result

1st Value - 1
2nd Value - 6 + 8 -> 14
3rd Value - 5 + 9 + 4 -> 18
4th Value - 7 + 2 -> Sum of next Four elements but four elements
 are not present in the array
so will sum up the remaining values -> 9

Your algorithms should work for assuming there can be n elements in the array

 */
    private static List<Integer> summation(int[] arr){
        int step = 0;
        int numOfItems = 1;
        List<Integer> output = new ArrayList<>();
        while (step < arr.length) {
            int sum = 0;
            for(int i = step; i < step + numOfItems; i++) {
                if(i < arr.length)
                    sum += arr[i];
            }
            output.add(sum);
            step += numOfItems;
            numOfItems++;
        }
        return output;
    }

    private static List<String> permute(String str){
        //String permute(str, 0, str.length() - 1);
        List<String> stringList = new ArrayList<>();
        permute(str, 0, str.length() - 1, stringList);
        return stringList;
    }

    private static void permute(String str, int i, int j, List<String> stringList) {
        if(i == j) {
            stringList.add(str);
            return;
        }
        else {
            for (int k = i; k <= j; k++) {
                str = swap(str, i, k);
                permute(str, i+1, j, stringList);
                str = swap(str, i, k);
            }
        }
    }

    private static String swap(String s, int i, int j) {
        char temp;
        char[] charArray = s.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    /*
    Longest Increasing Subsequence
    Input  : arr[] = {3, 10, 2, 1, 20}
    Output : Length of LIS = 3
    The longest increasing subsequence is 3, 10, 20

    Input  : arr[] = {3, 2}
    Output : Length of LIS = 1
    The longest increasing subsequences are {3} and {2}

    Input : arr[] = {50, 3, 10, 7, 40, 80}
    Output : Length of LIS = 4
    The longest increasing subsequence is {3, 7, 40, 80}
     */
    private static int longestIncreasingSubsequence(int[] arr) {
        int longest = 1;
        if(arr.length <= 1)
            return arr.length;
        int countItems = 1;
        for(int i = 0; i < arr.length - longest; i++) {
            System.out.print(arr[i]);
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[j] > arr[j-1]) {
                    countItems++;
                    System.out.print(" " + arr[j]);
                }
            }
            if(countItems > longest)
                longest = countItems;
            countItems = 1;
            System.out.println();
        }
        return longest;
    }

    private static int solution(String S) {
        if(S.length() <= 3){
            if(S.indexOf("X") >= 0)
                return 1;
            else
                return 0;
        }
        int rs = 0;
        String sub = "";
        for(int i = 0; i < S.length(); i++){
            sub = sub + S.charAt(i);
            if(sub.length() == 3) {
                if(sub.indexOf("X") >= 0)
                    rs++;
                sub = "";
            }
        }
        return rs;
    }

    private static int reverse(int[] A){
        if(A.length <= 1)
            return 0;
        int head = 0, tail = 0;
        int [] H = Arrays.copyOf(A, A.length);
        if(H[0] == 0)
            H[0] = 1;
        else
            H[0] = 0;
        for(int j = 1; j < H.length; j++){
            if(H[j] == H[j-1]){
                if(H[j] == 0)
                    H[j] = 1;
                else
                    H[j] = 0;
                head++;
            }
        }

        for(int j = 1; j < A.length; j++){
            if(A[j] == A[j-1]){
                if(A[j] == 0)
                    A[j] = 1;
                else
                    A[j] = 0;
                tail++;
            }
        }
        return Math.min(head, tail);
    }

    private static boolean isPalindrome(String s) {
        String clean = s.replaceAll("\\s+", "").toLowerCase();
        int forward = 0;
        int backward = clean.length() - 1;
        while (backward >= forward){
            if(clean.charAt(forward++) != clean.charAt(backward--))
                return false;
        }
        return true;
    }

    private static int prev = Integer.MIN_VALUE;
    private static boolean isBST(TreeNode root) {
        if(root != null) {
            if(!isBST(root.left))
                return false;
            if(root.val <= prev)
                return false;
            prev = root.val;
            return isBST(root.right);
        }
        return true;
    }

    private static int bstMaxVal(TreeNode root) {
        TreeNode node = root;
        while (node.right != null)
            node = node.right;
        return node.val;
    }

    private static void reverseLinkList(Node root) {
        Node p = null;
        Node n = root;
        while (n != null) {
            Node t = n;
            n = n.next;
            t.next = p;
            p = t;
        }
    }

    // function to sort hashmap by values
    private static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static int findNumbers(int[] nums) {
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(String.valueOf(nums[i]).length() % 2 == 0)
                count++;
        }
        return count;
    }

    public static void duplicateZeros(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) {
                for(int j = arr.length - 1; j > i;j--){
                    arr[j] = arr[j-1];
                }
                i += 1;
            }
        }
    }

    public static void printTreeLineByLine(TreeNode root){
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while (deque.size() > 0) {
            int size = deque.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = deque.poll();
                System.out.print(node.val);
                if(node.left != null)
                    deque.offer(node.left);
                if(node.right != null)
                    deque.offer(node.right);
            }
            System.out.println();
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> rs = new ArrayList<>();
        if(root == null)
            return rs;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(queue.size() > 0) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null)
                    queue.offer(node.left);
                if(node.right != null)
                    queue.offer(node.right);
            }
            rs.add(list);
        }
        return rs;
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return list;
        preorderTraversal(root, list);
        return list;
    }

    public static void preorderTraversal(TreeNode root, List<Integer> list) {
        list.add(root.val);
        if(root.left != null)
            preorderTraversal(root.left, list);
        if(root.right != null)
            preorderTraversal(root.right, list);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return list;
        inorderTraversal(root, list);
        return list;
    }

    private static void inorderTraversal(TreeNode root, List<Integer> list){
        if(root.left != null)
            inorderTraversal(root.left, list);
        list.add(root.val);
        if(root.right != null)
            inorderTraversal(root.right, list);
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return list;
        postorderTraversal(root, list);
        return list;
    }

    private static void postorderTraversal(TreeNode root, List<Integer> list) {
        if(root.left != null)
            postorderTraversal(root.left, list);
        if(root.right != null)
            postorderTraversal(root.right, list);
        list.add(root.val);
    }

    public static int maxDepth(TreeNode root) {
        if(root != null)
            maxDepth(root, 1);
        return max;
    }
    static int max = 0;
    public static void maxDepth(TreeNode root, int depth) {
        if(root.left == null && root.right == null)
            max = Math.max(max, depth);
        if(root.left != null)
            maxDepth(root.left, depth + 1);
        if(root.right != null)
            maxDepth(root.right, depth + 1);
    }

    public static boolean isSymmetric(TreeNode root) {
        if(root == null || (root.left == null && root.right == null))
            return true;
        return compare(root.left, root.right);
    }
    public static boolean compare(TreeNode left, TreeNode right) {
        if(left == null && right == null)
            return true;
        if(left == null && right != null)
            return false;
        if(left != null && right == null)
            return false;
        if(left.val != right.val)
            return false;
        return compare(left.left, right.right) && compare(left.right, right.left);
    }
    public static boolean compareIterate(TreeNode left, TreeNode right) {
        List<TreeNode> list = new ArrayList<>();
        if(left != null)
            list.add(left);
        if(right != null)
            list.add(right);
        while(list.size() > 0) {
            int size = list.size();
            if(size % 2 == 1)
                return false;
            for(int i = 0; i < size / 2; i++) {
                TreeNode nodeLeft = list.get(i);
                TreeNode nodeRight = list.get(size - i - 1);
                if(nodeLeft != null && nodeRight != null && nodeLeft.val != nodeRight.val)
                    return false;
                if(nodeLeft == null & nodeRight != null)
                    return false;
                if(nodeLeft != null & nodeRight == null)
                    return false;
            }
            for(int i = 0; i < size; i++) {
                TreeNode n = list.get(0);
                if(n != null) {
                    list.add(n.left);
                    list.add(n.right);
                }
                list.remove(0);
            }
        }
        return true;
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null)
            return false;
        sumPath(root, 0, targetSum);
        return result;
    }
    static boolean result = false;
    public static void sumPath(TreeNode root, int currSum, int targetSum) {
        if(root.left == null && root.right == null) {
            if(root.val + currSum == targetSum) {
                result = true;
            }
            return;
        }
        if(root.left != null)
            sumPath(root.left, currSum + root.val, targetSum);
        if(root.right != null)
            sumPath(root.right, currSum + root.val, targetSum);
    }

    static boolean camelCaseSeparation(String[] words, String variableName) {
        return camelCaseSeparation(words, variableName, true);
    }

    static boolean camelCaseSeparation(String[] words, String variableName, boolean isPrefix) {
        if(variableName.isEmpty())
            return true;
        String prefix = "";
        for(int i = 0; i < words.length; i++){
            if(words[i].length() > variableName.length())
                continue;
            String s = variableName.substring(0, words[i].length());
            if(isPrefix){
                if(s.toLowerCase().compareTo(words[i]) == 0){
                    prefix = words[i];
                    isPrefix = false;
                    break;
                }
            } else {
                if(s.compareTo(words[i].substring(0,1).toUpperCase() + words[i].substring(1)) == 0){
                    prefix = words[i];
                    break;
                }
            }
        }
        if(!prefix.isEmpty()) {
            variableName = variableName.substring(prefix.length());
            return camelCaseSeparation(words, variableName, isPrefix);
        }
        return false;
    }

    static long sumsDivisibleByK(int[] a, int k){
        HashSet<Integer> htb = new HashSet<>();
        long count = 0;
        for(int i = 0; i < a.length; i++){
            int item = a[i];
            if(a[i] >= k)
                item = a[i] % k;
            if(item == 0 && htb.contains(0))
                count++;
            else if(htb.contains(k - item))
                count++;
            if(!htb.contains(item))
                htb.add(item);
        }
        return count;
    }
}

/**
 * Definition for a binary tree node.
 * */
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

class Node {

    int data;
    Node next;

    Node(int d)
    {
        data = d;
        next = null;
    }
}