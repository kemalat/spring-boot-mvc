package com.baykalsoft.debtrack.entity;

import com.baykalsoft.debtrack.enums.FeeParty;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(staticName = "need")
public class OptionalFee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToOne
  Client client;

  @Enumerated(EnumType.ORDINAL)
  private FeeParty feeParty;

  @NonNull
  private int percent;
}
