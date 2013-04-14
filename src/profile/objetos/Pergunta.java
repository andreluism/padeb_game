/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package profile.objetos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Matheus
 */

public class Pergunta implements Serializable{
	private long id;
	private List<Dica> dicas;
	private String categoria;
    private Estado estado;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public List<Dica> getDicas() {
		return dicas;
	}
	public void setDicas(List<Dica> dicas) {
		this.dicas = dicas;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
	
}
