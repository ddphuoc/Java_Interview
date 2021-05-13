package personal.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketPermission;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.Permission;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

class Solution {

    public static void main(String[] args)  {
        //System.out.println(isPalindrome("race Car"));
//        System.out.println(permute("abc"));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(getAllPaths(root));
        //System.out.println(camelCaseSeparation(new String[]{"is","valid","right"}, "isValid"));
        //System.out.println(sumsDivisibleByK(new int[]{0,2,3,4,6}, 3));
        //System.out.println(merge(new int[]{1,4,7,8,0,0,0}, 4, new int[]{1,2,3}, 3));
//        System.out.println(countChars("aabbbaaacccccddcc"));
//        System.out.println(trippleSum(new int[]{1, 4, 7, 8, 2, 5, 3}));
//        URL oracle = new URL("http://www.google.com");
//        URLConnection connection = oracle.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        String inputLine;
//        while((inputLine = in.readLine()) != null){
//            System.out.println(inputLine);
//        }
//        in.close();
//        List<String> anagrams = new ArrayList<>();
//        anagrams.add("Tar");
//        anagrams.add("Rat");
//        anagrams.add("Art");
//        anagrams.add("Bat");
//        anagrams.add("tab");
//        System.out.println(groupAnagrams(anagrams));
        String s = "aabbbcdef";
        int count = 0;
        Stream<String> characterStream = s.chars().mapToObj(c -> String.valueOf(c));
        String rs = characterStream.reduce("", (c1, c2) -> "" + String.valueOf(c1) + String.valueOf(c2));
//        characterStream.forEach(c -> {
//            if(outputStr.length() == 0) {
//                outputStr.append(c);
//                charCount++;
//            }
//            else if(outputStr.lastIndexOf(c.toString()) == outputStr.length() - 1){
//                charCount++;
//            }
//            else {
//                outputStr.append(charCount);
//                outputStr.append(c);
//                charCount = 1;
//            }
//            if(pos == s.length() - 1){
//                outputStr.append(charCount);
//            }
//            pos++;
//        });
//        System.out.println(outputStr.toString());
//        System.out.println(getSum("23+++12#$#$1234;/.,10"));
        System.out.println(rs);
    }
    static int charCount = 0;
    static int pos = 0;
    static StringBuilder outputStr = new StringBuilder();
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
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
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

    /*
    arr1 = {1, 4, 7, 8, 0, 0, 0} --> m items are not zero, arr1 size = m + n
	arr2 = {2, 4, 6} --> size is n
	-> Merge arr2 into arr1, use in place
     */
    static int[] merge(int[] arr1, int m, int[] arr2, int n) {
        int i = m - 1;
        int j = n - 1;
        int pos = arr1.length - 1;
        while(i >= 0 || j >= 0) {
            if(i < 0) {
                arr1[pos] = arr2[j];
                j--;
            }
            else if(j < 0) {
                arr1[pos] = arr1[i];
                i--;
            }
            else if(arr1[i] > arr2[j]) {
                arr1[pos] = arr1[i];
                i--;
            }
            else {
                arr1[pos] = arr2[j];
                j--;
            }
            pos--;
        }
        return arr1;
    }

    /*
    String: aabbbaaacccccdcc, count by 1 if character repeat sequence more than 1
	-> 	a: 2
		b: 1
		c: 2
     */
    static HashMap<Character, Integer> countChars(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        if(s.length() <= 1)
            return hashMap;
        int i = 0;
        int j = 1;
        while(i < s.length() - 1){
            Character c = s.charAt(i);
            if(j <= s.length() - 1 && c == s.charAt(j)){
                j++;
            } else {
                if(j - i >= 2) {
                    if(hashMap.containsKey(c))
                        hashMap.replace(c, hashMap.get(c) + 1);
                    else
                        hashMap.put(c, 1);
                }
                i = j;
                j = i + 1;
            }
        }
        return hashMap;
    }

    /*
    TwoSum: arr1 = {1, 4, 7, 8, 2, 5, 3}, target = 10 => (7, 3), (8, 2)
     */
    static List<List<Integer>> twoSum(int[] arr1, int target){
        List<List<Integer>> list = new ArrayList<>();
        if(arr1 == null)
            return null;
        HashSet<Integer> hashSet = new HashSet<>();
        for(int i = 0; i < arr1.length; i++) {
            if(hashSet.contains(target - arr1[i])){
                List<Integer> l = new ArrayList<>();
                l.add(target - arr1[i]);
                l.add(arr1[i]);
                list.add(l);
            }
            if(!hashSet.contains(arr1[i]))
                hashSet.add(arr1[i]);
        }
        return list;
    }

    /*
    TrippleSum
	arr1 = {1, 4, 7, 8, 2, 5, 3}
	-> (1, 4, 5), (1, 3, 4), (1, 7, 8) (2, 3, 5), etc.
     */
    static List<List<Integer>> trippleSum(int[] arr1){
        List<List<Integer>> list = new ArrayList<>();
        if(arr1 == null)
            return null;
        for(int i = 0; i < arr1.length; i++) {
            List<List<Integer>> l = twoSum2(arr1, i);
            if(l != null) {
                list.addAll(l);
            }
        }
        return list;
    }
    static List<List<Integer>> twoSum2(int[] arr1, int indexTarget){
        List<List<Integer>> list = new ArrayList<>();
        if(arr1 == null)
            return null;
        int target = arr1[indexTarget];
        HashSet<Integer> hashSet = new HashSet<>();
        for(int i = 0; i < arr1.length; i++) {
            if(i == indexTarget)
                continue;
            if(hashSet.contains(target - arr1[i])){
                List<Integer> l = new ArrayList<>();
                l.add(target - arr1[i]);
                l.add(arr1[i]);
                l.add(target);
                list.add(l);
            }
            if(!hashSet.contains(arr1[i]))
                hashSet.add(arr1[i]);
        }
        return list;
    }

    /*
    2 sorted LinkedList
	-> combine these two linkedList
     */
    static LinkedList<Integer> mergeLinkedList(LinkedList<Integer> linkedList1, LinkedList<Integer> linkedList2) {
        if(linkedList1 == null)
            return linkedList2;
        if(linkedList2 == null)
            return linkedList1;
        LinkedList<Integer> list = new LinkedList<>();
        while(linkedList1.size() > 0 || linkedList2.size() > 0) {
            if(linkedList1.size() == 0)
                list.add(linkedList2.poll());
            else if(linkedList2.size() == 0)
                list.add(linkedList1.poll());
            else if(linkedList1.peek() >= linkedList2.peek())
                list.add(linkedList2.poll());
            else
                list.add(linkedList1.poll());
        }
        return list;
    }

    public static char firstNonRepeated(String str) {
        Map<Character,Integer> counts = new LinkedHashMap<>(str.length());

        for (char c : str.toCharArray()) {
            counts.put(c, counts.containsKey(c) ? counts.get(c) + 1 : 1);
        }

        for (Map.Entry<Character,Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return Character.MIN_VALUE;
    }

    /*
    Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree,
     construct and return the binary tree.
     Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     Output: [3,9,20,null,null,15,7]
     */
    static HashMap<Integer, Integer> hashMap = new LinkedHashMap<>();
    static int post_idx;
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null)
            return null;
        for (int i = 0; i < inorder.length;i++){
            hashMap.put(inorder[i], i);
        }
        post_idx = postorder.length - 1;
        return buildTree(inorder, postorder, 0, postorder.length - 1);
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder, int in_left, int post_right) {
        if(in_left > post_right)
            return null;
        int root_val = postorder[post_idx--];
        TreeNode root = new TreeNode(root_val);
        int index = hashMap.get(root_val);
        root.right = buildTree(inorder, postorder, index + 1, post_right);
        root.left = buildTree(inorder, postorder, in_left, index - 1);
        return root;
    }

    /*
    Given two integer arrays preorder and inorder
    where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree,
    construct and return the binary tree.
    Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    Output: [3,9,20,null,null,15,7]
    */
    static int pre_index;
    public static TreeNode buildTree1(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null)
            return null;
        pre_index = 0;
        for(int i = 0; i < inorder.length; i++){
            hashMap.put(inorder[i], i);
        }
        return buildTree1(inorder, preorder, 0,0);
    }
    public static TreeNode buildTree1(int[] inorder, int[] preorder, int in_left, int in_right) {
        if(in_left > in_right)
            return null;
        int root_val = preorder[pre_index++];
        TreeNode root = new TreeNode(root_val);
        int index = hashMap.get(root_val);
        root.left = buildTree1(inorder, preorder, in_left, index - 1);
        root.right = buildTree1(inorder, preorder, index + 1, in_right);
        return root;
    }

    public static List<List<String>> groupAnagrams(List<String> strs) {
        if (strs.size() == 0) return new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toLowerCase().toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key))
                ans.put(key, new ArrayList<>());
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
    }

    public static List<List<Integer>>  getAllPaths(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        getAllPaths(results, path, root);
        return results;
    }
    public static void getAllPaths(List<List<Integer>> results, List<Integer> path, TreeNode root) {
        path.add(root.val);
        if(root.left == null && root.right == null) {
            results.add(path);
            return;
        }
        List<Integer> pLeft = new ArrayList<>(path);
        getAllPaths(results, pLeft, root.left);
        List<Integer> pRight = new ArrayList<>(path);
        getAllPaths(results, pRight, root.right);
    }

    public static int getSum(String str){
        int sum = 0;
        String num = "";
        for(int i = 0; i < str.length(); i++) {
            if(Character.isDigit(str.charAt(i))) {
                num += str.charAt(i);
            }
            else if(str.charAt(i) == '-' && num == ""){
                num += "-";
            }
            else {
                if(num.length() > 0 && num != "-") {
                    sum += Integer.parseInt(num);
                    num = "";
                }
            }
        }
        if(!num.isEmpty() && num != "-")
            sum += Integer.parseInt(num);
        return sum;
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

//interface A {
//    public void aaa();
//}
//abstract class AA implements A {
//    public void bbb(){}
//    class C extends AA implements A {
//        public void aaa(){
//            System.out.println("Hi");
//        }
//    }
//}
//public class Exa extends AA{
//    public void aaa(){
//        System.out.println("Bye");
//    }
//    public static void main(String args[]){
//        A a = new AA();
//        a.aaa();
//    }
//}

class DemoExample {
    public static void main(String[] args) {

    }
}

class ThreadDemo extends Thread {
    @Override
    public synchronized void start(){

    }
    @Override
    public void run(){

    }
}
