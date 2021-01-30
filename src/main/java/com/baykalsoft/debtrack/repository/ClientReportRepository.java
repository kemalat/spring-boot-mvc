package com.baykalsoft.debtrack.repository;

import com.baykalsoft.debtrack.entity.ClientReport;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Ramesh Fadatare
 *
 */
@Repository

public interface ClientReportRepository extends JpaRepository<ClientReport, Integer>
{

  @Query("SELECT u FROM ClientReport u WHERE u.clientId = :clientId")
  Optional<ClientReport> findByClientId(@Param("clientId") int clientId);

  @Modifying
  @Transactional
  @Query("UPDATE ClientReport s SET s.fees = :fees, s.interest =:interest, s.legalFees =:legalFees,"
      + "s.owingActive =:owingActive, s.owingClosed =:owingClosed, "
      + "s.principal =:principal WHERE s.clientId = :clientId")
  int updateClientReport(@Param("fees") BigDecimal fees, @Param("interest") BigDecimal interest,
      @Param("legalFees") BigDecimal legalFees,
      @Param("owingActive")BigDecimal owingActive,
      @Param("owingClosed")BigDecimal owingClosed,
      @Param("principal")BigDecimal principal,@Param("clientId")int clientId );

}