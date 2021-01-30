package com.baykalsoft.debtrack.assembler;


import com.baykalsoft.debtrack.dto.RoleDto;
import com.baykalsoft.debtrack.dto.UserDetailDto;
import com.baykalsoft.debtrack.dto.UserDto;
import com.baykalsoft.debtrack.entity.Role;
import com.baykalsoft.debtrack.entity.User;
import com.baykalsoft.debtrack.enums.ActionPlan;
import com.baykalsoft.debtrack.enums.UserRole;
import com.baykalsoft.debtrack.enums.UserStatus;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAssembler {

  private final Logger logger = LoggerFactory.getLogger(UserAssembler.class);

  public UserDto toUserDto(User user) {
    List<RoleDto> roleDtos = user.getRoles().stream().map(this::toRoleDto).collect(Collectors.toList());
    return UserDto.of(user.getId(),user.getEmail(),user.getCreatedDate(),user.getStatus(),user.getName(),roleDtos);

  }

  private RoleDto toRoleDto(Role role) {
    return RoleDto.of(role.getName());
  }

  public UserDetailDto toDetailDto(User user, List<String> clients) {
    List<String> statutes = Arrays.stream(UserStatus.values()).map(userStatus -> userStatus.name()).collect(Collectors.toList());
    List<RoleDto> roleDtos = user.getRoles().stream().map(this::toRoleDto).collect(Collectors.toList());
    List<String> roles = Arrays.stream(UserRole.values()).map(userRole -> userRole.name()).collect(Collectors.toList());
    List<String> actionPlans = Arrays.stream(ActionPlan.values()).map(actionPlan -> actionPlan.name()).collect(Collectors.toList());

    return UserDetailDto.of(user.getId(),user.getCreatedDate(),user.getStatus(),user.getName(),user.getEmail(),clients,
        statutes,roleDtos,roles,actionPlans);

  }
}