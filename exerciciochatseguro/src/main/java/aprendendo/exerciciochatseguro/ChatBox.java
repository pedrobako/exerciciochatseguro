package aprendendo.exerciciochatseguro;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ChatBox extends javax.swing.JFrame {

    public ChatBox(String nomeUsuario, int portaLocal) throws UnknownHostException{
        initComponents();
        this.criptoSDES = new SDES("0000000001");
        this.criptoRC4 = new RC4("0000000001");
        this.criptoSetado = (Cripto) this.criptoSDES;
        this.radioGroupCripto = new ButtonGroup();
        this.radioGroupCripto.add(this.radioSDES);
        this.radioGroupCripto.add(this.radioRC4);
        this.radioSDES.setSelected(true);
        this.modoECB = new ECB();
        this.modoCBC = new CBC((byte)2);
        this.modoOpSetado = this.modoECB;
        this.radioGroupModoOp = new ButtonGroup();
        this.radioGroupModoOp.add(radioECB);
        this.radioGroupModoOp.add(radioCBC);
        this.radioECB.setSelected(true);
        this.campoChave.setText("0000000001");
        this.campoIpLocal.setText(InetAddress.getLocalHost().getHostAddress());
        this.campoPortaLocal.setText(String.valueOf(portaLocal));
        this.campoIpRemoto.setText("127.0.0.1");
        this.campoPortaRemota.setText("3001");
        this.labelNomeUsuario.setText(nomeUsuario);
        this.textAreaChat.setFont(new Font("monospaced", Font.PLAIN, 12));
        this.textAreaChat.setEditable(false);
        this.campoIpLocal.setEditable(false);
        this.painelModoOp.setVisible(true);
    }
    
    private void initClient(String host, int porta){
        try {
            cliente = new Socket(host,porta);
        } catch (IOException ex) {
            Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("empty-statement")
    private void initServer(int porta) throws ClassNotFoundException, IOException{
        byte[] data;
        byte byteLido;
        ServerSocket server;
        Socket socketSwervidor;
        InputStream inputStream = null;
        try {
            server = new ServerSocket(porta); 
            System.out.println("Servidor iniciado no IP: " + InetAddress.getLocalHost().getHostAddress() +
                               " Porta: " + porta);
            socketSwervidor = server.accept();
            System.out.println("Cliente conectado do IP "+socketSwervidor.getInetAddress().getHostAddress());
            inputStream = socketSwervidor.getInputStream();
        }
        catch (IOException ex) {
            Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){
            byteLido = (byte) inputStream.read();
            
            insereMsgChat(modoOpSetado.recebeMensagem(byteLido, criptoSetado));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaMsg = new javax.swing.JTextArea();
        botaoEnviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaChat = new javax.swing.JTextArea();
        labelNomeUsuario = new javax.swing.JLabel();
        campoIpRemoto = new javax.swing.JTextField();
        campoPortaRemota = new javax.swing.JTextField();
        campoChave = new javax.swing.JTextField();
        botaoAtualizaChave = new javax.swing.JButton();
        labelIp = new javax.swing.JLabel();
        labelPorta = new javax.swing.JLabel();
        labelChave = new javax.swing.JLabel();
        radioSDES = new javax.swing.JRadioButton();
        radioRC4 = new javax.swing.JRadioButton();
        labelAlgoritmo = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        campoIpLocal = new javax.swing.JTextField();
        campoPortaLocal = new javax.swing.JTextField();
        labelRemoto = new javax.swing.JLabel();
        labelLocal = new javax.swing.JLabel();
        botaoAbrirConexao = new javax.swing.JButton();
        botaoConectar = new javax.swing.JButton();
        painelModoOp = new javax.swing.JPanel();
        labelModoOp = new javax.swing.JLabel();
        radioECB = new javax.swing.JRadioButton();
        radioCBC = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Projeto chat seguro");

        textAreaMsg.setColumns(20);
        textAreaMsg.setRows(5);
        jScrollPane3.setViewportView(textAreaMsg);

        botaoEnviar.setText("Enviar");
        botaoEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEnviarActionPerformed(evt);
            }
        });

        textAreaChat.setColumns(20);
        textAreaChat.setRows(5);
        jScrollPane1.setViewportView(textAreaChat);

        labelNomeUsuario.setText("Nome do usuário");

        campoChave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoChaveActionPerformed(evt);
            }
        });

        botaoAtualizaChave.setText("Atualizar chave");
        botaoAtualizaChave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizaChaveActionPerformed(evt);
            }
        });

        labelIp.setText("Endereço IP");

        labelPorta.setText("Porta");

        labelChave.setText("Chave:");

        radioSDES.setText("SDES");
        radioSDES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSDESActionPerformed(evt);
            }
        });

        radioRC4.setText("RC4");
        radioRC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioRC4ActionPerformed(evt);
            }
        });

        labelAlgoritmo.setText("Algoritmo de criptografia:");

        labelUsuario.setText("Usuário");

        labelRemoto.setText("Remoto:");

        labelLocal.setText("Local:");

        botaoAbrirConexao.setText("Abrir Conexão");
        botaoAbrirConexao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAbrirConexaoActionPerformed(evt);
            }
        });

        botaoConectar.setText("Conectar");
        botaoConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConectarActionPerformed(evt);
            }
        });

        labelModoOp.setText("Modo de operação:");

        radioECB.setText("ECB");
        radioECB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioECBActionPerformed(evt);
            }
        });

        radioCBC.setText("CBC");
        radioCBC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCBCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelModoOpLayout = new javax.swing.GroupLayout(painelModoOp);
        painelModoOp.setLayout(painelModoOpLayout);
        painelModoOpLayout.setHorizontalGroup(
            painelModoOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelModoOpLayout.createSequentialGroup()
                .addComponent(labelModoOp)
                .addGap(50, 50, 50)
                .addComponent(radioECB)
                .addGap(18, 18, 18)
                .addComponent(radioCBC)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        painelModoOpLayout.setVerticalGroup(
            painelModoOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelModoOpLayout.createSequentialGroup()
                .addGroup(painelModoOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelModoOpLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(labelModoOp))
                    .addGroup(painelModoOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioECB)
                        .addComponent(radioCBC)))
                .addGap(0, 79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(91, 91, 91))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelIp)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelChave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addComponent(campoChave, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelRemoto)
                                    .addComponent(labelLocal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoIpLocal, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                    .addComponent(campoIpRemoto))
                                .addGap(3, 3, 3)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoAtualizaChave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(labelPorta))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(campoPortaLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botaoAbrirConexao)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campoPortaRemota, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoConectar)
                        .addGap(16, 16, 16)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelAlgoritmo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radioSDES)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radioRC4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(painelModoOp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNomeUsuario)
                    .addComponent(labelUsuario))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelIp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelRemoto)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(labelLocal)
                                    .addGap(45, 45, 45)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(campoIpLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(campoIpRemoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelPorta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoPortaLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoAbrirConexao))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoPortaRemota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoConectar))))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAlgoritmo)
                    .addComponent(radioSDES)
                    .addComponent(radioRC4))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoChave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelChave)
                    .addComponent(botaoAtualizaChave))
                .addGap(18, 18, 18)
                .addComponent(painelModoOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEnviarActionPerformed
        String msg = textAreaMsg.getText();
        String msgCompleta = (this.labelNomeUsuario.getText()+ ": " + msg + "\n");
        String espacos = new String();
        int qtdeEspacos = 55-msg.length();
        int i;
        
        OutputStream saida = null;
        try {
            saida = cliente.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
        }
        modoOpSetado.enviaMensagem(msgCompleta, saida, criptoSetado);
        this.textAreaMsg.setText("");
        for(i=0; i<qtdeEspacos; i++)
            espacos += " ";
        this.textAreaChat.append(espacos + "Eu: " + msg + "\n");
        this.textAreaChat.setCaretPosition(this.textAreaChat.getText().length());
    }//GEN-LAST:event_botaoEnviarActionPerformed

    private void campoChaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoChaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoChaveActionPerformed

    private void radioSDESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSDESActionPerformed
        // TODO add your handling code here:
        this.criptoSetado = (Cripto) this.criptoSDES;
    }//GEN-LAST:event_radioSDESActionPerformed

    private void radioRC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioRC4ActionPerformed
        // TODO add your handling code here:
        this.criptoSetado = this.criptoRC4;
    }//GEN-LAST:event_radioRC4ActionPerformed

    private void botaoAtualizaChaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizaChaveActionPerformed

        if (this.radioSDES.getSelectedObjects() != null){
            
        }
        else if (this.radioRC4.getSelectedObjects() != null){
            
        }
        this.criptoSetado.setChave(campoChave.getText());
    }//GEN-LAST:event_botaoAtualizaChaveActionPerformed

    private void botaoConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConectarActionPerformed
        String host = this.campoIpRemoto.getText();
        int porta = Integer.parseInt(this.campoPortaRemota.getText());
        initClient(host, porta);
    }//GEN-LAST:event_botaoConectarActionPerformed

    private void botaoAbrirConexaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAbrirConexaoActionPerformed
        final int porta = Integer.parseInt(this.campoPortaLocal.getText());
        new Thread(){
            @Override
            public void run(){
                try {
                    initServer(porta);
                } catch (ClassNotFoundException | IOException ex) {
                    Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }//GEN-LAST:event_botaoAbrirConexaoActionPerformed

    private void radioECBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioECBActionPerformed
        // TODO add your handling code here:
        this.modoOpSetado = (ModoOperacao)this.modoECB;
    }//GEN-LAST:event_radioECBActionPerformed

    private void radioCBCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCBCActionPerformed
        // TODO add your handling code here:
        this.modoOpSetado = (ModoOperacao)this.modoCBC;
    }//GEN-LAST:event_radioCBCActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAbrirConexao;
    private javax.swing.JButton botaoAtualizaChave;
    private javax.swing.JButton botaoConectar;
    private javax.swing.JButton botaoEnviar;
    private javax.swing.JTextField campoChave;
    private javax.swing.JTextField campoIpLocal;
    private javax.swing.JTextField campoIpRemoto;
    private javax.swing.JTextField campoPortaLocal;
    private javax.swing.JTextField campoPortaRemota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelAlgoritmo;
    private javax.swing.JLabel labelChave;
    private javax.swing.JLabel labelIp;
    private javax.swing.JLabel labelLocal;
    private javax.swing.JLabel labelModoOp;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JLabel labelPorta;
    private javax.swing.JLabel labelRemoto;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel painelModoOp;
    private javax.swing.JRadioButton radioCBC;
    private javax.swing.JRadioButton radioECB;
    private javax.swing.JRadioButton radioRC4;
    private javax.swing.JRadioButton radioSDES;
    public javax.swing.JTextArea textAreaChat;
    private javax.swing.JTextArea textAreaMsg;
    // End of variables declaration//GEN-END:variables
    private ButtonGroup radioGroupCripto;
    private SDES criptoSDES;
    private RC4 criptoRC4;
    private Cripto criptoSetado;
    private Socket cliente;
    private ButtonGroup radioGroupModoOp;
    private ECB modoECB;
    private CBC modoCBC;
    private ModoOperacao modoOpSetado;
    
    public void insereMsgChat(byte byteMsg){
        String msg = new String();
        msg += (char) byteMsg;
        this.textAreaChat.append(msg);
        this.textAreaChat.setCaretPosition(this.textAreaChat.getText().length());
    }
}