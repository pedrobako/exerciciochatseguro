package aprendendo.exerciciochatseguro;
import java.util.Arrays;
import java.util.Base64;

public class RC4 implements Cripto {
    
    private int KEY_SIZE;
    private byte[] chave;

    public RC4(String chave) {
        this.KEY_SIZE = 256;
        this.chave = chave.getBytes();
    }
    
    @Override
    public void setChave(String chave){
        this.chave = chave.getBytes();
    }
    
    @Override
    public String criptografa(String texto){
        byte[] textoByte = texto.getBytes();
        byte[] criptografado = RC4(this.chave, textoByte);
        String encodedString = Base64.getEncoder().encodeToString(criptografado);
        
        return encodedString;
    }
    
    @Override
    public String decriptografa(String textoCriptografado){
        byte[] decodedArray = Base64.getDecoder().decode(textoCriptografado);
        byte[] decriptografado = RC4(this.chave, decodedArray);
        
        return (new String(decriptografado));
    }
    
    @Override
    public byte[] criptografa(byte[] bytesMsgClaro){
        return RC4(this.chave, bytesMsgClaro);
    }
    
    @Override
    public byte[] decriptografa(byte[] bytesMsgCripto){
        return RC4(this.chave, bytesMsgCripto);
    }
    
    private byte[] RC4(byte[] chave, byte[] texto) {
        int[] s = inicializaS(chave);
        byte[] textoCifrado = new byte[texto.length];
        int i = 0, j = 0;

        for (int k = 0; k < texto.length; k++) {
            i = (i + 1) % KEY_SIZE;
            j = (j + s[i]) % KEY_SIZE;
            swap(s, i, j);
            int t = (s[i] + s[j]) % KEY_SIZE;
            byte keyByte = (byte) s[t];
            textoCifrado[k] = (byte) (texto[k] ^ keyByte);
        }

        return textoCifrado;
    }

    private int[] inicializaS(byte[] chave) {
        int[] s = new int[KEY_SIZE];
        int tamanhoChave = chave.length;

        for (int i = 0; i < KEY_SIZE; i++) {
            s[i] = i;
        }

        int j = 0;
        for (int i = 0; i < KEY_SIZE; i++) {
            j = (j + s[i] + chave[i % tamanhoChave]) % KEY_SIZE;
            swap(s, i, j);
        }

        return s;
    }

    private void swap(int[] s, int i, int j) {
        int temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}