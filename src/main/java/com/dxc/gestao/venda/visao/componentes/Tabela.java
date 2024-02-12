package com.dxc.gestao.venda.visao.componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
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
                
//                if (column == 3) {
//                    tabelaCabecalho.setHorizontalAlignment(JLabel.CENTER);
//                }
                
                return tabelaCabecalho;
            }
            
        });
        
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                if (value != null) {
                   if (value.toString().equals("ENTRADA")) {
                    var label = new JLabel();
                    label.setOpaque(true);
                    label.setText(String.valueOf(value));
                    label.setBackground(new Color(147, 249, 185));
                    label.setForeground(Color.WHITE);
                    return label;
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
    
    
}
