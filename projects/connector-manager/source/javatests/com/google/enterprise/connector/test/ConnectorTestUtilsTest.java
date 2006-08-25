// Copyright (C) 2006 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.enterprise.connector.test;

import com.google.enterprise.connector.mock.MockRepositoryPropertyTest;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.logging.Logger;

public class ConnectorTestUtilsTest extends TestCase {
  private static final Logger logger = 
    Logger.getLogger(MockRepositoryPropertyTest.class.getName());

  String expectedContents = "now is the time for all \n"
    + "good men to come to the aid \n" + "of their country \n";

  public void testStreamToString() {
    String testString = "now is the time for all \n"
      + "good men to come to the aid \n" + "# this is a comment \n"
      + "of their country // wow!";
    // System.out.println(testString);
    StringReader sr = new StringReader(testString);
    BufferedReader br = new BufferedReader(sr);
    String contents = ConnectorTestUtils.streamToString(br);
    // System.out.println(contents);
    // System.out.println("length of contents " + contents.length());
    // System.out.println(expectedContents);
    // System.out.println("length of expected contents " +
    // expectedContents.length());
    Assert.assertTrue(expectedContents.equals(contents));
  }

  public void testStreamToStringFromFile() {
    String contents = ConnectorTestUtils.
      streamToString("/com/google/enterprise/connector/testdata/testFile1.txt");
    logger.info(contents);
    logger.info("length of contents " + contents.length());
    String expectedContents = "now is the time for all \n"
      + "good men to come to the aid \n" + "of their country \n";
    logger.info(expectedContents);
    logger.info("length of expected contents "
      + expectedContents.length());
    Assert.assertTrue(expectedContents.equals(contents));
  }
}