package com.baykalsoft.debtrack.entity;


import com.baykalsoft.debtrack.enums.ComissionItem;
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

@Entity
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comission {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int percent;
  @Enumerated(EnumType.ORDINAL)
  private ComissionItem comissionItem;




}