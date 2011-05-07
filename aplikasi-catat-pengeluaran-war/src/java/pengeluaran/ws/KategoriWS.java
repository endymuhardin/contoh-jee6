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

package pengeluaran.ws;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import pengeluaran.entity.Kategori;
import pengeluaran.service.KategoriServiceLocal;

/**
 *
 * @author endy
 */
@WebService(serviceName = "KategoriWS")
public class KategoriWS {
    @EJB
    private KategoriServiceLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "simpan")
    @Oneway
    public void simpan(@WebParam(name = "kategori")
    Kategori kategori) {
        ejbRef.simpan(kategori);
    }

    @WebMethod(operationName = "semuaKategori")
    public List<Kategori> semuaKategori() {
        return ejbRef.semuaKategori();
    }

    @WebMethod(operationName = "kategoriDenganKode")
    public Kategori kategoriDenganKode(@WebParam(name = "kode")
    String kode) {
        return ejbRef.kategoriDenganKode(kode);
    }

    @WebMethod(operationName = "kategoriDenganId")
    public Kategori kategoriDenganId(@WebParam(name = "id")
    Long id) {
        return ejbRef.kategoriDenganId(id);
    }
    
}
