package com.baykalsoft.debtrack.entity;


import com.baykalsoft.debtrack.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime paymentDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime postedDate;

  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @NonNull
  private long debtor;


  @ManyToOne(fetch = FetchType.EAGER)
  @Cascade(CascadeType.ALL)
  private User agent;

  @Column(length = 2000)
  private String description;

  @Digits(integer=2, fraction=2)
  private BigDecimal toAgency;

  @Digits(integer=2, fraction=2)
  private BigDecimal toClient;

  @Digits(integer=2, fraction=2)
  private BigDecimal comission;

  @Digits(integer=2, fraction=2)
  private BigDecimal taxes;

  @Digits(integer=2, fraction=2)
  private BigDecimal clientReturn;

}