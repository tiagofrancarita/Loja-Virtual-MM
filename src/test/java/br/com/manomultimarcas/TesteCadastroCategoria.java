package br.com.manomultimarcas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.manomultimarcas.controllers.CategoriaController;
import br.com.manomultimarcas.model.CategoriaProduto;
import br.com.manomultimarcas.model.PessoaJuridica;
import br.com.manomultimarcas.repository.CategoriaRepository;
import br.com.manomultimarcas.repository.PessoaRepository;
import br.com.manomultimarcas.util.ExceptionLojaVirtual;


@Profile("test")
@SpringBootTest(classes = LojaVirtualManosMultimarcasApplication.class)
public class TesteCadastroCategoria {

	@Autowired
	private CategoriaController categoriaController;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test
	public void testeCadastroCategoria() throws ExceptionLojaVirtual {
		
		PessoaJuridica pessoaJuridica = pessoaRepository.cnpjExistente("4345345677783910657335676745");
		 
		//PessoaFisica pessoaFisicaTeste = new PessoaFisica();
		
		CategoriaProduto categoriaProdutoTeste = new CategoriaProduto();
		categoriaProdutoTeste.setDescricaoCategoria("Camisas");
		categoriaProdutoTeste.setEmpresa(pessoaJuridica);
		
		// pessoaFisicaTeste = pessoaController.salvarPf(pessoaFisicaTeste).getBody();
		
		categoriaProdutoTeste = categoriaController.salvarCategoria(categoriaProdutoTeste).getBody();
	}
}