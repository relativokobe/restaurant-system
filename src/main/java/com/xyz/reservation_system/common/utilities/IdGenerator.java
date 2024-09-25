package com.xyz.reservation_system.common.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdGenerator {
  private IdGenerator() {}

  /**
   * Used to generate a unique ID with a prefix a long with the next value of the primary key
   *
   * @param prefix the prefix othe unique id
   * @param id the next value of the primary key
   * @return the generated unique ID
   */
  public static String generateIdWithPrefix(String prefix, Long id) {
    return generateAsPrefixDateId(prefix, String.format("%09d", id));
  }

  /**
   * Used to generate a unique ID with a prefix a long with the next value of the primary key
   *
   * @param prefix the prefix othe unique id
   * @param id the next value of the primary key
   * @return the generated unique ID
   */
  private static String generateAsPrefixDateId(String prefix, String id) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyy");
    String formattedDate = dateFormat.format(new Date());
    String prefixDate = prefix.trim() + formattedDate;
    return prefixDate + "-" + id.trim();
  }
}
