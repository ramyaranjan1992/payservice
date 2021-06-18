package payer.restservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import payer.restservices.modal.PayerBO;

@Repository
public interface PayerRepository extends JpaRepository<PayerBO,Long> {

	PayerBO findByAccountIdAndStatus(String username, String string);
	
	Optional<PayerBO> findByAccountId(String username);

	//PayerBO findByLoginIdAndPasswdAndStatus(String username, String password, String string);
	
	//PayerBO findByMobileNoAndPasswdAndStatus(Long valueOf, String password, String string);

	PayerBO findByEmail(String username);
	
	
	

}