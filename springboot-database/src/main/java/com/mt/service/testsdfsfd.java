package com.mt.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2026/3/17 19:25
 * @Version:
 * @Description:
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * You are not expected to understand this
 */
public class testsdfsfd {

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2, 5};
        List<Integer> special = findSpecialElements(arr);
        System.out.println("满足条件的元素: " + special);
    }


    public static List<Integer> findSpecialElements(int[] arr) {
        int n = arr.length;
        List<Integer> result = new ArrayList<>();
        if (n == 0) return result;
        int[] leftMax = new int[n];
        leftMax[0] = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i - 1]);
        }

        int[] rightMin = new int[n];
        rightMin[n - 1] = Integer.MAX_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], arr[i + 1]);
        }

        for (int i = 0; i < n; i++) {
            if (arr[i] > leftMax[i] && arr[i] < rightMin[i]) {
                result.add(arr[i]);
            }
        }
            return result;
    }
}
