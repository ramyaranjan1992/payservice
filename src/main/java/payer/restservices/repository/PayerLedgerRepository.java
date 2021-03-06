package payer.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import payer.restservices.modal.PayerLedgerBO;

@Repository
public interface PayerLedgerRepository extends JpaRepository<PayerLedgerBO, Long> {

}