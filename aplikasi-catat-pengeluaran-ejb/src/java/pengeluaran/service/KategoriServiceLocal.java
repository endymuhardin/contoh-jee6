/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pengeluaran.service;

import java.util.List;
import javax.ejb.Local;
import pengeluaran.entity.Kategori;

/**
 *
 * @author endy
 */
@Local
public interface KategoriServiceLocal {

    public void simpan(Kategori kategori);
    public List<Kategori> semuaKategori();
    public Kategori kategoriDenganKode(String kode);
    public Kategori kategoriDenganId(Long id);
    
}
