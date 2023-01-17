package com.scalesec.vulnado;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.errorprone.annotations.Var;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
  public static String run(String input) {
    var processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + input + "'";
    System.out.println(cmd);
    processBuilder.command("bash", "-c", cmd);

    var output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      var reader = new BufferedReader(new InputStreamReader(process.getInputStream(), UTF_8));

      @Var String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return output.toString();
  }
}
