package com.CK;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
//        char[][] matrix = {
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}};
        char[][] matrix = {{'0', '1', '1', '0', '1'}, {'1', '1', '0', '1', '0'}, {'0', '1', '1', '1', '0'}, {'1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '1'}, {'0', '0', '0', '0', '0'}};

        Solution solution = new Solution();
        System.out.println(solution.maximalRectangle(matrix));
    }
}

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] heights = new int[matrix[0].length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') heights[j] = 0;
                else heights[j]++;
            }
            max = Math.max(max, largestRectangleArea(heights));
        }

        return max;
    }

    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int currentIdx, left, right = 0, max = Integer.MIN_VALUE, area;
        for (int i = 0; i < heights.length; i++) {
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) stack.push(i);
            else {
                currentIdx = stack.pop();
                right = i;
                left = stack.isEmpty() ? -1 : stack.peek();
                area = heights[currentIdx] * (right - left - 1);
                max = Math.max(max, area);
                i--;
            }
        }
        right = stack.peek() + 1;
        while (!stack.isEmpty()) {
            currentIdx = stack.pop();
            left = stack.isEmpty() ? -1 : stack.peek();
            area = heights[currentIdx] * (right - left - 1);
            max = Math.max(max, area);
        }

        return max;
    }
}