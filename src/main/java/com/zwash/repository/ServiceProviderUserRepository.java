package com.zwash.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.zwash.pojos.ServiceProviderUser;



public interface ServiceProviderUserRepository extends JpaRepository<ServiceProviderUser, Long> {

	@Override
	@Query("SELECT s FROM ServiceProviderUser s WHERE s.id = :id")
	Optional<ServiceProviderUser> findById(@Param("id") Long id);
	
	@Query("SELECT s FROM ServiceProviderUser s WHERE s.id = :id  AND u.password = :password")
	Optional<ServiceProviderUser> signIn(@Param("username") String username, @Param("password") String password );
	
	 @Modifying
	 @Query("UPDATE ServiceProviderUser u SET u.password = :password WHERE u.username = :username")
	 int updatePassword(@Param("username") String username, @Param("password") String password);


}