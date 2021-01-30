package com.baykalsoft.debtrack.repository;

import com.baykalsoft.debtrack.entity.Client;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * @author Ramesh Fadatare
 *
 */
@Repository

public interface ClientRepository extends PagingAndSortingRepository<Client, Long>
{

  @Query(value="select id from client", nativeQuery=true)
  public List<BigInteger> getIds();

  @Query(value="select sum(fees) , sum(interest) , sum(legal_fees), sum(principal) from debtor where client_id =:clientId ", nativeQuery=true)
  public List<Object[]> getTotals(@Param("clientId") long clientId);

  @Query(value="select sum(owing) from debtor where status = 'ACTIVE' and client_id =:clientId", nativeQuery=true)
  public List<BigDecimal> getOwingActive(@Param("clientId") long clientId);

  @Query(value="select sum(owing) from debtor where status = 'CLOSED' and client_id =:clientId", nativeQuery=true)
  public List<BigDecimal> getOwingClosed(@Param("clientId") long clientId);

}
