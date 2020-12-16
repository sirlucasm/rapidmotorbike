package br.com.rapidmotorbike.rapidmotorbike.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rapidmotorbike.rapidmotorbike.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByCellPhoneAndPassword(String cell_phone, String password);
}