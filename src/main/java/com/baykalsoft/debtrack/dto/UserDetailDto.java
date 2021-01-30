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
public class UserDetailDto {

  private long userId;
  private LocalDateTime createdDate;
  private UserStatus status;
  private String name;
  private String email;
  private List<String> clients;
  private List<String> userStatutes;
  private List<RoleDto> roleDto;
  private List<String> roles;
  private List<String> actionPlans;

}
