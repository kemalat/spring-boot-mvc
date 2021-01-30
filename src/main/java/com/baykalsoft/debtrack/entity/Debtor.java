package com.baykalsoft.debtrack.entity;


import com.baykalsoft.debtrack.enums.DebtorStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Debtor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private String address;
  private String phone;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime closedDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime lastPaymentDate;

  @Enumerated(EnumType.STRING)
  private DebtorStatus status;

  @ManyToOne(fetch = FetchType.EAGER)
  @Cascade(CascadeType.ALL)
  private User agent;

  @Digits(integer=4, fraction=2)
  private BigDecimal principal;

  @Digits(integer=4, fraction=2)
  private BigDecimal interest;

  @Digits(integer=4, fraction=2)
  private BigDecimal fees;

  @Digits(integer=4, fraction=2)
  private BigDecimal legalFees;

  @Digits(integer=4, fraction=2)
  private BigDecimal paid;

  @Digits(integer=4, fraction=2)
  private BigDecimal owing;

  @NonNull
  private long clientId;


  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "debtor", orphanRemoval = true)
  @Cascade(CascadeType.ALL)
  @Size(max=10)
  private List<Transaction> transactions;


}