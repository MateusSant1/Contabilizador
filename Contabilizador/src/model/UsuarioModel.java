package model;

public class UsuarioModel {
    private long id;
    private String usuario;
    private String email;
    private String senha;


    public UsuarioModel(Long id, String usuario, String email, String senha) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}

