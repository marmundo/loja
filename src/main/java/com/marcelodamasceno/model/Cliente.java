package com.marcelodamasceno.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;

@Entity
public class Cliente implements Comparable<Object> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Length(min = 2, max = 30, message = "O tamanho do nome deve ser entre {min} e {max} caracteres")
	private String primeiroNome;
	
	@NotNull
    @Length(min=2, max=30,message="O tamanho do sobrenome deve ser entre {min} e {max} caracteres")
	private String sobrenome;

	@OneToMany(mappedBy = "cliente",fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<Pedido> pedidos;

	
	public Cliente(Long id, String primeiroNome, String sobrenome) {
		super();
		this.id = id;
		this.primeiroNome = primeiroNome;
		this.sobrenome = sobrenome;
	}

	public Cliente(String primeiroNome, String sobrenome) {
		super();
		this.primeiroNome = primeiroNome;
		this.sobrenome = sobrenome;
	}

	public Cliente() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", primeiroNome=" + primeiroNome + ", sobrenome=" + sobrenome + ", pedidos="
				+ pedidos + "]";
	}

	@Override
	public int compareTo(Object o) {
		Cliente c = (Cliente) o;
		return this.primeiroNome.compareTo(c.primeiroNome);
	}

}
