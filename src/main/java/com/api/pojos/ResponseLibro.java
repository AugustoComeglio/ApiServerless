package com.api.pojos;

import java.io.InputStream;

public class ResponseLibro {

    private String titulo;
    private String genero;
    private Integer paginas;
    private Integer fecha;

    public ResponseLibro(String titulo, String genero, Integer paginas, Integer fecha) {
        this.titulo = titulo;
        this.genero = genero;
        this.paginas = paginas;
        this.fecha = fecha;
    }

    public ResponseLibro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public Integer getFecha() {
        return fecha;
    }

    public void setFecha(Integer fecha) {
        this.fecha = fecha;
    }
}
