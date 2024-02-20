package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.modelo.dto.ProdutoDto;
import com.dxc.gestao.venda.modelo.entidade.Categoria;
import com.dxc.gestao.venda.modelo.entidade.Produto;
import com.dxc.gestao.venda.modelo.servico.CategoriaServico;
import com.dxc.gestao.venda.modelo.servico.PermissaoServico;
import com.dxc.gestao.venda.modelo.servico.ProdutoServico;
import com.dxc.gestao.venda.modelo.tabela.modelo.CategoriaModelo;
import com.dxc.gestao.venda.modelo.tabela.modelo.ProdutoModelo;
import com.dxc.gestao.venda.visao.componentes.Mensagem;
import com.dxc.gestao.venda.visao.formulario.FormularioProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;

public class FormularioProdutoControlador implements ActionListener, KeyListener, MouseListener {
    
    private final FormularioProduto formularioProduto;
    private final ProdutoServico produtoServico;
    private final CategoriaServico categoriaServico;
    private final PermissaoServico permissaoServico;
    private ProdutoModelo produtoModelo;
    private CategoriaModelo categoriaModelo;
    private static List<ProdutoDto> produtos;
    private static List<Categoria> categorias;
    private static Long categoriaId;
    private static Long produtoId;
    private final long PERMISSAO_ID_PARA_SALVAR_PRODUTO = 7;
    private final long PERMISSAO_ID_PARA_REMOVER_PRODUTO = 8;
    private final long PERMISSAO_ID_PARA_SALVAR_CATEGORIA = 11;
    private final long PERMISSAO_ID_PARA_REMOVER_CATEGORIA = 12;
    
    public FormularioProdutoControlador(FormularioProduto formularioProduto) {
        this.formularioProduto = formularioProduto;
        produtoServico = new ProdutoServico();
        categoriaServico = new CategoriaServico();
        permissaoServico = new PermissaoServico();
        categorias = categoriaServico.encontrarTodos();
        categoriaModelo = new CategoriaModelo(categorias);
        atualizaTabela();
    }
    
    private void atualizaTabela() {
        produtos = produtoServico.encontrarTodos();
        produtoModelo = new ProdutoModelo(produtos);
        formularioProduto.getTabelaProduto().setModel(produtoModelo);
    }
    
    private void atualizaTabelaCategoria() {
        this.categorias = categoriaServico.encontrarTodos();
        
        categoriaModelo = new CategoriaModelo(categorias);
        formularioProduto.getFormulario().getProdutoCategoria().getTabelaCategoria().setModel(categoriaModelo);
        
        formularioProduto.getFormulario()
                    .getProdutoCategoria()
                    .getComboBoxCategoriaProduto()
                    .removeAllItems();
        
        formularioProduto.getFormulario()
                    .getProdutoCategoria()
                    .getComboBoxCategoriaProduto()
                    .addItem("Seleciona a categoria");
        
        categorias.forEach(c -> {
            formularioProduto.getFormulario()
                    .getProdutoCategoria()
                    .getComboBoxCategoriaProduto()
                    .addItem(c.getNome());
        });
    }
    
    private void validaCampo(String texto) {
        System.out.println("VALIDACAO: " + texto + " - " + texto.isBlank());
        if (texto.isBlank()){
            String mensagem = "Todos os campos sao obrigatorio.";
            formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
//            throw new RuntimeException(mensagem);
            return;
        }
    }
    
    private Categoria selecionaTabelaCategoria() {
        int index = formularioProduto.getFormulario().getProdutoCategoria().getTabelaCategoria().getSelectedRow();
        System.out.println("INDEX: " + index);
        if (index != -1) {
            Categoria categoria = categorias.get(index);
            categoriaId = categoria.getId();
            return categoria;
        }
        return null;
    }
    
    private void preencherOsCamposCategoria() {
        Categoria categoria = selecionaTabelaCategoria();
        
        if (categoria != null) {
            formularioProduto.getFormulario().getProdutoCategoria().getTextoNomeCategoria().setText(categoria.getNome());
            formularioProduto.getFormulario().getProdutoCategoria().getTextoDescricaoCategoria().setText(categoria.getDescricao());
        }
        
    }
    
    private void removeCategoria() {
        permissaoServico.validaPermissao(formularioProduto.getDashboard().getUsuario().getId(), PERMISSAO_ID_PARA_REMOVER_CATEGORIA);
        Categoria categoria = selecionaTabelaCategoria();
        int confirma = JOptionPane.showConfirmDialog(null, "Tens certeza?\n" +
                "Categoria: " + categoria.getNome()
                , "Remove categoria", JOptionPane.ERROR_MESSAGE);

        if (confirma == JOptionPane.YES_NO_OPTION) {
            String mensagem = categoriaServico.removerPeloId(categoria.getId());

            if (mensagem.startsWith("Categoria removido")) {
                formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.SUCESSO, mensagem);
                limpaCampoCategoria();
                atualizaTabelaCategoria();
            } else {
                formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
            }
        }
    }
    
    private void salvaCategoria() {
        formularioProduto.getFormulario().getProdutoCategoria().getBotaoCategoria().addActionListener(e -> {
            permissaoServico.validaPermissao(formularioProduto.getDashboard().getUsuario().getId(), PERMISSAO_ID_PARA_SALVAR_CATEGORIA);
            String nomeCategoria = formularioProduto.getFormulario().getProdutoCategoria().getTextoNomeCategoria().getText().trim();
            String descricaoCategoria = formularioProduto.getFormulario().getProdutoCategoria().getTextoDescricaoCategoria().getText().trim();

            validaCampo(nomeCategoria);

            Categoria categoria = Categoria.builder()
                    .id(categoriaId)
                    .nome(nomeCategoria)
                    .descricao(descricaoCategoria)
                    .build();
            
            String mensagem = categoriaServico.salva(categoria);
            
            if (mensagem.startsWith("Categoria salvando")) {
                formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.SUCESSO, mensagem);
                limpaCampoCategoria();
                atualizaTabelaCategoria();
            } else {
                formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
            }         
        });
    }
    
    private void limpaCampoCategoria() {
        categoriaId = null;
        formularioProduto.getFormulario().getProdutoCategoria().getTextoNomeCategoria().setText("");
        formularioProduto.getFormulario().getProdutoCategoria().getTextoDescricaoCategoria().setText("");
    }
    private void salvaProduto() {
        formularioProduto.getFormulario().getProdutoCategoria().getBotaoProduto().addActionListener(e -> {
            permissaoServico.validaPermissao(formularioProduto.getDashboard().getUsuario().getId(), PERMISSAO_ID_PARA_SALVAR_PRODUTO);
            String nome = formularioProduto.getFormulario().getProdutoCategoria().getTextoNomeProduto().getText().trim();
            String descricao = formularioProduto.getFormulario().getProdutoCategoria().getTextoDescricaoProduto().getText().trim();
            String precoString = formularioProduto.getFormulario().getProdutoCategoria().getTextoPrecoProduto().getText().trim();
            String categoriaNome  = formularioProduto.getFormulario().getProdutoCategoria().getComboBoxCategoriaProduto().getSelectedItem().toString();
            
            validaCampo(nome);
            validaCampo(precoString);
            validaCategoriaSelecionada();
            
            BigDecimal preco = converterStringParaDecimal(precoString);
            Categoria categoria = categoriaServico.encontrarPeloNome(categoriaNome);
            
            if (categoria == null) {
                String mensagem = "Categoria nao encontrada...";
                formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
                throw new RuntimeException(mensagem);
            }
            
            Produto produto = Produto.builder()
                    .id(produtoId)
                    .nome(nome)
                    .descricao(descricao)
                    .preco(preco)
                    .categoriaId(categoria.getId())
                    .usuarioId(formularioProduto.getUsuarioId())
                    .dataCriacao(LocalDateTime.now())
                    .build();
            
            String mensagem = produtoServico.salva(produto);
            if (mensagem.startsWith("Produto cadastrado")) {
                  formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.SUCESSO, mensagem);
                  limpaCampoProduto();
                  atualizaTabela();
                  formularioProduto.getDashboard().getFormularioPrincipal().setTotalProduto("Total " + produtos.size());
            } else {
                  formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
            }
        });
    }
    
    private void limpaCampoProduto() {
        produtoId = null;
        formularioProduto.getFormulario().getProdutoCategoria().getTextoNomeProduto().setText("");
        formularioProduto.getFormulario().getProdutoCategoria().getTextoDescricaoProduto().setText("");
        formularioProduto.getFormulario().getProdutoCategoria().getTextoPrecoProduto().setText("");
        formularioProduto.getFormulario().getProdutoCategoria().getComboBoxCategoriaProduto().setSelectedIndex(0);
    }

    private void validaCategoriaSelecionada() {
        if (formularioProduto.getFormulario().getProdutoCategoria().getComboBoxCategoriaProduto().getSelectedIndex() < 1) {
            String mensagem = "Categoria é um campo obrigatorio...";
            formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
//            throw new RuntimeException(mensagem);
            return;
        }
    }
       
    private BigDecimal converterStringParaDecimal(String texto) {
        try {
            return new BigDecimal(texto);
        } catch (Exception e) {
            String mensagem = "Deves preencher somente com numeros...";
            throw new RuntimeException(mensagem);
        }
    }

    public CategoriaModelo getCategoriaModelo() {
        return categoriaModelo;
    }
    
    private void botaoReset() {
        formularioProduto.getFormulario().getProdutoCategoria().getBotaoResetCategoria().addActionListener(e -> {
            limpaCampoCategoria();
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand().toLowerCase();
        System.out.println(action);
        
        switch(action) {
            case "adicionar" -> {adicionar();}
            case "atualizar" -> {alterarProduto();}
            case "remover" -> {remover();}
            case "imprimir" -> {}
        }
        
        salvaCategoria();
        salvaProduto();
        botaoReset();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        
        if (formularioProduto.getMenuSelectionadoIndex() == 1) {
            String texto = formularioProduto.getCabecalho().getPesquisar().getText().trim();
            encontraProdutoPeloAtributo(texto);
        }
    
    }
    
    private void encontraProdutoPeloAtributo(String texto) {
        if (texto.isBlank()) {
            atualizaTabela();
        } else {
            produtos = produtoServico.encontrarPeloAtributo(texto);
            produtoModelo = new ProdutoModelo(produtos);
            formularioProduto.getTabelaProduto().setModel(produtoModelo);
        }
    }

    private void adicionar() {
        formularioProduto.getDashboard().setForm(formularioProduto.getFormulario());
        limpaCampoProduto();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int colum = formularioProduto.getFormulario().getProdutoCategoria().getTabelaCategoria().getSelectedColumn();
        
        if (colum == 3) {
            removeCategoria();
        } else {
            preencherOsCamposCategoria();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void remover() {
        permissaoServico.validaPermissao(formularioProduto.getDashboard().getUsuario().getId(), PERMISSAO_ID_PARA_REMOVER_PRODUTO);
        ProdutoDto produtoDto = selecionaProdutoNaTabela();
        
        int confirma = JOptionPane.showConfirmDialog(null, "Tens certeza?\n" +
                 "\nNome: " + produtoDto.getNome() +
                 "\nPreço: " + produtoDto.getPreco() +
                 "\nCategoria: " + produtoDto.getCategoria().getNome(), 
                "Remover Produto", JOptionPane.ERROR_MESSAGE);
        
        if (confirma == JOptionPane.YES_OPTION) {
            String mensagem = produtoServico.remover(produtoDto.getId());
            
            if (mensagem.startsWith("Produto removido")) {
                JOptionPane.showMessageDialog(null, mensagem);
                atualizaTabela();
                formularioProduto.getDashboard().getFormularioPrincipal().setTotalProduto("Total " + produtos.size());
            } else {
                JOptionPane.showMessageDialog(formularioProduto, mensagem, "Remover Produto", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
 
    private ProdutoDto selecionaProdutoNaTabela() {
        int index = formularioProduto.getTabelaProduto().getSelectedRow();
        
        if(index != -1) {
            return produtos.get(index);
        }
        String mensagem = "Tens que seleciona um produto";
        JOptionPane.showMessageDialog(null, mensagem);
        
        throw new RuntimeException(mensagem);
    }

    private void alterarProduto() {
        ProdutoDto produtoDto = selecionaProdutoNaTabela();
        preencherCamposDoProduto(produtoDto);
        formularioProduto.getDashboard().setForm(formularioProduto.getFormulario());
    }
    
    private void preencherCamposDoProduto(ProdutoDto produtoDto) {
        produtoId = produtoDto.getId();
        formularioProduto.getFormulario().getProdutoCategoria().getTextoNomeProduto().setText(produtoDto.getNome());
        formularioProduto.getFormulario().getProdutoCategoria().getTextoDescricaoProduto().setText(produtoDto.getDescricao());
        formularioProduto.getFormulario().getProdutoCategoria().getTextoPrecoProduto().setText(produtoDto.getPreco().toString());
        formularioProduto.getFormulario().getProdutoCategoria().getComboBoxCategoriaProduto().setSelectedItem(produtoDto.getCategoria().getNome());
    }
}
