package com.api.pojos;

public class ResponseDomicilo {

    private String calle;
    private Integer numero;

    public ResponseDomicilo(String calle, Integer numero) {
        this.calle = calle;
        this.numero = numero;
    }

    public ResponseDomicilo() {
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
