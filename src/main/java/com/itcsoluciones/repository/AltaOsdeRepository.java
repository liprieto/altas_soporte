package com.itcsoluciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itcsoluciones.entity.AltaOsde;
@Repository
public interface AltaOsdeRepository extends JpaRepository<AltaOsde, Integer>
{

}
