package aprendendo.exerciciochatseguro;
import java.util.*;
import java.math.*;

public class DiffieHellman {
    private int chavePubLocal;
    private int chavePriv;
    private int chaveSessao;
    private int alfa;
    private int primo;
    private int chavePublicaRemota;

    public void setChavePriv(int chavePriv) {
        this.chavePriv = chavePriv;
    }

    public void setChavePubLocal(int chavePub) {
        this.chavePubLocal = chavePub;
    }
    
    public void setChavePublicaRemota(int chavePub){
        this.chavePublicaRemota = chavePub;
        calculaChaveSessao();
    }

    private void calculaChaveSessao() {
        BigInteger bigChavePubRemota = BigInteger.valueOf(this.chavePublicaRemota);
        BigInteger bigPrimo = BigInteger.valueOf(this.primo);
        BigInteger bigChaveSessao = (bigChavePubRemota.pow(this.chavePriv)).mod(bigPrimo);
        this.chaveSessao = bigChaveSessao.intValue();
    }

    public void setAlfa(int alfa) {
        this.alfa = alfa;
    }

    public void setPrimo(int primo) {
        this.primo = primo;
    }

    public int getAlfa() {
        return alfa;
    }

    public int getPrimo() {
        return primo;
    }

    public int getChavePriv() {
        return chavePriv;
    }

    public int getChavePubLocal() {
        return chavePubLocal;
    }

    public int getChaveSessao() {
        return chaveSessao;
    }
    
    public int geraChavePublicaLocal(){
        BigInteger alfaBig = BigInteger.valueOf(this.alfa);
        BigInteger primoBig = BigInteger.valueOf(this.primo);
        System.out.println("Alfa: " + alfaBig + " Primo: " + primoBig + " Chave Priv: " + this.chavePriv);
        BigInteger chavePubBig = (alfaBig.pow(this.chavePriv)).mod(primoBig);
        this.chavePubLocal = chavePubBig.intValue();
        
        return this.chavePubLocal;
    }

    public int geraPrimo(int pontoPartida){
        int candidato = pontoPartida;
        while(true){
            if (verificaPrimo(candidato)){
                this.primo = candidato;
                return this.primo;
            }
            else
                candidato++;
        }
    }
    
    public List<BigInteger> geraRaizesPrimitivaNrPrimo(){
        BigInteger primoBig = new BigInteger(String.valueOf(this.primo));
        List<BigInteger> raizesPrimitivas = new ArrayList();
        if (verificaPrimo(this.primo)) {
            BigInteger aux;
            List<BigInteger> restos = new ArrayList();
            BigInteger raizCandidata = BigInteger.valueOf(2);
            
            //Enquanto o valor candidato a raiz primitiva (mod primo)
            //for menor que o número primo
            //continuo testando se é raíz primitiva (mod primo)
            while (raizCandidata.compareTo(primoBig) < 0) {
                for (int k = 1; k < this.primo; k++) {
                    aux = (raizCandidata.pow(k)).mod(primoBig);
                    if (aux == BigInteger.ONE || restos.contains(aux)) {
                        break;
                    }
                    restos.add(aux);
                }
                if (restos.size() == (this.primo - 1)) {
                    raizesPrimitivas.add(raizCandidata);
                }
                //Atualizo a candidata a raíz primitiva
                //E limpo a lista de restos
                raizCandidata = raizCandidata.add(BigInteger.ONE);
                restos.clear();
            }
        }
        return raizesPrimitivas;
    }

    private boolean verificaPrimo(int n)
    {
        int i, raiz;
        boolean retorno = true;

        //Se n for 2 ou 3, ele é primo
        if ((n == 2) || (n == 3))
            retorno = true;
        //Se n for 1 ou for divisível por 2 ou 3, não é primo
        else if ((n == 1) || (n%2 == 0) || (n%3 == 0))
            retorno = false;
        else
        {
            //Usando o crivo de Eratóstenes como limite
            raiz = (int)Math.sqrt(n);

            //Começo a testar do 5 pulando os múltiplos de 2 e 3
            for (i=5; i<=raiz; i+=4)
            {
                //Se n tiver um divisor diferente de 1 e dele mesmo, não é primo
                if (n%i == 0)
                {
                    retorno = false;
                    i = 2*raiz;
                }
                else
                {
                    i+=2;

                    if (n%i == 0)
                        retorno = false;
                }
            }
        }
        return retorno;
    }
}