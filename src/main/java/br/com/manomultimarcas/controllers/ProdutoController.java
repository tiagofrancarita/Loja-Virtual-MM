package br.com.manomultimarcas.controllers;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import br.com.manomultimarcas.model.Produto;
import br.com.manomultimarcas.repository.ProdutoRepository;
import br.com.manomultimarcas.util.ExceptionLojaVirtual;

@Controller
public class ProdutoController {

	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

	@Autowired
	private ProdutoRepository produtoRepository;

	@ResponseBody // Poder dar um retorno da API
	@PostMapping(value = "**/salvarProduto") // URL para receber o json
	public ResponseEntity<Produto> salvarProduto(@RequestBody @Valid Produto produto) throws ExceptionLojaVirtual {

		logger.info("Cadastro de produto iniciado.");
		
		if (produto.getEmpresa() == null || produto.getEmpresa().getId() <= 0) {
			logger.error("Cadastro de produto encerrado com erro.");
			logger.error("Empresa responsável deve ser imformada.");
			throw new ExceptionLojaVirtual("Empresa responsável deve ser imformada.");	
		}
		
		if (produto.getId() == null) {
			List<Produto> produtos = produtoRepository.buscarProdutoNome(produto.getDescricao().toUpperCase(), produto.getEmpresa().getId());
			if (!produtos.isEmpty()) {
				logger.error("Cadastro de produto encerrado com erro.");
				logger.error("Já existe um produto com essa descrição." + produto.getNome());
				throw new ExceptionLojaVirtual("Já existe produto");
			}
			
	
			if (produto.getCategoriaProduto() == null  || produto.getCategoriaProduto().getId() <= 0) {
				logger.error("Cadastro de produto encerrado com erro.");
				logger.error("Categoria do produto deve ser imformada.");
				throw new ExceptionLojaVirtual("Categoria do produto deve ser imformada.");	
			}
			if (produto.getMarcaProduto() == null  || produto.getMarcaProduto().getId() <= 0) {
				logger.error("Cadastro de produto encerrado com erro.");
				logger.error("Marca do produto deve ser imformada.");
				throw new ExceptionLojaVirtual("Marca do produto deve ser imformada.");	
			}
		}

		logger.info("Produto cadastrado.");
		logger.info("Cadastro de produto finalizado com sucesso.");
		Produto produtoSalvo = produtoRepository.save(produto);
		return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.OK);

	}

	@ResponseBody // Poder dar um retorno da API
	@PostMapping(value = "**/deletarProduto") // URL para receber o json
	public ResponseEntity<String> deletarProduto(@RequestBody Produto produto) {// Recebe o json e converte para objeto

		produtoRepository.deleteById(produto.getId());
		return new ResponseEntity<String>("Produto excluído", HttpStatus.OK);

	}

	@ResponseBody
	@DeleteMapping(value = "**/deletarProdutoPorId/{id}") // URL para receber o json
	public ResponseEntity<String> deletarProdutoPorId(@PathVariable("id") Long id) {// Recebe o json e converte para
																					// objeto

		produtoRepository.deleteById(id);

		return new ResponseEntity<String>("Produto excluído", HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/obterProdutoId/{id}") // URL para receber o json
	public ResponseEntity<?> obterProdutoId(@PathVariable("id") Long id) throws ExceptionLojaVirtual {// Recebe o json e
			
		Produto produto = produtoRepository.findById(id).orElse(null);

		if (produto == null) {
			throw new ExceptionLojaVirtual("Código informado não existe." + "Código: " + id);
		}

		return new ResponseEntity<Produto>(produto, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/obterProdutoNome/{desc}") // URL para receber o json
	public ResponseEntity<List<Produto>> obterProdutoNome(@PathVariable("desc") String desc) {// Recebe o json e
																								// converte para objeto

		List<Produto> produto = produtoRepository.buscarProdutoNome(desc.toUpperCase());

		return new ResponseEntity<List<Produto>>(produto, HttpStatus.OK);

	}
}