/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pengeluaran.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import pengeluaran.entity.Kategori;
import pengeluaran.service.KategoriServiceLocal;

/**
 *
 * @author endy
 */
@FacesConverter(forClass=Kategori.class)
public class KategoriConverter implements Converter{

    private KategoriServiceLocal kategoriService;
    
    public KategoriConverter() {
        try {
            String ejbName = "java:global/aplikasi-catat-pengeluaran/aplikasi-catat-pengeluaran-ejb/KategoriService";
            Context ctx = new InitialContext();
            kategoriService = (KategoriServiceLocal) ctx.lookup(ejbName);
        } catch (NamingException ex) {
            Logger.getLogger(KategoriConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, 
            String id) {
        return kategoriService.kategoriDenganId(Long.valueOf(id));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Kategori k = (Kategori) value;
        return k.getId().toString();
    }
    
}
