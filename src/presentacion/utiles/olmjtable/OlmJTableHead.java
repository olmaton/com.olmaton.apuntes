package presentacion.utiles.olmjtable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import javax.swing.SwingConstants;

/**
 *
 * @author olmaton
 */
public class OlmJTableHead {

    private ArrayList<OlmJTableHeadContent> contenido = new ArrayList<>();

    public OlmJTableHead() {
    }
    
    public void addColumn(String title, int zise, int align, Color background, Color foreground) {
        contenido.add(new OlmJTableHeadContent(contenido.size(), title, zise, align, background, foreground));
    }

    public void addColumn(String title, int zise, int align, Color background) {
        contenido.add(new OlmJTableHeadContent(contenido.size(), title, zise, align, background, null));
    }

    public void addColumn(String title, int zise, int align) {
        contenido.add(new OlmJTableHeadContent(contenido.size(), title, zise, align, null, null));
    }

    public void addColumn(String title, int zise) {
        contenido.add(new OlmJTableHeadContent(contenido.size(), title, zise, -1, null, null));
    }

    public void addColumn(String title) {
        contenido.add(new OlmJTableHeadContent(contenido.size(), title, -1, -1, null, null));
    }

    public String[] getHead() {
        return Arrays.copyOf(contenido.stream().map(item -> Objects.toString(item.title, "-")).toArray(), contenido.size(), String[].class);
    }

    public int[] getSizeWith() {
        return contenido.stream().mapToInt(item -> item.zise).toArray();
    }

    public HashMap<Integer, Integer> getAlign() {
        HashMap<Integer, Integer> retorno = new HashMap<>();
        for (OlmJTableHeadContent item : contenido) {
            if(item.align==-1){
                retorno.put(item.idx, SwingConstants.LEFT);
            }else{
                retorno.put(item.idx, item.align);
            }            
        }
        return retorno;
    }
    
    public HashMap<Integer, Color> getBackground() {
        HashMap<Integer, Color> retorno = new HashMap<>();
        for (OlmJTableHeadContent item : contenido) {
            if(item.background!=null){
                retorno.put(item.idx, item.background);
            }
        }
        return retorno;
    }
    
    public HashMap<Integer, Color> getForeground() {
        HashMap<Integer, Color> retorno = new HashMap<>();
        for (OlmJTableHeadContent item : contenido) {
            if(item.foreground!=null){
                retorno.put(item.idx, item.foreground);
            }
        }
        return retorno;
    }

    public int getSize() {
        return contenido.size();
    }

    private class OlmJTableHeadContent {

        int idx = 0;
        private String title = "-";
        private int zise = -1;
        private int align = -1;
        private Color background;
        private Color foreground;

        private OlmJTableHeadContent(int idx, String title, int zise, int align, Color background, Color foreground) {
            this.idx = idx;
            this.title = title;
            this.zise = zise;
            this.align = align;
            this.background = background;
            this.foreground = foreground;
        }
    }
}
