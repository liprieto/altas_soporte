package com.itcsoluciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itcsoluciones.entity.AltaPrueba;
@Repository
public interface AltaPruebaRepository extends JpaRepository<AltaPrueba, Integer>
{
	
}