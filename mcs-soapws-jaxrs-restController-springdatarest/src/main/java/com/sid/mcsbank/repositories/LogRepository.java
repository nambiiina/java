package com.sid.mcsbank.repositories;


import com.sid.mcsbank.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
