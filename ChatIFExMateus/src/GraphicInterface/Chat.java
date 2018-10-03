package GraphicInterface;


import EmissorFila.Emissor;
import EmissorFila.Receptor;
import EmissorSub.EmissorSub;
import EmissorSub.ReceptorSub;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class Chat extends javax.swing.JFrame {

    /**
     * Creates new form Char
     */
    private static String meuChat = "mateus";
    private static String chatGeral = "cstads";
    
    private Emissor emissorOneToOne;
    private EmissorSub emissorOneToMany;
    private Receptor receptorOneToOne;
    private ReceptorSub receptorOneToMany;
    
    public Chat() throws IOException, TimeoutException {
        initComponents();
        receptorOneToOne = new Receptor(this);
        receptorOneToMany = new ReceptorSub(this);
        emissorOneToMany = new EmissorSub();
        emissorOneToOne = new Emissor();
               

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        enviarButton = new javax.swing.JButton();
        membrosChat = new javax.swing.JComboBox<>();
        textoChat = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        telaChat = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(51, 153, 255));

        jPanel.setBackground(new java.awt.Color(0, 0, 102));

        enviarButton.setBackground(new java.awt.Color(0, 0, 102));
        enviarButton.setForeground(new java.awt.Color(255, 255, 255));
        enviarButton.setText("Enviar");
        enviarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarButtonActionPerformed(evt);
            }
        });

        membrosChat.setBackground(new java.awt.Color(0, 0, 102));
        membrosChat.setForeground(new java.awt.Color(255, 255, 255));
        membrosChat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[CSTADS]", "Aldir", "Bruno", "Fernanda", "Frutos", "Joao", "Julian", "Luciano", "Matheus", "Vinicius", "Wellington", " " }));
        membrosChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                membrosChatActionPerformed(evt);
            }
        });

        telaChat.setEditable(false);
        telaChat.setColumns(20);
        telaChat.setRows(5);
        jScrollPane1.setViewportView(telaChat);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ChAtBoLaDaO");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(textoChat, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(enviarButton))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(membrosChat, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(membrosChat, javax.swing.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoChat)
                    .addComponent(enviarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarButtonActionPerformed
                
        int index = membrosChat.getSelectedIndex();
        String destinatario = membrosChat.getItemAt(index);
        String mensagem = "[MATEUS]: "+textoChat.getText();
        textoChat.setText("");
        
        if(index == 0){
            try {
                emissorOneToMany.enviarMensagem(chatGeral, mensagem);
                inserirLinha(mensagem, "[CSTADS]");
            } catch (IOException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                emissorOneToOne.enviarMensagem(destinatario, mensagem);
                inserirLinha(mensagem, destinatario);
            } catch (IOException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    
    }//GEN-LAST:event_enviarButtonActionPerformed

    private void membrosChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_membrosChatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_membrosChatActionPerformed
      
    public void inserirLinha(String msg, String destinatario){
        this.telaChat.append(msg+"    --|"+ destinatario+"|--\n");
    }
    public void inserirLinhaReceptor(String msg){
        this.telaChat.append(msg+"\n");
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                           
                try {
                    new Chat().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TimeoutException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
            }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton enviarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> membrosChat;
    private javax.swing.JTextArea telaChat;
    private javax.swing.JTextField textoChat;
    // End of variables declaration//GEN-END:variables
}
