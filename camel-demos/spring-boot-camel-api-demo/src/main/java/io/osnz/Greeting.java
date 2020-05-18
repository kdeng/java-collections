package io.osnz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Greeting {
  Integer id;
  String name;
}
