package br.com.manomultimarcas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.manomultimarcas.model.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long> {
	
	
	@Query("SELECT pf from PessoaFisica pf WHERE pf.cpf = ?1")
	public PessoaFisica cpfExistente(String cpf);

}
