# exerciciochatseguro
Exercicio da disciplina de Segurança Computacional da UNIFESP

A intenção do projeto era implementar as seguintes funcionalidades:
- Algoritmo de criptografia de bloco SDES
- Algoritmo de criptografia de fluxo RC4
- Modo de operação de cifras em blocos ECB
-  Modo de operação de cifras em blocos CBC
-  Algoritmo de troca de chaves Diffie-Hellman

Como usar o programa:
1. Baixe o projeto em sua máquina
2. Execute, em sua pasta, o arquivo de compilação de acordo com seu SO: compila_Windows.bat ou compila_Ubuntu.sh
3. Execute o arquivo para início do programa de acordo com o perfil desejado:
   
   a. Alice_e_Bob -> Para executar na mesma máquina dois usuários
   
   b. Alice -> Para executar apenas o usuário Alice para troca de mensagens com outro usuário em outra máquina
   
   c. Bob -> Para executar apenas o usuário Bob para troca de mensagens com outro usuário em outra máquina
   
   d. UNomeDoUsuario -> Renomeie o arquivo substituindo "NomeDoUsuario" pelo nome desejado, mas mantenha a letra "U" no nome do arquivo
   
4. Já com o programa em execução:
   
   a. Em um dos usuários clique em "Abrir Conexão"
   
   b. No outro usuário defina o IP e a porta para conexão ao usuário remoto
   
   c. Digite um ponto de partida para gerar um número primo e clique em "Gerar primo"
   
   c. Será gerado um número primo e suas raízes primitivas
   
   d. Selecione uma das raízes primitivas geradas e selecione a chave privada que deve ter valor: 0 < chave privada < primo
   
   e. Clique em "Gerar chave Pub"
   
   f. Neste mesmo usuário, clique em "Conectar" e depois em "Abrir Conexão"
   
   g. No outro usuário, ajuste o IP e porta para conexão e clique em "Conectar"
   
   h. A partir deste ponto poderá ser realizada a troca de mensagens
   
   i. Poderá ser modificado o algoritmo de criptografia a ser utilizado, selecionando os radiobuttons SDES ou RC4
   
   j. Também poderá ser modificado o modo de operação a ser utilizado, selecionando os radiobuttons ECB ou CBC
