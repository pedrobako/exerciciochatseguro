package aprendendo.exerciciochatseguro;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CBC implements ModoOperacao {
    private byte vetorXorSaida;
    private byte vetorXorEntrada;
    
    public CBC(byte IV){
        this.vetorXorEntrada = IV;
        this.vetorXorSaida = IV;
    }
    
    @Override
    public void enviaMensagem(String mensagem, OutputStream saida, Cripto cripto){
        byte[] bytesMsgClaro = mensagem.getBytes();
        byte[] arrayBloco = new byte[1];
        for (int i=0; i<bytesMsgClaro.length; i++){
            arrayBloco[0] = (byte) (((int)bytesMsgClaro[i])^((int)vetorXorSaida));
            try {
                this.vetorXorSaida = cripto.criptografa(arrayBloco)[0];
                saida.write(this.vetorXorSaida);
            } catch (IOException ex) {
                Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public byte recebeMensagem(byte bloco, Cripto cripto){
        byte[] arrayByteCripto = new byte[1];
        arrayByteCripto[0] = bloco;
        byte byteXor = this.vetorXorEntrada;
        this.vetorXorEntrada = bloco;
        byte decripto = cripto.decriptografa(arrayByteCripto)[0];
        return (byte)(((int)decripto)^((int)byteXor));
    }
}