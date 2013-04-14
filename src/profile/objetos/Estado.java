package profile.objetos;

import java.io.Serializable;

/**
 *
 * @author Matheus
 */

public class Estado implements Serializable{
	
    private long id;
    public long getId() {
            return id;
    }

    public void setId(long id) {
            this.id = id;
    }
    private String nome;
    private float aprendizadoPort9;
    private float aprendizadoMtm9;
    private float aprendizadoPort5;
    private float aprendizadoMtm5;
    private String infos;

    public Estado() {
    }

    public String getNome() {
            return nome;
    }
    public void setNome(String nome) {
            this.nome = nome;
    }
    public float getAprendizadoPort9() {
            return aprendizadoPort9;
    }
    public void setAprendizadoPort9(float aprendizadoPort9) {
            this.aprendizadoPort9 = aprendizadoPort9;
    }
    public float getAprendizadoMtm9() {
            return aprendizadoMtm9;
    }
    public void setAprendizadoMtm9(float aprendizadoMtm9) {
            this.aprendizadoMtm9 = aprendizadoMtm9;
    }
    public float getAprendizadoPort5() {
            return aprendizadoPort5;
    }
    public void setAprendizadoPort5(float aprendizadoPort5) {
            this.aprendizadoPort5 = aprendizadoPort5;
    }
    public float getAprendizadoMtm5() {
            return aprendizadoMtm5;
    }
    public void setAprendizadoMtm5(float aprendizadoMtm5) {
            this.aprendizadoMtm5 = aprendizadoMtm5;
    }
    public String getInfos() {
            return infos;
    }
    public void setInfos(String infos) {
            this.infos = infos;
    }
	

}
