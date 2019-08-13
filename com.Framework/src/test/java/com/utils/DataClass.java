package com.utils;

import org.testng.annotations.DataProvider;

public class DataClass {

  @DataProvider
  public static Object[][] GetdataSource() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
}
