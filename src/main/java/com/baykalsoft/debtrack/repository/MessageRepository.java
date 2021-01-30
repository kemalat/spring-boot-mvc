package com.baykalsoft.debtrack.repository;

import com.baykalsoft.debtrack.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Ramesh Fadatare
 *
 */
public interface MessageRepository extends JpaRepository<Message, Integer>{

}