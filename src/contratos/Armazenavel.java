package contratos;

public interface Armazenavel {

    public abstract String getStorageString();
    public abstract Object fromStorageString(String texto);
    
}