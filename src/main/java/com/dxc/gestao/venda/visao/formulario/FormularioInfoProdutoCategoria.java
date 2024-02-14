package com.dxc.gestao.venda.visao.formulario;

import com.dxc.gestao.venda.modelo.entidade.Categoria;
import com.dxc.gestao.venda.modelo.tabela.modelo.CategoriaModelo;
import com.dxc.gestao.venda.visao.componentes.BarraDeRolar;
import com.dxc.gestao.venda.visao.componentes.Botao;
import com.dxc.gestao.venda.visao.componentes.CampoDeTexto;
import com.dxc.gestao.venda.visao.componentes.ComboBox;
import com.dxc.gestao.venda.visao.componentes.Tabela;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class FormularioInfoProdutoCategoria extends javax.swing.JLayeredPane {
   
    
    public FormularioInfoProdutoCategoria() {
        initComponents();
        inicializacaoDoProduto();
        inicializacaoDaCategoria();
        
        categoria.setVisible(true);
        produto.setVisible(false);
    }
    
    private void inicializacaoDoProduto() {
        produto.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]15[]push"));
        JLabel label = new JLabel("Registro de produto");
        label.setFont(new Font("sansserif", 1, 20));
        label.setForeground(Color.decode("#1cb5e0"));
        produto.add(label);
        
        String caminho = "\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\";
        
        CampoDeTexto textoNome = new CampoDeTexto();
        textoNome.setDica("Nome");
        textoNome.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "produto1.png"));
        produto.add(textoNome, "w 60%");
        
        CampoDeTexto textoPreco = new CampoDeTexto();
        textoPreco.setDica("Preço");
        textoPreco.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "preco.png"));
        produto.add(textoPreco, "w 60%");
        
        CampoDeTexto textoDescricao = new CampoDeTexto();
        textoDescricao.setDica("Descrição");
        textoDescricao.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "descricao.png"));
        produto.add(textoDescricao, "w 60%");
        
        ComboBox comboCategoria = new ComboBox();
        comboCategoria.addItem("Seleciona a categoria");
//        comboCategoria.setForeground(Color.decode("#1D1B86"));
        produto.add(comboCategoria, "w 60%, h 35");
        
        
        Botao botao = new Botao();
        botao.setBackground(new Color(28, 181, 224));
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("sansserif", 5, 14));
        botao.setText("Salvar");
        produto.add(botao, "w 40%, h 40");
    }
    
    private void inicializacaoDaCategoria() {
        categoria.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]50[]push"));
        JLabel label = new JLabel("Registro de Categoria");
        label.setFont(new Font("sansserif", 1, 20));
        label.setForeground(Color.decode("#1cb5e0"));
        categoria.add(label);
        
        String caminho = "\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\";
        
        CampoDeTexto textoNome = new CampoDeTexto();
        textoNome.setDica("Nome");
        textoNome.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "produto1.png"));
        categoria.add(textoNome, "w 60%");
        
        CampoDeTexto textoDescricao = new CampoDeTexto();
        textoDescricao.setDica("Descrição");
        textoDescricao.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "descricao.png"));
        categoria.add(textoDescricao, "w 60%");
        
        JButton botaoReset = new JButton();
        botaoReset.setContentAreaFilled(false);
        botaoReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoReset.setForeground(Color.decode("#1cb5e0"));
        botaoReset.setText("Limpa");
        categoria.add(botaoReset);
        
        Botao botao = new Botao();
        botao.setBackground(new Color(28, 181, 224));
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("sansserif", 5, 14));
        botao.setText("Salvar");
        categoria.add(botao, "w 40%, h 40");
        
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setVerticalScrollBar(new BarraDeRolar());
        categoria.add(jScrollPane1, "w 90%, h 40%");
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        campoDeTexto1 = new com.dxc.gestao.venda.visao.componentes.CampoDeTexto();
        botao1 = new com.dxc.gestao.venda.visao.componentes.Botao();
        campoDeTexto2 = new com.dxc.gestao.venda.visao.componentes.CampoDeTexto();
        produto = new javax.swing.JPanel();
        categoria = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela1 = new com.dxc.gestao.venda.visao.componentes.Tabela();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(28, 181, 224));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registro da Categoria");

        campoDeTexto1.setDica("Nome");
        campoDeTexto1.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\produto1.png")); // NOI18N

        botao1.setBackground(new java.awt.Color(28, 181, 224));
        botao1.setForeground(new java.awt.Color(255, 255, 255));
        botao1.setText("Salvar");
        botao1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N

        campoDeTexto2.setDica("Descrição");
        campoDeTexto2.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\descricao.png")); // NOI18N

        setLayout(new java.awt.CardLayout());

        produto.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout produtoLayout = new javax.swing.GroupLayout(produto);
        produto.setLayout(produtoLayout);
        produtoLayout.setHorizontalGroup(
            produtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        produtoLayout.setVerticalGroup(
            produtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );

        add(produto, "card3");

        categoria.setBackground(new java.awt.Color(255, 255, 255));

        tabela1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabela1);

        javax.swing.GroupLayout categoriaLayout = new javax.swing.GroupLayout(categoria);
        categoria.setLayout(categoriaLayout);
        categoriaLayout.setHorizontalGroup(
            categoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        categoriaLayout.setVerticalGroup(
            categoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoriaLayout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        add(categoria, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dxc.gestao.venda.visao.componentes.Botao botao1;
    private com.dxc.gestao.venda.visao.componentes.CampoDeTexto campoDeTexto1;
    private com.dxc.gestao.venda.visao.componentes.CampoDeTexto campoDeTexto2;
    private javax.swing.JPanel categoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel produto;
    private com.dxc.gestao.venda.visao.componentes.Tabela tabela1;
    // End of variables declaration//GEN-END:variables
}
