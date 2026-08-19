package com.example.interview;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.interview.algorithm.AlgorithmPractice;
import org.junit.jupiter.api.Test;

class AlgorithmPracticeTests {

    @Test
    void mergeSortedArraysReturnsSortedResult() {
        int[] result = AlgorithmPractice.mergeSortedArrays(
                new int[] {1, 3, 5},
                new int[] {2, 4, 6});

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void mergeIntoFirstArrayUsesExistingSpace() {
        int[] target = {1, 3, 5, 0, 0, 0};

        AlgorithmPractice.mergeIntoFirstArray(target, 3, new int[] {2, 4, 6}, 3);

        assertThat(target).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void maxProfitOneTransactionBuysBeforeSelling() {
        int profit = AlgorithmPractice.maxProfitOneTransaction(new int[] {7, 1, 5, 3, 6, 4});

        assertThat(profit).isEqualTo(5);
    }

    @Test
    void maxProfitManyTransactionsAddsEveryAscendingDiff() {
        int profit = AlgorithmPractice.maxProfitManyTransactions(new int[] {7, 1, 5, 3, 6, 4});

        assertThat(profit).isEqualTo(7);
    }

    @Test
    void twoSumSortedFindsPairWithTargetSum() {
        int[] pair = AlgorithmPractice.twoSumSorted(new int[] {1, 2, 3, 4, 6}, 6);

        assertThat(pair).containsExactly(2, 4);
    }

    @Test
    void threeSumSortedFindsUniqueTriples() {
        List<int[]> triples = AlgorithmPractice.threeSumSorted(new int[] {1, 2, 3, 4, 5, 6}, 10);

        assertThat(AlgorithmPractice.formatTriples(triples))
                .containsExactly("[1, 3, 6]", "[1, 4, 5]", "[2, 3, 5]");
    }

    @Test
    void palindromeChecksBothTrueAndFalseCases() {
        assertThat(AlgorithmPractice.isPalindrome("radar")).isTrue();
        assertThat(AlgorithmPractice.isPalindrome("hello")).isFalse();
    }

    @Test
    void countPalindromicSubstringsExpandsAroundCenters() {
        int count = AlgorithmPractice.countPalindromicSubstrings("aaa");

        assertThat(count).isEqualTo(6);
    }
}
