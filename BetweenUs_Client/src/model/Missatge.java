package model;

import java.io.Serializable;

public class Missatge implements Serializable {
    private String accio;
    private Object data;


    public Missatge(String accio, Object data) {
        this.accio = accio;
        this.data = data;
    }

    public String getAccio() {
        return accio;
    }

    public void setAccio(String accio) {
        this.accio = accio;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
