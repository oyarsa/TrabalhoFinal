/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula1.Visao;

import aula1.Controlador.CategoriaController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Italo
 */
public class Categoria extends javax.swing.JFrame {

    /**
     * Creates new form Categoria
     */
    public Categoria() {
        initComponents();
        desabilitarComponentes();
        carregarTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCodigo = new javax.swing.JLabel();
        textCodigo = new javax.swing.JTextField();
        labelDescricao = new javax.swing.JLabel();
        textDescricao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCategorias = new javax.swing.JTable();
        buttonNovo = new javax.swing.JButton();
        buttonEditar = new javax.swing.JButton();
        buttonExcluir = new javax.swing.JButton();
        buttonSalvar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelCodigo.setText("Código");

        textCodigo.setEnabled(false);
        textCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCodigoActionPerformed(evt);
            }
        });

        labelDescricao.setText("Descrição");

        tableCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Código", "Descrição"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCategoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCategorias);
        if (tableCategorias.getColumnModel().getColumnCount() > 0) {
            tableCategorias.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        buttonNovo.setText("Novo");
        buttonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNovoActionPerformed(evt);
            }
        });

        buttonEditar.setText("Editar");
        buttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarActionPerformed(evt);
            }
        });

        buttonExcluir.setText("Excluir");
        buttonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExcluirActionPerformed(evt);
            }
        });

        buttonSalvar.setText("Salvar");
        buttonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvarActionPerformed(evt);
            }
        });

        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Categorias");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(buttonNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancelar)
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelDescricao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textDescricao)))
                        .addContainerGap())))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo)
                    .addComponent(textCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDescricao)
                    .addComponent(textDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNovo)
                    .addComponent(buttonEditar)
                    .addComponent(buttonExcluir)
                    .addComponent(buttonSalvar)
                    .addComponent(buttonCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNovoActionPerformed
        limparComponentes();
        habilitarComponentes();
    }//GEN-LAST:event_buttonNovoActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        int linha = tableCategorias.getSelectedRow();
        if (linha == -1) {
            return;
        }

        habilitarComponentes();
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        desabilitarComponentes();

        int linha = tableCategorias.getSelectedRow();
        if (linha == -1) {
            return;
        }

        DefaultTableModel m = (DefaultTableModel) tableCategorias.getModel();

        String codigo = (String) m.getValueAt(linha, 0);
        CategoriaController.excluir(codigo);

        JOptionPane.showMessageDialog(this, "Operação executada com sucesso", "Sucesso",
                JOptionPane.PLAIN_MESSAGE);

        m.removeRow(linha);
    }//GEN-LAST:event_buttonExcluirActionPerformed

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
        desabilitarComponentes();

        int linha = tableCategorias.getSelectedRow();
        DefaultTableModel m = (DefaultTableModel) tableCategorias.getModel();

        if (linha == -1) {
            m.addRow(new Object[]{null, null});
            linha = m.getRowCount() - 1;
        }

        //String[] dados = {textCodigo.getText(), textDescricao.getText()};
        JsonObject dados = new JsonObject();
        dados.addProperty("codigo", textCodigo.getText());
        dados.addProperty("descricao", textDescricao.getText());
        String json = CategoriaController.salvar(dados.toString());

        JsonObject result = new Gson().fromJson(json, JsonObject.class);

        if (result.has("mensagem")) {
            JOptionPane.showMessageDialog(this, result.get("mensagem").getAsString(), 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            habilitarComponentes();
            return;
        }

        m.setValueAt(result.get("codigo").getAsInt(), linha, 0);
        m.setValueAt(textDescricao.getText(), linha, 1);
    }//GEN-LAST:event_buttonSalvarActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        limparComponentes();
        desabilitarComponentes();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void tableCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCategoriasMouseClicked
        int linha = tableCategorias.getSelectedRow();
        if (linha == -1) {
            return;
        }

        DefaultTableModel m = (DefaultTableModel) tableCategorias.getModel();

        String codigo = m.getValueAt(linha, 0).toString();
        String json = CategoriaController.recuperar(codigo);
        
        JsonObject dados = new Gson().fromJson(json, JsonObject.class);
        
        textCodigo.setText(dados.get("codigo").getAsString());
        textDescricao.setText(dados.get("descricao").getAsString());
    }//GEN-LAST:event_tableCategoriasMouseClicked

    private void textCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCodigoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonEditar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonNovo;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JTable tableCategorias;
    private javax.swing.JTextField textCodigo;
    private javax.swing.JTextField textDescricao;
    // End of variables declaration//GEN-END:variables

    private void limparComponentes() {
        textCodigo.setText("0");
        textDescricao.setText("");
        tableCategorias.clearSelection();
    }

    private void habilitarComponentes() {
        configurarComponentes(true);
    }

    private void desabilitarComponentes() {
        configurarComponentes(false);
    }

    private void configurarComponentes(boolean ativo) {
        textDescricao.setEditable(ativo);

        buttonNovo.setEnabled(!ativo);
        buttonEditar.setEnabled(!ativo);
        buttonSalvar.setEnabled(ativo);
        buttonCancelar.setEnabled(ativo);

        tableCategorias.setEnabled(!ativo);
    }

    private void carregarTabela() {
        DefaultTableModel m = (DefaultTableModel) tableCategorias.getModel();
        m.setRowCount(0);

        List<String> colecao = CategoriaController.recuperarTodos();

        for (String json : colecao) {
            JsonObject dados = new Gson().fromJson(json, JsonObject.class);
            Object[] linha = {dados.get("codigo").getAsInt(), dados.get("descricao").getAsString()};
            m.addRow(linha);
        }
    }
}
