package com.baykalsoft.debtrack.entity;

import com.baykalsoft.debtrack.enums.UserStatus;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Ramesh Fadatare
 *
 */
@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name="users")
public class User
{
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable=false)
  private String name;

  @Column(nullable=false, unique=true)
  @Email(message="{errors.invalid_email}")
  private String email;

  @Column(nullable=false)
  @Size(min=4)
  private String password;

  @Column(nullable=false)
  private String photo;

  @Enumerated(EnumType.ORDINAL)
  private UserStatus status;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdDate;


  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      })
  @JoinTable(
      name="user_role",
      joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
      inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
  private List<Role> roles;

  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      })
  @JoinTable(
      name="user_client",
      joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
      inverseJoinColumns={@JoinColumn(name="CLIENT_ID", referencedColumnName="ID")})
  private List<Client> clients;

  public Integer getId()
  {
    return id;
  }
  public void setId(Integer id)
  {
    this.id = id;
  }
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public String getEmail()
  {
    return email;
  }
  public void setEmail(String email)
  {
    this.email = email;
  }
  public String getPassword()
  {
    return password;
  }
  public void setPassword(String password)
  {
    this.password = password;
  }
  public List<Role> getRoles()
  {
    return roles;
  }
  public void setRoles(List<Role> roles)
  {
    this.roles = roles;
  }

  public List<Client> getClients() {
    return clients;
  }

  public void setClients(List<Client> clients) {
    this.clients = clients;
  }

  public void setStatus(UserStatus status) {
    this.status = status;
  }
}
