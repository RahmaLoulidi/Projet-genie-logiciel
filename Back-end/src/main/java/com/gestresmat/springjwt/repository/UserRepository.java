package com.gestresmat.springjwt.repository;

import java.util.List;
import java.util.Optional;

import com.gestresmat.springjwt.models.Constat;
import com.gestresmat.springjwt.models.Fournisseur;
import com.gestresmat.springjwt.models.Panne;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestresmat.springjwt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	User findUserById(Long id);
	List<User> findAllSortedById(Sort sort);

	List<User> findAllByOrderByIdDesc();


}
