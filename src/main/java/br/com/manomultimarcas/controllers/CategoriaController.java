package br.com.manomultimarcas.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.manomultimarcas.model.CategoriaProduto;
import br.com.manomultimarcas.repository.CategoriaRepository;
import br.com.manomultimarcas.services.CategoriaService;
import br.com.manomultimarcas.util.ExceptionLojaVirtual;

@Controller
@RestController
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@ResponseBody
	@PostMapping(value = "**/salvarCategoria")
	public ResponseEntity<CategoriaProduto> salvarCategoria(@RequestBody @Valid CategoriaProduto categoriaProduto) throws ExceptionLojaVirtual{
		
		if (categoriaProduto == null ) {
			throw new ExceptionLojaVirtual("Categoria não pode ser nulo.");
			
		}
	
		if (categoriaProduto.getId() == null && categoriaRepository.descricaoExistente(categoriaProduto.getDescricaoCategoria()) != null) {
			throw new ExceptionLojaVirtual("Descrição já cadastrada");
		}
		
		categoriaProduto = categoriaService.salvarCategoria(categoriaProduto);
		
		return new ResponseEntity<CategoriaProduto>(categoriaProduto, HttpStatus.OK);
	}
}