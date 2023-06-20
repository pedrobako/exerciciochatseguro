package aprendendo.exerciciochatseguro;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ChatBox extends javax.swing.JFrame {

    public ChatBox(String nomeUsuario, int portaLocal) throws UnknownHostException{
        initComponents();
        this.dh = new DiffieHellman();
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
        this.textAreaRaizes.setEditable(false);
        this.textFieldPrimoGerado.setEditable(false);
        this.textFieldChavePub.setEditable(false);
        this.textFieldChaveSessao.setEditable(false);
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
        byte byteLido;
        ServerSocket server = null;
        Socket socketServidor = null;
        InputStream inputStream = null;
        try {
            server = new ServerSocket(porta); 
            System.out.println("Servidor iniciado no IP: " + InetAddress.getLocalHost().getHostAddress() +
                               " Porta: " + porta);
            socketServidor = server.accept();
            System.out.println("Cliente conectado do IP "+socketServidor.getInetAddress().getHostAddress());
            inputStream = socketServidor.getInputStream();
            
            int chavePublicaRemota;
            try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
                chavePublicaRemota = dataInputStream.readInt();
                
                dh.setChavePublicaRemota(chavePublicaRemota);
                int chaveSessao = dh.getChaveSessao();
                this.textAreaChat.append(Integer.toString(chaveSessao));
                
                chavePublicaRemota = dataInputStream.readInt();
                while (chavePublicaRemota < 0) {
                    //Aguarda próxima entrada
                }
                
                
                while(true){
                byteLido = (byte) inputStream.read();
            
                insereMsgChat(modoOpSetado.recebeMensagem(byteLido, criptoSetado));
            }
            }
        }
        catch (IOException ex) {
            Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (socketServidor != null) {
                socketServidor.close();
            }
            if (server != null) {
                server.close();
            }
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
        painelDH = new javax.swing.JPanel();
        labelPartidaPrimo = new javax.swing.JLabel();
        textFieldPontoPartida = new javax.swing.JTextField();
        labelRaizes = new javax.swing.JLabel();
        botaoGerarPrimo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaRaizes = new javax.swing.JTextArea();
        labelPrimoGerado = new javax.swing.JLabel();
        textFieldPrimoGerado = new javax.swing.JTextField();
        labelRaizSelecionada = new javax.swing.JLabel();
        textFieldRaizSelecionada = new javax.swing.JTextField();
        lebelSelecionaChavePriv = new javax.swing.JLabel();
        textFieldChavePriv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textFieldChavePub = new javax.swing.JTextField();
        labelChaveSessao = new javax.swing.JLabel();
        textFieldChaveSessao = new javax.swing.JTextField();
        botaoGerarChavePubDH = new javax.swing.JButton();

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

        javax.swing.GroupLayout painelModoOpLayout = new javax.swing.GroupLayout(painelModoOp);
        painelModoOp.setLayout(painelModoOpLayout);
        painelModoOpLayout.setHorizontalGroup(
            painelModoOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelModoOpLayout.setVerticalGroup(
            painelModoOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 202, Short.MAX_VALUE)
        );

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

        labelPartidaPrimo.setText("Digite um ponto de partida para gerar um primo:");

        labelRaizes.setText("Raízes primitivas:");

        botaoGerarPrimo.setText("Gerar primo");
        botaoGerarPrimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarPrimoActionPerformed(evt);
            }
        });

        textAreaRaizes.setColumns(20);
        textAreaRaizes.setRows(5);
        textAreaRaizes.setLineWrap(true);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setViewportView(textAreaRaizes);

        labelPrimoGerado.setText("Primo selecionado:");

        labelRaizSelecionada.setText("Selecione uma raíz:");

        lebelSelecionaChavePriv.setText("Selecione uma chave privada (< primo):");

        textFieldChavePriv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldChavePrivActionPerformed(evt);
            }
        });

        jLabel2.setText("Chave Pública:");

        labelChaveSessao.setText("Chave de sessão:");

        botaoGerarChavePubDH.setText("Gerar chave Pub");
        botaoGerarChavePubDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarChavePubDHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelDHLayout = new javax.swing.GroupLayout(painelDH);
        painelDH.setLayout(painelDHLayout);
        painelDHLayout.setHorizontalGroup(
            painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(painelDHLayout.createSequentialGroup()
                        .addGroup(painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDHLayout.createSequentialGroup()
                                .addComponent(labelPartidaPrimo)
                                .addGap(12, 12, 12)
                                .addComponent(textFieldPontoPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoGerarPrimo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelDHLayout.createSequentialGroup()
                                .addGroup(painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelDHLayout.createSequentialGroup()
                                        .addComponent(labelPrimoGerado)
                                        .addGap(38, 38, 38)
                                        .addComponent(textFieldPrimoGerado, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelRaizes))
                                    .addGroup(painelDHLayout.createSequentialGroup()
                                        .addComponent(labelChaveSessao)
                                        .addGap(18, 18, 18)
                                        .addComponent(textFieldChaveSessao, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(painelDHLayout.createSequentialGroup()
                                .addGroup(painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelRaizSelecionada)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(textFieldChavePub, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                    .addComponent(textFieldRaizSelecionada))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addGroup(painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDHLayout.createSequentialGroup()
                                        .addComponent(lebelSelecionaChavePriv)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textFieldChavePriv, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(botaoGerarChavePubDH, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addContainerGap())))
        );
        painelDHLayout.setVerticalGroup(
            painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDHLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPartidaPrimo)
                    .addGroup(painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textFieldPontoPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoGerarPrimo)))
                .addGap(6, 6, 6)
                .addGroup(painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPrimoGerado)
                    .addComponent(textFieldPrimoGerado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRaizes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRaizSelecionada)
                    .addComponent(textFieldRaizSelecionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lebelSelecionaChavePriv)
                    .addComponent(textFieldChavePriv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoGerarChavePubDH)
                    .addGroup(painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(textFieldChavePub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(painelDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelChaveSessao)
                    .addComponent(textFieldChaveSessao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(painelDH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(123, 123, 123)
                                        .addComponent(labelIp))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelLocal)
                                            .addComponent(labelRemoto))
                                        .addGap(39, 39, 39)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(campoIpLocal, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                            .addComponent(campoIpRemoto))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoPortaLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(labelPorta))
                                    .addComponent(campoPortaRemota, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botaoAbrirConexao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(botaoConectar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(painelModoOp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelChave)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(labelAlgoritmo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(radioSDES)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(radioRC4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addComponent(campoChave)))
                                .addGap(19, 19, 19)
                                .addComponent(botaoAtualizaChave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelModoOp)
                                .addGap(50, 50, 50)
                                .addComponent(radioECB)
                                .addGap(18, 18, 18)
                                .addComponent(radioCBC)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNomeUsuario)
                    .addComponent(labelUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelIp, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelPorta, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLocal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoIpLocal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoPortaLocal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAbrirConexao, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelRemoto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoIpRemoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoPortaRemota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoConectar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAlgoritmo)
                    .addComponent(radioSDES)
                    .addComponent(radioRC4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoChave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelChave)
                    .addComponent(botaoAtualizaChave))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(labelModoOp))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioECB)
                        .addComponent(radioCBC)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
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

        this.criptoSetado.setChave(campoChave.getText());
    }//GEN-LAST:event_botaoAtualizaChaveActionPerformed

    private void botaoConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConectarActionPerformed
        String host = this.campoIpRemoto.getText();
        int porta = Integer.parseInt(this.campoPortaRemota.getText());
        initClient(host, porta);
        OutputStream saida = null;
        try {
            saida = cliente.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            try (DataOutputStream dataOutputStream = new DataOutputStream(saida)) {
                dataOutputStream.writeInt(dh.geraChavePublicaLocal());
                dataOutputStream.flush();
                dataOutputStream.writeInt(-1);
                //dataOutputStream.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void botaoGerarPrimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarPrimoActionPerformed
        // TODO add your handling code here:
        int pontoPartida = Integer.parseInt(this.textFieldPontoPartida.getText());
        int primo = this.dh.geraPrimo(pontoPartida);
        List<BigInteger> raizesPrimitivas = this.dh.geraRaizesPrimitivaNrPrimo();
        Collections.sort(raizesPrimitivas);
        this.textFieldPrimoGerado.setText(String.valueOf(primo));
        for (var i : raizesPrimitivas){
            this.textAreaRaizes.append(i + " ");
        }
    }//GEN-LAST:event_botaoGerarPrimoActionPerformed

    private void textFieldChavePrivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldChavePrivActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldChavePrivActionPerformed

    private void botaoGerarChavePubDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarChavePubDHActionPerformed
        // TODO add your handling code here:
        int raizSelecionada = Integer.parseInt(this.textFieldRaizSelecionada.getText());
        this.dh.setAlfa(raizSelecionada);
        this.dh.setChavePriv(Integer.parseInt(this.textFieldChavePriv.getText()));
        int chavePub = this.dh.geraChavePublicaLocal();
        this.textFieldChavePub.setText(Integer.toString(chavePub));
    }//GEN-LAST:event_botaoGerarChavePubDHActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAbrirConexao;
    private javax.swing.JButton botaoAtualizaChave;
    private javax.swing.JButton botaoConectar;
    private javax.swing.JButton botaoEnviar;
    private javax.swing.JButton botaoGerarChavePubDH;
    private javax.swing.JButton botaoGerarPrimo;
    private javax.swing.JTextField campoChave;
    private javax.swing.JTextField campoIpLocal;
    private javax.swing.JTextField campoIpRemoto;
    private javax.swing.JTextField campoPortaLocal;
    private javax.swing.JTextField campoPortaRemota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelAlgoritmo;
    private javax.swing.JLabel labelChave;
    private javax.swing.JLabel labelChaveSessao;
    private javax.swing.JLabel labelIp;
    private javax.swing.JLabel labelLocal;
    private javax.swing.JLabel labelModoOp;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JLabel labelPartidaPrimo;
    private javax.swing.JLabel labelPorta;
    private javax.swing.JLabel labelPrimoGerado;
    private javax.swing.JLabel labelRaizSelecionada;
    private javax.swing.JLabel labelRaizes;
    private javax.swing.JLabel labelRemoto;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel lebelSelecionaChavePriv;
    private javax.swing.JPanel painelDH;
    private javax.swing.JPanel painelModoOp;
    private javax.swing.JRadioButton radioCBC;
    private javax.swing.JRadioButton radioECB;
    private javax.swing.JRadioButton radioRC4;
    private javax.swing.JRadioButton radioSDES;
    public javax.swing.JTextArea textAreaChat;
    private javax.swing.JTextArea textAreaMsg;
    private javax.swing.JTextArea textAreaRaizes;
    private javax.swing.JTextField textFieldChavePriv;
    private javax.swing.JTextField textFieldChavePub;
    private javax.swing.JTextField textFieldChaveSessao;
    private javax.swing.JTextField textFieldPontoPartida;
    private javax.swing.JTextField textFieldPrimoGerado;
    private javax.swing.JTextField textFieldRaizSelecionada;
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
    private DiffieHellman dh;
    
    public void insereMsgChat(byte byteMsg){
        String msg = new String();
        msg += (char) byteMsg;
        this.textAreaChat.append(msg);
        this.textAreaChat.setCaretPosition(this.textAreaChat.getText().length());
    }
}