package br.com.rapidmotorbike.rapidmotorbike.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rapidmotorbike.rapidmotorbike.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByCellPhoneAndPassword(String cell_phone, String password);

	List<Users> findByUserType(int userType);
}