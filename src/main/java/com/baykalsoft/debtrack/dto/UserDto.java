package com.baykalsoft.debtrack.dto;

import com.baykalsoft.debtrack.enums.UserStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = false)
public class UserDto {

  private long id;
  private String email;
  private LocalDateTime created;
  private UserStatus status;
  private String name;
  private List<RoleDto> roles;

}
