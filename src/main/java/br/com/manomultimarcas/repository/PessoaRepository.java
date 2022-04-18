package br.com.manomultimarcas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.manomultimarcas.model.PessoaJuridica;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaJuridica, Long> {

	@Query("SELECT pj from PessoaJuridica pj WHERE pj.cnpj = ?1")
	public PessoaJuridica cnpjExistente(String cnpj);
	
	@Query("SELECT pj from PessoaJuridica pj WHERE pj.inscEstadual = ?1")
	public PessoaJuridica insEstadualExistente(String inscEstadual);
	
	@Query("SELECT pj from PessoaJuridica pj WHERE pj.inscMunincipal = ?1")
	public PessoaJuridica insMunincipalExistente(String inscMunincipal);

}