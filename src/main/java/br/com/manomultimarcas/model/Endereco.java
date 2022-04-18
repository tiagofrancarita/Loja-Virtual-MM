package br.com.manomultimarcas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.manomultimarcas.enums.TipoEndereco;

@Entity
@Table(name = "Endereco")
@SequenceGenerator(name = "seqEnderecoCobranca", sequenceName = "seqEnderecoCobranca", allocationSize = 1,initialValue = 1)
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEnderecoCobranca")
	private Long id;
	
	@NotBlank(message = "O campo logradouro deve ser informado!")
	@NotNull(message = "O campo logradouro munincipal deve ser informado!")
	@Column(nullable = false)
	private String logradouro;
	
	@NotBlank(message = "O campo cep deve ser informado!")
	@NotNull(message = "O campo cep deve ser informado!")
	@Column(nullable = false)
	private String cep;
	
	@NotBlank(message = "O campo número deve ser informado!")
	@NotNull(message = "O campo número deve ser informado!")
	@Column(nullable = false)
	private String numero;
	
	@NotBlank(message = "O campo complemento deve ser informado!")
	@NotNull(message = "O campo complemento deve ser informado!")
	@Column(nullable = false)
	private String complemento;
	
	@NotBlank(message = "O campo bairro deve ser informado!")
	@NotNull(message = "O campo bairro deve ser informado!")
	@Column(nullable = false)
	private String bairro;
	
	@NotBlank(message = "O campo cidade deve ser informado!")
	@NotNull(message = "O campo cidade deve ser informado!")
	@Column(nullable = false)
	private String cidade;
	
	@NotBlank(message = "O campo uf deve ser informado!")
	@NotNull(message = "O campo uf deve ser informado!")
	@Column(nullable = false)
	private String uf;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Pessoa.class) //muitos para um
	@JoinColumn (name = "pessoaID",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoaFK") )
	private Pessoa pessoa;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoEndereco tipoEndereco;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Pessoa.class) //muitos para um
	@JoinColumn (name = "empresaid",nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresaid") )
	private Pessoa empresa;
	
	public Pessoa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Pessoa empresa) {
		this.empresa = empresa;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}
	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
