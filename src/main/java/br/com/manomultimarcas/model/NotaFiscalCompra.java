package br.com.manomultimarcas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "NotaFiscalCompra")
@SequenceGenerator(name = "seqNotaFiscalCompra", sequenceName = "seqNotaFiscalCompra", allocationSize = 1,initialValue = 1)
public class NotaFiscalCompra implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqNotaFiscalCompra")
	private Long id;
	
	@Column(nullable = false)
	private String numeroNota;
	
	@Column(nullable = false)
	private String serieNota;
	
	
	private String descricaoObs;
	
	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	
	private BigDecimal valorDesconto;
	
	@Column(nullable = false)
	private BigDecimal valorICMS;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataCompra;
	
	@ManyToOne(targetEntity = Pessoa.class) //muitos para um
	@JoinColumn (name = "pessoaID",nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoaFK") )
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn (name = "contaPagarID",nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "contaPagarFK") )
	private ContaPagar contaPagar;
	
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

	public String getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}

	public String getSerieNota() {
		return serieNota;
	}

	public void setSerieNota(String serieNota) {
		this.serieNota = serieNota;
	}

	public String getDescricaoObs() {
		return descricaoObs;
	}

	public void setDescricaoObs(String descricaoObs) {
		this.descricaoObs = descricaoObs;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorICMS() {
		return valorICMS;
	}

	public void setValorICMS(BigDecimal valorICMS) {
		this.valorICMS = valorICMS;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public ContaPagar getContaPagar() {
		return contaPagar;
	}

	public void setContaPagar(ContaPagar contaPagar) {
		this.contaPagar = contaPagar;
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
		NotaFiscalCompra other = (NotaFiscalCompra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
