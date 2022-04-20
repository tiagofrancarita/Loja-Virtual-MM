package br.com.manomultimarcas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.manomultimarcas.model.CategoriaProduto;

@Repository
public interface CategoriaRepository extends CrudRepository<CategoriaProduto, Long> {
	
	@Query("SELECT categ from CategoriaProduto categ WHERE upper(trim(categ.descricaoCategoria)) like %?1%")
	public CategoriaProduto descricaoExistente(String descricaoCategoria);
}