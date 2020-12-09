package br.com.rapidmotorbike.rapidmotorbike.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rapidmotorbike.rapidmotorbike.models.Locations;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Long> { }