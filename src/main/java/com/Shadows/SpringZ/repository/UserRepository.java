package com.Shadows.SpringZ.repository;

import com.Shadows.SpringZ.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email); // SELECT * FROM user WHERE email = ?

	boolean existsByEmail(String email);

	List<User> findByNameContaining(String name); // LIKE %name%

	List<User> findByNameAndEmail(String name, String email); // AND

	@Query("SELECT u FROM User u WHERE u.email = :email")
	User chercherParEmail(@Param("email") String email);

	@Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
	List<User> chercherParNom(@Param("name") String name);

	@Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
	User chercherParEmailSQL(@Param("email") String email);
}
