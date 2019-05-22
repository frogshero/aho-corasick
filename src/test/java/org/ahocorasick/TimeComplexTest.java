package org.ahocorasick;

import org.junit.Test;

public class TimeComplexTest {


  //3logN
  void log(int n){
    for(int i=1; i<n; i*=2){
      System.out.println("1");
      System.out.println("2");
      System.out.println("3");
    }
  }

  @Test
  public void test() {
    log(8);
  }
}
