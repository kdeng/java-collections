package nz.net.osnz.demos.jersey;

import java.io.Serializable;

@lombok.Value
@lombok.Builder
@lombok.RequiredArgsConstructor
public class User implements Serializable {

  String name;

  String greeting;

}
