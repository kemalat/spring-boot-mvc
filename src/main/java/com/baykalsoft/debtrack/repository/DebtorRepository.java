package com.baykalsoft.debtrack.repository;

import com.baykalsoft.debtrack.entity.Debtor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Ramesh Fadatare
 *
 */
@Repository

public interface DebtorRepository extends PagingAndSortingRepository<Debtor, Long>
{

  Page<Debtor> findDebtorsByClientId(Pageable pageable, long clientId);


}