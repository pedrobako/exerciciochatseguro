package aprendendo.exerciciochatseguro;

public interface Cripto {
    
    public void setChave(String chave);
    
    public String criptografa(String texto);
    
    public String decriptografa(String textoCriptografado);
    
    public byte[] criptografa(byte[] bytesMsgClaro);
    
    public byte[] decriptografa(byte[] bytesMsgCripto);
}
