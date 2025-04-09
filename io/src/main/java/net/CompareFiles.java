package net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CompareFiles {

  public static void main(String[] args) {
    List<Set<String>> diffs = readDiffToSets("diff.txt");
    Map<String, String> map1 = new HashMap<>();
    diffs.get(0).forEach(s -> {
      String[] splits = s.split(",");
      //      computeIfAbsent
      //      map1.put(splits[2], );
    });
    Map<String, String> map2 = new HashMap<>();
    diffs.get(1).forEach(s -> {
      String[] splits = s.split(",");
      map2.put(splits[2], s);
    });
    map1.forEach((k, v) -> {
      System.out.println(k);
      System.out.println(v);
    });

  }

  private static void compare() {
    Set<String> set1 = readFileToSet("output1.txt");
    Set<String> set2 = readFileToSet("output3.txt");

    Set<String> difference = new HashSet<>(set1);
    difference.removeAll(set2);

    System.out.println("Differences 1-2:");
    for (String line : difference) {
      System.out.println(line);
    }

    Set<String> difference2 = new HashSet<>(set2);
    difference2.removeAll(set1);
    System.out.println("Differences 2-1:");
    for (String line : difference2) {
      System.out.println(line);
    }
  }

  private static Set<String> readFileToSet(String fileName) {
    Set<String> lines = new HashSet<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (!line.trim().isEmpty() && !line.startsWith("requestUrl")) {
          lines.add(line);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lines;
  }

  private static List<Set<String>> readDiffToSets(String fileName) {
    Set<String> lines1 = new HashSet<>();
    Set<String> lines2 = new HashSet<>();
    boolean first = true;
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (!line.trim().isEmpty() && line.startsWith("Differences 2-1")) {
          first = false;
        }
        if (!line.trim().isEmpty() && !line.startsWith("Differences")) {
          if (first) {
            lines1.add(line);
          } else {
            lines2.add(line);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return List.of(lines1, lines2);
  }

}