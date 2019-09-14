package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Agendamento;

public interface AgendamentosRepository extends JpaRepository<Agendamento, Long> {

}
