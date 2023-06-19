package aprendendo.exerciciochatseguro;

import java.io.OutputStream;

public interface ModoOperacao {
    
    public void enviaMensagem(String mensagem, OutputStream saida, Cripto cripto);
    
    public byte recebeMensagem(byte bloco, Cripto cripto);
    
}
