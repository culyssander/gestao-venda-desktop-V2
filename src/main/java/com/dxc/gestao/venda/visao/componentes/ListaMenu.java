package com.dxc.gestao.venda.visao.componentes;

import com.dxc.gestao.venda.visao.evento.EventoMenuSelectionado;
import com.dxc.gestao.venda.visao.modelo.MenuModelo;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class ListaMenu<E extends Object> extends JList<E>{
    
    private final DefaultListModel model;
    private int selectedIndex = -1;
    private int overIndex = -1;
    private EventoMenuSelectionado evento;
    
    public void addEventoMenuSelecionado(EventoMenuSelectionado evento) {
        this.evento = evento;
    }

    public ListaMenu() {
        setOpaque(false);
        this.model = new DefaultListModel();
        setModel(model);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) {
                    int index = locationToIndex(e.getPoint());
                    
                    Object o = model.getElementAt(index);
                    if (o instanceof MenuModelo) {
                        MenuModelo menuModelo = (MenuModelo) o;
                        
                        if (menuModelo.getTipoMenu().equals(MenuModelo.TipoMenu.MENU)) {
                            selectedIndex = index;
                            if (evento != null) {
                                evento.selectionado(index);
                            }
                        }
                    } else {
                        selectedIndex = index;
                    }
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                overIndex = -1;
                repaint();
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = locationToIndex(e.getPoint()); 
                
                if (index != overIndex) {
                    Object o = model.getElementAt(index);
                    
                    if (o instanceof MenuModelo) {
                        MenuModelo menuModelo = (MenuModelo) o;
                        
                        if (menuModelo.getTipoMenu().equals(MenuModelo.TipoMenu.MENU)) {
                            overIndex = index;
                        } else {
                            overIndex = -1;
                        }
                        
                        repaint();
                    }
                }
            }
            
        });
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                MenuModelo menuModelo;
                
                if (value instanceof MenuModelo) {
                    menuModelo = (MenuModelo) value;
                } else {
                    menuModelo = new MenuModelo("", value +" ", MenuModelo.TipoMenu.VAZIO);
                }
                  MenuItem menuItem = new MenuItem(menuModelo);
                  menuItem.setSelected(selectedIndex == index);
                  menuItem.setOver(overIndex == index);
                  return menuItem;
            }
        };
    }
    
    public void adicionarItem(MenuModelo menuModelo) {
        model.addElement(menuModelo);
        
        if (menuModelo.getNome().equals("Dashboard")) {
            selectedIndex = 0;
        }
    }
    
}
