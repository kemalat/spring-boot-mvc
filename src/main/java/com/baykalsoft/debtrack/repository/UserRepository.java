package com.baykalsoft.debtrack.repository;

import com.baykalsoft.debtrack.dto.DataTableFilterDTO;
import com.baykalsoft.debtrack.entity.User;
import com.baykalsoft.debtrack.enums.UserStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Ramesh Fadatare
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>
{


  @Transactional
  @Query(value = "INSERT INTO user_role(user_id,role_id) VALUES (:userId, :roleId)", nativeQuery = true)
  @Modifying
  int insertUserRole(@Param("userId") int userId, @Param("roleId") int roleId);

  Optional<User> findByEmail(String email);
  List<User> findByStatus(UserStatus status);

  @Query(value = "select * from users limit ?#{#filter.length} offset ?#{#filter.start}",nativeQuery = true)
  List<User> findUsers(DataTableFilterDTO filter);

  @Query(value = "select count(id) from users",nativeQuery = true)
  public Long getColumnCount();

  @Transactional
  @Query(value = "select client_id from user_client where user_id = ?}", nativeQuery = true)
  List<Integer> findClients(@Param("userId") int userId);


//  @Query(value = "select count(id) from users inner join user_role on users.id = user_role.user_id where role_id <> 1 ",nativeQuery = true)

//  @Query(value = "select * from users inner join user_role on users.id = user_role.user_id where role_id <> 1 and "
//      + "users.id > ?#{#filter.start} order by ?#{#filter.orderColumn} limit ?#{#filter.length}",
//      countQuery = "SELECT count(*) FROM users WHERE role_id <> 1",nativeQuery = true)


}
