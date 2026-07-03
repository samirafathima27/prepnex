package com.prepnex.repository;

import com.prepnex.model.DsaProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DsaProblemRepository extends JpaRepository<DsaProblem, Long> {

}