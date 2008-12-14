// Copyright 2008 Google Inc.  All Rights Reserved.
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

package com.google.enterprise.common;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/**
 * Useful utilities for writing servlets.
 */
public abstract class ServletBase extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected static final DateTimeFormatter dtFormat =
      DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'");
  
  public static String httpDateString() {
    return dtFormat.print((new DateTime()).withZone(DateTimeZone.UTC));
  }

  public static PrintWriter initNormalResponse(HttpServletResponse response) throws IOException {
    initResponse(response);
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    response.setBufferSize(0x1000);
    return response.getWriter();
  }

  public static void initErrorResponse(HttpServletResponse response, int code)
      throws IOException {
    initResponse(response);
    response.sendError(code);
  }

  public static void initResponse(HttpServletResponse response) {
    response.addHeader("Date", httpDateString());
  }
}