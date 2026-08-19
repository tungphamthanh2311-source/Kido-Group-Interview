package com.example.interview.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class AlgorithmPractice {

    private AlgorithmPractice() {
    }

    // Bai 1: Dung 2 con tro de tron 2 mang da sap xep thanh mang moi.
    // Do phuc tap: O(n + m), vi moi phan tu chi duoc doc 1 lan.
    public static int[] mergeSortedArrays(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    // Nang cao bai 1: Tron tu cuoi ve dau de khong ghi de du lieu con can doc trong target.
    // target co dang [phan_tu_A..., 0, 0, 0], trong do phan sau la cho trong.
    public static void mergeIntoFirstArray(int[] target, int targetSize, int[] source, int sourceSize) {
        int i = targetSize - 1;
        int j = sourceSize - 1;
        int k = targetSize + sourceSize - 1;

        while (j >= 0) {
            if (i >= 0 && target[i] > source[j]) {
                target[k--] = target[i--];
            } else {
                target[k--] = source[j--];
            }
        }
    }

    // Bai 2: Mua 1 lan, ban 1 lan trong tuong lai.
    // Luu gia nho nhat da gap, moi ngay thu tinh loi nhuan neu ban tai ngay do.
    public static int maxProfitOneTransaction(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }

    // Nang cao bai 2: Duoc mua ban nhieu lan, nhung chi giu 1 don vi vang tai mot thoi diem.
    // Cong tat ca khoang tang lien tiep tu ngay truoc sang ngay sau.
    public static int maxProfitManyTransactions(int[] prices) {
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }

        return profit;
    }

    // Bai 3: Mang da tang dan nen dung 2 con tro.
    // Tong nho hon target thi tang left, tong lon hon target thi giam right.
    public static int[] twoSumSorted(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] {numbers[left], numbers[right]};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[0];
    }

    // Nang cao bai 3: Co dinh 1 phan tu, sau do bai toan con lai tro thanh two-sum.
    // Bo qua phan tu trung de ket qua khong lap lai cung mot bo ba.
    public static List<int[]> threeSumSorted(int[] numbers, int target) {
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < numbers.length - 2; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = numbers.length - 1;

            while (left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];
                if (sum == target) {
                    result.add(new int[] {numbers[i], numbers[left], numbers[right]});
                    left++;
                    right--;
                    while (left < right && numbers[left] == numbers[left - 1]) {
                        left++;
                    }
                    while (left < right && numbers[right] == numbers[right + 1]) {
                        right--;
                    }
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    // Bai 4: So sanh tung cap ky tu tu 2 dau chuoi tien vao giua.
    public static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    // Nang cao bai 4: Moi palindrome deu co mot tam.
    // Mo rong quanh tung tam le va tam chan de dem tat ca chuoi con doi xung.
    public static int countPalindromicSubstrings(String text) {
        int count = 0;

        for (int center = 0; center < text.length(); center++) {
            count += countFromCenter(text, center, center);
            count += countFromCenter(text, center, center + 1);
        }

        return count;
    }

    // Dem so palindrome co cung mot tam bang cach mo rong left/right cho den khi khac ky tu.
    private static int countFromCenter(String text, int left, int right) {
        int count = 0;

        while (left >= 0 && right < text.length() && text.charAt(left) == text.charAt(right)) {
            count++;
            left--;
            right++;
        }

        return count;
    }

    public static String format(int[] values) {
        return Arrays.toString(values);
    }

    public static List<String> formatTriples(List<int[]> triples) {
        return triples.stream()
                .map(Arrays::toString)
                .toList();
    }
}
