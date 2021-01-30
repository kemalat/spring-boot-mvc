package com.baykalsoft.debtrack.entity;


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(indexes = { @Index(name = "idx_client_id", columnList = "clientId") })
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientReport {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(nullable = false)
  private int clientId;

//  @Digits(integer=4, fraction=2)
  @Column(columnDefinition="Decimal(6,2) default '0.00'")
  private BigDecimal principal;

  @Column(columnDefinition="Decimal(6,2) default '0.00'")
  private BigDecimal fees;

  @Column(columnDefinition="Decimal(6,2) default '0.00'")
  private BigDecimal legalFees;

  @Column(columnDefinition="Decimal(6,2) default '0.00'")
  private BigDecimal owingActive;

  @Column(columnDefinition="Decimal(6,2) default '0.00'")
  private BigDecimal owingClosed;

  @Column(columnDefinition="Decimal(6,2) default '0.00'")
  private BigDecimal interest;



}