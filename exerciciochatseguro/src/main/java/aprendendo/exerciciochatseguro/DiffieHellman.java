package aprendendo.exerciciochatseguro;
import java.util.*;
import java.math.*;

public class DiffieHellman {
    
    public int geraPrimo(int pontoPartida){
        int candidato = pontoPartida;
        while(true){
            if (verificaPrimo(candidato))
                return candidato;
            else
                candidato++;
        }
    }
    
    public List<BigInteger> geraRaizesPrimitivaNrPrimo(BigInteger primo){
        List<BigInteger> raizesPrimitivas = new ArrayList();
        if (verificaPrimo(primo.intValue())) {
            BigInteger aux;
            List<BigInteger> restos = new ArrayList();
            BigInteger raizCandidata = BigInteger.valueOf(2);
            
            //Enquanto o valor candidato a raiz primitiva (mod primo)
            //for menor que o número primo
            //continuo testando se é raíz primitiva (mod primo)
            while (raizCandidata.compareTo(primo) < 0) {
                for (int k = 1; k < primo.intValue(); k++) {
                    aux = (raizCandidata.pow(k)).mod(primo);
                    if (aux == BigInteger.ONE || restos.contains(aux)) {
                        break;
                    }
                    restos.add(aux);
                }
                if (restos.size() == primo.intValue() - 1) {
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