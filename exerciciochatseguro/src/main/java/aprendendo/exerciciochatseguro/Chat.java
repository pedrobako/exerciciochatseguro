package aprendendo.exerciciochatseguro;
import java.net.UnknownHostException;
import java.util.*;
import java.math.*;


public class Chat {

    public static void main(String[] args) throws UnknownHostException{        
        int intPorta=0, portaFinal=0, intPos=0, posFinal=0;
        String nomeUsuario = "default";
		char idArg;

        for (String arg : args){
            arg = arg.toUpperCase().trim();
            idArg = arg.charAt(0);

            switch (idArg){
                case 'U':
                        nomeUsuario = arg.substring(1);
                        break;
                case 'L':
                        intPos = Integer.parseInt(arg.substring(1));
                        break;
                case 'P':
                        intPorta = Integer.parseInt(arg.substring(1));
                        break;
                default:
                        break;
            }
        }
        if (intPorta > 3000 && intPorta < 65536){
            portaFinal = intPorta;
        }
        else{
            portaFinal = 3001;
        }
        if (intPos >= 0 && intPos <= 870){
            posFinal = intPos;
        }
        else{
            posFinal = Math.abs(intPos - (intPos-870));
        }
		
        ChatBox chat;
        chat = new ChatBox(nomeUsuario, portaFinal);
        chat.setLocation(posFinal, 0);
        chat.setVisible(true);
        
        ChatBox chatTeste;
        chatTeste = new ChatBox("Usuario de teste", 3002);
        chatTeste.setLocation(800, 0);
        chatTeste.setVisible(true);
    }
}
