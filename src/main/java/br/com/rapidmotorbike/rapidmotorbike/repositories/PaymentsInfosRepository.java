package br.com.rapidmotorbike.rapidmotorbike.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rapidmotorbike.rapidmotorbike.models.PaymentsInfos;
import br.com.rapidmotorbike.rapidmotorbike.models.Users;

@Repository
public interface PaymentsInfosRepository extends JpaRepository<PaymentsInfos, Long> {
    Optional<Users> findByUser(Users user);
}