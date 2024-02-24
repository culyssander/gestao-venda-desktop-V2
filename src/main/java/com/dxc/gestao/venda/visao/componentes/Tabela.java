package com.dxc.gestao.venda.visao.componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Tabela extends JTable {

    public Tabela() {
        setShowHorizontalLines(true);
        setRowHeight(30);
        setGridColor(new Color(230, 230, 230));
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TabelaCabecalho tabelaCabecalho = new TabelaCabecalho(value + "");
                return tabelaCabecalho;
            }
            
        });
        
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                if (value != null) {
                    
                    switch(value.toString()) {
                        case "ENTRADA" -> {return label(value.toString(), Color.decode("#17F53C"));}
                        case "SAIDA" -> {return label(value.toString(), Color.decode("#F71A0F")); }
                        case "REMOVER" -> { return botao("Remover");}
                    }
                }
                
//                if(column != 3) {
                    Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    com.setBackground(Color.WHITE);
                    setBorder(noFocusBorder);
                    
                    if(isSelected) {
                        com.setForeground(new Color(13, 113, 182));
                    } else {
                        com.setForeground(new Color(102, 102, 102));
                    }
                    
                    return com;
//                }
                
            }
            
        });
    }

    public JLabel label(String texto, Color color) {
        var label = new JLabel();
        label.setOpaque(false);
        label.setText(texto);
        label.setFont(new Font("sansserif", 5, 13));
        label.setForeground(color);
        return label;
    }
    
    public JButton botao(String texto) {
        BotaoContorno botao = new BotaoContorno();
        botao.setText(texto);
        botao.setForeground(new Color(204, 0, 0));
        botao.setBackground(new Color(204, 0, 0));
        return botao;
    }

}
