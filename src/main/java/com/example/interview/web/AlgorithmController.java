package com.example.interview.web;

import java.util.Arrays;

import com.example.interview.algorithm.AlgorithmPractice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlgorithmController {

    @GetMapping("/algorithms")
    public String algorithms(Model model) {
        int[] first = {1, 3, 5};
        int[] second = {2, 4, 6};
        int[] mergeTarget = {1, 3, 5, 0, 0, 0};
        AlgorithmPractice.mergeIntoFirstArray(mergeTarget, 3, second, 3);

        int[] prices = {7, 1, 5, 3, 6, 4};
        int[] numbers = {1, 2, 3, 4, 6};
        int[] tripleNumbers = {1, 2, 3, 4, 5, 6};

        model.addAttribute("mergeInput", "A = " + Arrays.toString(first) + ", B = " + Arrays.toString(second));
        model.addAttribute("mergeOutput", AlgorithmPractice.format(AlgorithmPractice.mergeSortedArrays(first, second)));
        model.addAttribute("mergeInPlaceOutput", AlgorithmPractice.format(mergeTarget));
        model.addAttribute("oneProfit", AlgorithmPractice.maxProfitOneTransaction(prices));
        model.addAttribute("manyProfit", AlgorithmPractice.maxProfitManyTransactions(prices));
        model.addAttribute("twoSum", AlgorithmPractice.format(AlgorithmPractice.twoSumSorted(numbers, 6)));
        model.addAttribute("threeSum", AlgorithmPractice.formatTriples(AlgorithmPractice.threeSumSorted(tripleNumbers, 10)));
        model.addAttribute("radarPalindrome", AlgorithmPractice.isPalindrome("radar"));
        model.addAttribute("helloPalindrome", AlgorithmPractice.isPalindrome("hello"));
        model.addAttribute("palindromeCount", AlgorithmPractice.countPalindromicSubstrings("aaa"));
        return "algorithms";
    }
}
