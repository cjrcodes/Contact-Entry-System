package com.cjrcodes.ces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjrcodes.ces.models.Name;


@Repository
public interface NameRepository extends JpaRepository<Name, Long> {

}
