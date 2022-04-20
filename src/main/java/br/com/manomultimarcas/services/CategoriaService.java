package br.com.manomultimarcas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.manomultimarcas.model.CategoriaProduto;
import br.com.manomultimarcas.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public CategoriaProduto salvarCategoria(CategoriaProduto categoriaProduto) {
		
		categoriaProduto = categoriaRepository.save(categoriaProduto);
		
		return categoriaRepository.save(categoriaProduto);
		
	}
}