package com.baykalsoft.debtrack.entity;


import com.baykalsoft.debtrack.enums.ContactRole;
import com.baykalsoft.debtrack.enums.ContactStatus;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String fullName;
  private String email;
  private String phone;


  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime updatedDate;


  @Enumerated(EnumType.STRING)
  private ContactStatus status;

  @Enumerated(EnumType.STRING)
  private ContactRole role;


}