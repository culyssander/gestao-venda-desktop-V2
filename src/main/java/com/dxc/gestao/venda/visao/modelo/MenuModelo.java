package com.dxc.gestao.venda.visao.modelo;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuModelo {
    private String icon;
    private String nome;
    private TipoMenu tipoMenu;
    
    public enum TipoMenu {
        MENU, TITULO, VAZIO;
    }
    
    public Icon toIcon() {
        return new ImageIcon(System.getProperty("user.dir")  + "\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\" + icon + ".png");
    }
}
