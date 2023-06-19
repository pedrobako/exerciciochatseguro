package aprendendo.exerciciochatseguro;

public class VetorChar {
    
    public static void imprime(char[] vetor){
        int tam = vetor.length;
        int i;
        for (i=0;i<tam;i++)
            System.out.print(vetor[i]);
        System.out.println("");
    }
    
    public static void imprime(String nome, char[] vetor){
        int tam = vetor.length;
        int i;
        System.out.print(nome + ": ");
        for (i=0;i<tam;i++)
            System.out.print(vetor[i]);
        System.out.println("");
    }
    
    public static String toString(char[] vetor){
        String s = new String();
        for (char c : vetor){
            s+=c;
        }
        return s;
    }
    
}
