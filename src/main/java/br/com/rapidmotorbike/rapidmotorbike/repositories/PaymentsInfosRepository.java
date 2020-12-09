package br.com.rapidmotorbike.rapidmotorbike.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rapidmotorbike.rapidmotorbike.models.PaymentsInfos;

@Repository
public interface PaymentsInfosRepository extends JpaRepository<PaymentsInfos, Long> { }