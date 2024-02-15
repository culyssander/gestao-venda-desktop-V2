package com.dxc.gestao.venda.visao.formulario;

import com.dxc.gestao.venda.visao.componentes.BarraDeRolar;
import com.dxc.gestao.venda.visao.componentes.Botao;
import com.dxc.gestao.venda.visao.componentes.CampoDeTexto;
import com.dxc.gestao.venda.visao.componentes.ComboBox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;

public class FormularioInfoProdutoCategoria extends javax.swing.JLayeredPane {
   
    private CampoDeTexto textoNomeCategoria;
    private CampoDeTexto textoDescricaoCategoria;
    private JButton botaoResetCategoria;
    private Botao botaoCategoria;
    
    private CampoDeTexto textoPrecoProduto;
    private CampoDeTexto textoNomeProduto;
    private CampoDeTexto textoDescricaoProduto;
    private ComboBox comboBoxCategoriaProduto;
    private Botao botaoProduto;
    
    
    public FormularioInfoProdutoCategoria() {
        initComponents();
        inicializacaoDoProduto();
        inicializacaoDaCategoria();
        
        categoria.setVisible(false);
        produto.setVisible(true);
    }
    
    private void inicializacaoDoProduto() {
        produto.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]15[]push"));
        JLabel label = new JLabel("Registro de produto");
        label.setFont(new Font("sansserif", 1, 20));
        label.setForeground(Color.decode("#1cb5e0"));
        produto.add(label);
        
        String caminho = "\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\";
        
        textoNomeProduto = new CampoDeTexto();
        textoNomeProduto.setDica("Nome");
        textoNomeProduto.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "produto1.png"));
        produto.add(textoNomeProduto, "w 60%");
        
        textoPrecoProduto = new CampoDeTexto();
        textoPrecoProduto.setDica("Preço");
        textoPrecoProduto.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "preco.png"));
        produto.add(textoPrecoProduto, "w 60%");
        
        textoDescricaoProduto = new CampoDeTexto();
        textoDescricaoProduto.setDica("Descrição");
        textoDescricaoProduto.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "descricao.png"));
        produto.add(textoDescricaoProduto, "w 60%");
        
        comboBoxCategoriaProduto = new ComboBox();
        comboBoxCategoriaProduto.addItem("Seleciona a categoria");
        produto.add(comboBoxCategoriaProduto, "w 60%, h 35");
                
        botaoProduto = new Botao();
        botaoProduto.setBackground(new Color(28, 181, 224));
        botaoProduto.setForeground(Color.WHITE);
        botaoProduto.setFont(new Font("sansserif", 5, 14));
        botaoProduto.setText("Salvar");
        produto.add(botaoProduto, "w 40%, h 40");
    }
    
    private void inicializacaoDaCategoria() {
        categoria.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]50[]push"));
        JLabel label = new JLabel("Registro de Categoria");
        label.setFont(new Font("sansserif", 1, 20));
        label.setForeground(Color.decode("#1cb5e0"));
        categoria.add(label);
        
        String caminho = "\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\";
        
        textoNomeCategoria = new CampoDeTexto();
        textoNomeCategoria.setDica("Nome");
        textoNomeCategoria.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "produto1.png"));
        categoria.add(textoNomeCategoria, "w 60%");
        
        textoDescricaoCategoria = new CampoDeTexto();
        textoDescricaoCategoria.setDica("Descrição");
        textoDescricaoCategoria.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "descricao.png"));
        categoria.add(textoDescricaoCategoria, "w 60%");
        
        botaoResetCategoria = new JButton();
        botaoResetCategoria.setContentAreaFilled(false);
        botaoResetCategoria.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoResetCategoria.setForeground(Color.decode("#1cb5e0"));
        botaoResetCategoria.setText("Limpa");
        categoria.add(botaoResetCategoria);
        
        botaoCategoria = new Botao();
        botaoCategoria.setBackground(new Color(28, 181, 224));
        botaoCategoria.setForeground(Color.WHITE);
        botaoCategoria.setFont(new Font("sansserif", 5, 14));
        botaoCategoria.setText("Salvar");
        categoria.add(botaoCategoria, "w 40%, h 40");
        
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setVerticalScrollBar(new BarraDeRolar());
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        categoria.add(jScrollPane1, "w 90%, h 40%");
    }
    
    public void mostrarProduto(boolean mostrar) {
        if (mostrar) {
            produto.setVisible(true);
            categoria.setVisible(false);
        } else {
            produto.setVisible(false);
            categoria.setVisible(true);
        }
    }
    
    public JTable getTabelaCategoria() {
        return tabela1;
    }

    public CampoDeTexto getTextoNomeCategoria() {
        return textoNomeCategoria;
    }

    public CampoDeTexto getTextoDescricaoCategoria() {
        return textoDescricaoCategoria;
    }

    public CampoDeTexto getTextoDescricaoProduto() {
        return textoDescricaoProduto;
    }

    public CampoDeTexto getTextoNomeProduto() {
        return textoNomeProduto;
    }

    public CampoDeTexto getTextoPrecoProduto() {
        return textoPrecoProduto;
    }

    public ComboBox getComboBoxCategoriaProduto() {
        return comboBoxCategoriaProduto;
    }

    public Botao getBotaoCategoria() {
        return botaoCategoria;
    }

    public Botao getBotaoProduto() {
        return botaoProduto;
    }

    public JButton getBotaoResetCategoria() {
        return botaoResetCategoria;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        produto = new javax.swing.JPanel();
        categoria = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela1 = new com.dxc.gestao.venda.visao.componentes.Tabela();

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
    private javax.swing.JPanel categoria;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel produto;
    private com.dxc.gestao.venda.visao.componentes.Tabela tabela1;
    // End of variables declaration//GEN-END:variables
}
