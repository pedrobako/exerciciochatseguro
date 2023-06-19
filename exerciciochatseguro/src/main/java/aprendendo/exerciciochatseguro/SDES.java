package aprendendo.exerciciochatseguro;
import java.util.*;

public class SDES implements Cripto{
    private char[] k1;
    private char[] k2;
    private int[][] matrizSZero, matrizSUm;
    private char[] chave;
    
    public SDES(String chave){
        instanciaMatrizes();
        this.chave = chave.toCharArray();
        geraK1K2();
    }
    
    private void instanciaMatrizes(){
        this.matrizSZero = new int[4][4];
        this.matrizSUm = new int[4][4];
        this.matrizSZero[0][0] = 1;
        this.matrizSZero[0][1] = 0;
        this.matrizSZero[0][2] = 3;
        this.matrizSZero[0][3] = 2;
        this.matrizSZero[1][0] = 3;
        this.matrizSZero[1][1] = 2;
        this.matrizSZero[1][2] = 1;
        this.matrizSZero[1][3] = 0;
        this.matrizSZero[2][0] = 0;
        this.matrizSZero[2][1] = 2;
        this.matrizSZero[2][2] = 1;
        this.matrizSZero[2][3] = 3;
        this.matrizSZero[3][0] = 3;
        this.matrizSZero[3][1] = 1;
        this.matrizSZero[3][2] = 3;
        this.matrizSZero[3][3] = 2;
        
        this.matrizSUm[0][0] = 1;
        this.matrizSUm[0][1] = 1;
        this.matrizSUm[0][2] = 2;
        this.matrizSUm[0][3] = 3;
        this.matrizSUm[1][0] = 2;
        this.matrizSUm[1][1] = 0;
        this.matrizSUm[1][2] = 1;
        this.matrizSUm[1][3] = 3;
        this.matrizSUm[2][0] = 3;
        this.matrizSUm[2][1] = 0;
        this.matrizSUm[2][2] = 1;
        this.matrizSUm[2][3] = 0;
        this.matrizSUm[3][0] = 2;
        this.matrizSUm[3][1] = 1;
        this.matrizSUm[3][2] = 0;
        this.matrizSUm[3][3] = 3;
    }

    @Override
    public void setChave(String chave) {
        this.chave = chave.toCharArray();
        geraK1K2();
    }
    
    @Override
    public String criptografa(String s){
        char c;
        char[] bloco;
        
        String criptografado = new String();
        
        for (int i=0; i<s.length(); i++){
            c = s.charAt(i);
            bloco = formataBloco((byte)c);
            criptografado += VetorChar.toString(criptografaBloco8Char(bloco));
        }
        return criptografado;
    }
    
    @Override
    public String decriptografa(String s){
        int i, j;
        int qtdeBlocos = s.length()/8;
        char bloco[] = new char[8];
        String decriptografado = new String();
        
        for (i=0; i<qtdeBlocos; i++){
            for (j=0; j<8; j++){
                bloco[j] = s.charAt(j + (i*8));
            }
            decriptografado += blocoToChar(decriptografaBloco8Char(bloco));
        }
        return decriptografado;
    }
    
    @Override
    public byte[] criptografa(byte[] bytesMsgClaro){
        char[] bloco;
        char[] blocoCripto;
        byte[] bytesCriptografados = new byte[bytesMsgClaro.length];
        
        for (int i=0; i<bytesMsgClaro.length; i++){
            bloco = formataBloco(bytesMsgClaro[i]);
            blocoCripto = criptografaBloco8Char(bloco);
            bytesCriptografados[i] = (byte) blocoToChar(blocoCripto);
        }
        return bytesCriptografados;
    }
    
    @Override
    public byte[] decriptografa(byte[] bytesMsgCripto){
        char[] blocoCripto;
        char[] blocoDecripto;
        byte[] bytesDecriptografados = new byte[bytesMsgCripto.length];
        
        for (int i=0; i<bytesMsgCripto.length; i++){
            blocoCripto = formataBloco(bytesMsgCripto[i]);
            blocoDecripto = decriptografaBloco8Char(blocoCripto);
            bytesDecriptografados[i] = (byte) blocoToChar(blocoDecripto);
        }
        return bytesDecriptografados;
    }
    
    private char[] formataBloco(byte b){
        int i, dif;
        int byteInt = (int) b;
        if (byteInt < 0)
            byteInt = 256 + byteInt;
        String binario = Integer.toBinaryString(byteInt);
        
        char[] bloco = binario.toCharArray();
        dif = 8 - bloco.length;
        bloco = Arrays.copyOf(bloco, 8);
        for (i=7; i>=dif; i--)
            bloco[i] = bloco[i-dif];
        for (i=0; i<dif; i++)
            bloco[i] = '0';
        
        return bloco;
    }
    
    private char blocoToChar(char[] bloco){
        String binario = VetorChar.toString(bloco);
        int i = Integer.parseInt(binario, 2);
        return (char) i;
    }
    
    //Recebe um vetor de 8 caracteres (texto em claro)
    //Criptografa o texto
    //Retornando um vetor de 8 caracteres com o texto criptografado
    public char[] criptografaBloco8Char(char[] bloco){    
        char[] pIni = permutacao_inicial(bloco);
        char[] saida1 = funcao_complexa(pIni, this.k1);
        char[] sw = funcaoSwitch(saida1);
        char[] saida2 = funcao_complexa(sw, this.k2);
        char[] invPI = inversaPermutacaoInicial(saida2);
        return invPI;
    }
    
    private char[] decriptografaBloco8Char(char[] bloco){    
        char[] pIni = permutacao_inicial(bloco);
        char[] saida1 = funcao_complexa(pIni, k2);
        char[] sw = funcaoSwitch(saida1);
        char[] saida2 = funcao_complexa(sw, k1);
        char[] invPI = inversaPermutacaoInicial(saida2);
        return invPI;
    }
    
    
    //Aplicando a permutacao inicial [2 6 3 1 4 8 5 7]
    //Adaptada para vetor com indice inicial 0 [1 5 2 0 3 7 4 6]
    private char[] permutacao_inicial(char[] bloco){
        char[] pIni = new char[8];
        pIni[0] = bloco[1];
        pIni[1] = bloco[5];
        pIni[2] = bloco[2];
        pIni[3] = bloco[0];
        pIni[4] = bloco[3];
        pIni[5] = bloco[7];
        pIni[6] = bloco[4];
        pIni[7] = bloco[6];
        return pIni;
    }

    //Recebe um vetor de 10 caracteres e aplica a permutacao P10
    private char[] p10(){
        char[] saidaP10 = new char[10];
        //Aplicando a permutacao p10 [3 5 2 7 4 10 1 9 8 6]
        //Adaptada para vetor com indice inicial 0 [2 4 1 6 3 9 0 8 7 5]
        saidaP10[0] = this.chave[2];
        saidaP10[1] = this.chave[4];
        saidaP10[2] = this.chave[1];
        saidaP10[3] = this.chave[6];
        saidaP10[4] = this.chave[3];
        saidaP10[5] = this.chave[9];
        saidaP10[6] = this.chave[0];
        saidaP10[7] = this.chave[8];
        saidaP10[8] = this.chave[7];
        saidaP10[9] = this.chave[5];
        return saidaP10;
    }
    
    //Realiza a rotacao a esquerda no vetor passado como parametro
    private void rotacaoLS1(char[] vetor){
        int tam = vetor.length;
        int i;
        char temp = vetor[0];
        
        for(i=0; i<(tam-1); i++)
            vetor[i] = vetor[i+1];
        
        vetor[tam-1] = temp;
    }
    
    //Aplicando a permutacao p8 [6 3 7 4 8 5 10 9]
    //Adaptada para vetor com indice inicial 0 [5 2 6 3 7 4 9 8]
    private char[] p8(char[] entrada){
        char[] saida_p8 = new char[8];
        saida_p8[0] = entrada[5];
        saida_p8[1] = entrada[2];
        saida_p8[2] = entrada[6];
        saida_p8[3] = entrada[3];
        saida_p8[4] = entrada[7];
        saida_p8[5] = entrada[4];
        saida_p8[6] = entrada[9];
        saida_p8[7] = entrada[8];
        return saida_p8;
    }

    //Recebe a chave K com 10 digitos e gera as chaves k1 e k2 com 8 digitos
    private void geraK1K2(){

        int i;
        char[] subchave1 = new char[5];
        char[] subchave2 = new char[5];
        char[] uniao = new char[10];

        //Realizando a permutação P10 na chave recebida
        char[] chaveP10 = p10();

        //Dividindo a chave P10 em duas subchaves
        for(i=0; i<5; i++){
            subchave1[i] = chaveP10[i];
            subchave2[i] = chaveP10[i+5];
        }

        //Realizando uma rotação a esquerda nas subchaves
        rotacaoLS1(subchave1);
        rotacaoLS1(subchave2);

        //Reunindo as subchaves
        for(i=0; i<5; i++){
            uniao[i] = subchave1[i];
            uniao[i+5] = subchave2[i];
        }

        //Realizando a permutação P8 para gerar K1
        this.k1 = p8(uniao);

        //Realizando duas rotações a esquerda nas subchaves
        for(i=0; i<2; i++){
            rotacaoLS1(subchave1);
            rotacaoLS1(subchave2);
        }

        //Reunindo as subchaves
        for(i=0; i<5; i++){
            uniao[i] = subchave1[i];
            uniao[i+5] = subchave2[i];
        }

        //Realizando a permutação P8 para gerar K2
        this.k2 = p8(uniao);
    }
    
    //Expandindo de acordo com a ordem [4 1 2 3 2 3 4 1]
    //Adaptada para vetor com indice inicial zero [3 0 1 2 1 2 3 0]
    private char[] expande(char[] entrada){
        char[] saida = new char[8];
        saida[0] = entrada[3];
        saida[1] = entrada[0];
        saida[2] = entrada[1];
        saida[3] = entrada[2];
        saida[4] = entrada[1];
        saida[5] = entrada[2];
        saida[6] = entrada[3];
        saida[7] = entrada[0];
        return saida;
    }
    
    //Retorna um vetor de caracteres com o xor aplicado digito a digito entre os dois vetores de entrada
    //Os vetores de entrada devem possuir char que representam os bits 0 e 1
    private char[] xor(char[] vetor1, char[] vetor2){

        int i;
        int tam = vetor1.length;
        char[] xorado = new char[tam];

        //Considerando que estamos trabalhando com char, temos: 0+0=96, 0+1=97 e 1+1=98
        //No XOR a saida somente sera 1 se as entradas forem opostas, ou seja, 0 e 1 ou 1 e 0, ou seja quando a soma der 97
        for(i=0; i<tam; i++){
            if (vetor1[i]+vetor2[i] == 97)
                xorado[i] = '1';
            else
                xorado[i] = '0';
        }
        return xorado;
    }
    
    //Recebe dois char que representam bits 0 ou 1 e retorna o inteiro decimal representado pelos dois "bits"
    private int charBin2ToInt(char digUm, char digDois){

        int saida;
        int soma = digUm + digDois;

        //Considerando que estamos trabalhando com char, temos: 0+0=96, 0+1=97, 1+0=97 e 1+1=98
        if ((soma) == 96)
            saida = 0;
        else if ((soma) == 98)
            saida = 3;
        else if (digUm == '0')
            saida = 1;
        else
            saida = 2;

        return saida;
    }

    //Recebe um inteiro entre 0 e 3
    //e retorna uma string de dois char que contem os "bits" que representam o inteiro decimal em binario
    char[] intToChar2Bin(int entrada){

        char[] saida = new char[2];

        switch (entrada) {
            case 0 : {
                saida[0] = '0';
                saida[1] = '0';
                break;
            }
            case 1 : {
                saida[0] = '0';
                saida[1] = '1';
                break;
            }
            case 2 : {
                saida[0] = '1';
                saida[1] = '0';
                break;
            }
            case 3 : {
                saida[0] = '1';
                saida[1] = '1';
                break;
            }
            default : {
                break;
            }
        }
        return saida;
    }
    
    //Recebe um vetor de 2 char que contem 2 "bits"
    //e retorna um vetor com 2 char que representam em binario o inteiro de saida da matriz S zero
    char[] sZero(char[] entrada){
        int linha = charBin2ToInt(entrada[0], entrada[3]);
        int coluna = charBin2ToInt(entrada[1], entrada[2]);
        
        return (intToChar2Bin(this.matrizSZero[linha][coluna]));
    }
    
    //Recebe uma string de 2 char que contem 2 "bits"
    //e retorna uma string com 2 char que representam em binario o inteiro de saida da matriz s_um
    private char[] sUm(char[] entrada){
        int linha = charBin2ToInt(entrada[0], entrada[3]);
        int coluna = charBin2ToInt(entrada[1], entrada[2]);
        
        return (intToChar2Bin(matrizSUm[linha][coluna]));
    }
    
    //Aplicando a permutacao p4 [2 4 3 1]
    //Adaptada para vetor com indice inicial zero [1 3 2 0]
    char[] p4(char[] entrada){
        char[] saidaP4 = new char[4];
        saidaP4[0] = entrada[1];
        saidaP4[1] = entrada[3];
        saidaP4[2] = entrada[2];
        saidaP4[3] = entrada[0];
        return saidaP4;
    }

    //Aplicando a inversa permutacao inicial [4 1 3 5 7 2 8 6]
    //Adaptada para vetor com indice inicial zero [3 0 2 4 6 1 7 5]
    private char[] inversaPermutacaoInicial(char[] entrada){
        char[] saida = new char[8];
        saida[0] = entrada[3];
        saida[1] = entrada[0];
        saida[2] = entrada[2];
        saida[3] = entrada[4];
        saida[4] = entrada[6];
        saida[5] = entrada[1];
        saida[6] = entrada[7];
        saida[7] = entrada[5];
        return saida;
    }
    
    //Recebe um vetor de dados com 8 digitos e uma chave criptografica (k1 ou k2) tambem com 8 digitos
    //Aplica a funcao complexa de SDES e retorna um vetor criptografada/decriptografada com 8 digitos
    private char[] funcao_complexa(char[] entrada, char[] k){
        int i;
        char[] esq = new char[4];
        char[] dir = new char[4];
        char[] dirEsq = new char[4];
        char[] dirDir = new char[4];
        char[] entradaP4 = new char[4];
        char[] dirExp, dirXor, saidaS0, saidaS1, saidaP4, saidaXor;
        char[] saida = new char[8];

        //Dividindo o lado esquerdo e direito dos dados para processamento
        for(i=0; i<4; i++){
            esq[i] = entrada[i];
            dir[i] = entrada[i+4];
        }

        //Expandindo o lado direito de 4 para 8 bits
        dirExp = expande(dir);

        //Realizando o xor do lado direito expandido com a chave k
        dirXor = xor(dirExp, k);

        //Dividindo o lado direito expandido e xorado em duas partes (esq/dir)
        for(i=0; i<4; i++){
            dirEsq[i] = dirXor[i];
            dirDir[i] = dirXor[i+4];
        }

        //Passando o lado esquerdo pela caixa S0
        saidaS0 = sZero(dirEsq);

        //Passando o lado direito pela caixa S1
        saidaS1 = sUm(dirDir);

        //Concatenando saida_s0 e saida_s1 para dar entrada na permutacao p4
        for (i=0; i<2; i++){
            entradaP4[i] = saidaS0[i];
            entradaP4[i+2] = saidaS1[i];
        }

        //Realizando a permutacao P4
        saidaP4 = p4(entradaP4);

        //Xorando saida P4 com a entrada esquerda
        saidaXor = xor(esq, saidaP4);

        //Concatenando a saida do XOR com a parte direita dos dados de entrada
        for(i=0; i<4; i++){
            saida[i] = saidaXor[i];
            saida[i+4] = dir[i];
        }
        return saida;
    }

    //Recebe um vetor de 8 caracteres e retorna com os 4 primeiros caracteres invertidos com os 4 ultimos
    private char[] funcaoSwitch(char[] entrada){
        int i;
        char[] saida = new char[8];
        for(i=0; i<4; i++){
            saida[i] = entrada[i+4];
            saida[i+4] = entrada[i];
        }
        return saida;
    }
}