/*
 * Copyright (C) 2011 Endy Muhardin <endy.muhardin@gmail.com>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
