package profile.objetos;

import java.io.Serializable;

/**
 *
 * @author Matheus
 */

public class Dica implements Serializable{
    private long id;
    private String dica;
    private long pergunta;
    public long getId() {
            return id;
    }
    public void setId(long id) {
            this.id = id;
    }

    public String getDica() {
            return dica;
    }
    public void setDica(String dica) {
            this.dica = dica;
    }

    public long getPergunta() {
        return pergunta;
    }

    public void setPergunta(long pergunta) {
        this.pergunta = pergunta;
    }
}
