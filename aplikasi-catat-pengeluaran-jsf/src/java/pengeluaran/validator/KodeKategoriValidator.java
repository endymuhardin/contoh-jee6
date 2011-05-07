/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pengeluaran.validator;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
