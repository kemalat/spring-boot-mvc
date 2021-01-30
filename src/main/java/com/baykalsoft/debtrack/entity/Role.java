package com.baykalsoft.debtrack.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

/**
 * @author Ramesh Fadatare
 *
 */
@Entity
@Table(name="roles")
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class Role
{
  @Id @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  @Column(nullable=false, unique=true)
  @NaturalId
  private String name;

  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },mappedBy="roles")
  private List<User> users;

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

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

}