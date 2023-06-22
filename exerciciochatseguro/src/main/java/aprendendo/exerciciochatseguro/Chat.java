package aprendendo.exerciciochatseguro;
import java.net.UnknownHostException;


public class Chat {
    
    public static void iniciaUsuario(String nomeUsuario, int porta, int posHoriz) throws UnknownHostException{
        ChatBox chat;
        chat = new ChatBox(nomeUsuario, porta);
        chat.setLocation(posHoriz, 0);
        chat.setVisible(true);
    }

    public static void main(String[] args) throws UnknownHostException{        
        String nomeUsuario;
        char tipoOp;
        
        if (args.length > 0)
            tipoOp = args[0].charAt(0);
        else
            tipoOp = 'V';
        
        //Operacao automatica com 2 usuarios
        //Alice e Bob
        if ((tipoOp == 'C') || (tipoOp == 'V')){
            iniciaUsuario("Alice", 3001, 0);
            iniciaUsuario("Bob", 3002, 800);
        }
        else if (tipoOp == 'A'){
            iniciaUsuario("Alice", 3001, 400);
        }
        else if (tipoOp == 'B'){
            iniciaUsuario("Bob", 3001, 400);
        }
        else if (tipoOp == 'U'){
            nomeUsuario = args[0].substring(1);
            iniciaUsuario(nomeUsuario, 3001, 400);
        }
    }
}
