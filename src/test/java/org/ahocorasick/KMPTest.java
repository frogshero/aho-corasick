package org.ahocorasick;

import org.junit.Test;

import java.util.Arrays;

public class KMPTest {

  //时间复杂度最坏O(n*n),最好O(n)
  private int findMaxPostfix(char[] arr, int endIdx) {
    int postfixStart = 1;  //real postfix
    while (postfixStart <= endIdx) {
      int i = 0;
      boolean found = false;
      //尝试匹配后缀，不行就短一个字符继续匹配
      while (postfixStart + i + 1 <= endIdx
             && arr[i + 1] == arr[postfixStart + i + 1]) {
        i++;
        found = true;
      }
      if (found && postfixStart + i == endIdx) {
        //找到后缀
        return i + 1;
      }
      postfixStart++;
    }
    return 0;
  }

  private static String PATTERN = "abcabeabcabw";


  private int search(String src, String p) {
    int[] patternArr = getPatternArr(p);
    int i = 0;
    int j = 0;
    while (i < src.length()) {
      if (src.charAt(i) == p.charAt(j)) {
        i++;
        j++;
        if (j == p.length()) {
          return i - j;
        }
      } else {
        j = patternArr[j];
        if (j == 0) {
          //等于0就是从头开始匹配
          i++;
        }
      }
    }
    return -1;
  }

  //复杂度为最坏O(n*n*n)，最好O(n*n)
  private int[] getPatternArr(String s) {
    char[] arr = s.toCharArray();
    int[] backIdx = new int[arr.length];
    int pos = 1;
    backIdx[0] = 0;
    System.out.println(-1);
    //求当前位置pos的最大后缀
    while (pos < arr.length) {
      int postfixStart = findMaxPostfix(arr, pos);
      backIdx[pos] = postfixStart;
      pos++;
    }
    System.out.println(Arrays.toString(backIdx));

    //只有匹配失效的字符才需要后退
    for (int i = backIdx.length - 1; i > 0; i--) {
      if (backIdx[i] == 0) {
        if (backIdx[i - 1] > 0) {
          backIdx[i] = backIdx[i - 1];
        }
      } else {
        backIdx[i] = 0;
      }
    }
    System.out.println(Arrays.toString(backIdx));
    return backIdx;
  }

  @Test
  public void test() {
    System.out.println(search("abcabe" + PATTERN, "caa"));
    //System.out.println(Arrays.toString(getPatternArr(PATTERN)));
  }
}
