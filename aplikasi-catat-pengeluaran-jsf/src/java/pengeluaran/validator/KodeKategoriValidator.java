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

package pengeluaran.validator;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import pengeluaran.service.KategoriServiceLocal;

/**
 *
 * @author endy
 */
@FacesValidator("pengeluaran.validator.KodeKategoriValidator")
public class KodeKategoriValidator implements Validator {
    
    private KategoriServiceLocal kategoriService;

    public KodeKategoriValidator() {
        try {
            String ejbName = "java:global/aplikasi-catat-pengeluaran/aplikasi-catat-pengeluaran-ejb/KategoriService";
            Context ctx = new InitialContext();
            kategoriService = (KategoriServiceLocal) ctx.lookup(ejbName);
        } catch (NamingException ex) {
            Logger.getLogger(KodeKategoriValidator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String kode = (String) value;
        if (kategoriService.kategoriDenganKode(kode) != null) {
            FacesMessage msg = new FacesMessage("Kode Kategori Error",
                    "Kode Kategori sudah terpakai");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
