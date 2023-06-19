package aprendendo.exerciciochatseguro;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ECB implements ModoOperacao {
    
    @Override
    public void enviaMensagem(String mensagem, OutputStream saida, Cripto cripto){
        byte[] bytesMsgClaro = mensagem.getBytes();
        byte[] arrayBloco = new byte[1];
        for (int i=0; i<bytesMsgClaro.length; i++){
            arrayBloco[0] = bytesMsgClaro[i];
            try {
                saida.write(cripto.criptografa(arrayBloco)[0]);
            } catch (IOException ex) {
                Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public byte recebeMensagem(byte bloco, Cripto cripto){
        byte[] arrayByteCripto = new byte[1];
        arrayByteCripto[0] = bloco;        
        return cripto.decriptografa(arrayByteCripto)[0];
    }
    
}
