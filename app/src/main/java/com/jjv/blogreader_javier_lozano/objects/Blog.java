package com.jjv.blogreader_javier_lozano.objects;

/**
 * Created by javi0 on 02/02/2017.
 */

public class Blog {
    private String titulo;
    private String autor;
    private String blogUrl;

    public String getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
