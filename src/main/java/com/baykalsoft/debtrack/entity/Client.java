package com.baykalsoft.debtrack.entity;


import com.baykalsoft.debtrack.enums.ActionPlan;
import com.baykalsoft.debtrack.enums.ClientStatus;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private String address;
  private String city;
  private String phone;
  private String webSite;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdDate;

  @ManyToOne(fetch = FetchType.EAGER)
  @Cascade(CascadeType.ALL)
  private Contact contact;

  @Enumerated(EnumType.STRING)
  private ClientStatus status;

  @Enumerated(EnumType.ORDINAL)
  private ActionPlan actionPlan;

//  @OneToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name = "agentId")
//  private User agent;

  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          javax.persistence.CascadeType.PERSIST,
          javax.persistence.CascadeType.MERGE
      },mappedBy="clients")
  private List<User> users;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "feeId")
  private OptionalFee optionalFee;

  private int rateCalculationMode;

  @OneToMany(mappedBy = "clientId",orphanRemoval = true)
  @Cascade(CascadeType.ALL)
  @Size(max=10)
  private List<Debtor> debtors;

  @OneToMany(orphanRemoval = true)
  @Cascade(CascadeType.ALL)
  @JoinColumn(name = "client_id")
  private List<Comission> comissions;


}
