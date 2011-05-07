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

package pengeluaran.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import pengeluaran.entity.Kategori;

/**
 *
 * @author endy
 */
@Stateless
public class KategoriService implements KategoriServiceLocal {
    @PersistenceContext(unitName = "aplikasi-catat-pengeluaran-ejbPU")
    private EntityManager em;

    @Override
    public void simpan(Kategori kategori) {
        if(kategori.getId() == null) {
            em.persist(kategori);
        } else {
            em.merge(kategori);
        }
    }
    
    @Override
    public List<Kategori> semuaKategori() {
        String jpaQL = "select k from Kategori k order by k.kode";
        List<Kategori> hasil = 
                em.createQuery(jpaQL)
                .getResultList();
        return hasil;
    }
    
    @Override
    public Kategori kategoriDenganKode(String kode){
        try {
            Kategori k = (Kategori) em
                    .createQuery("select k from Kategori k where k.kode = :kode")
                    .setParameter("kode", kode)
                    .getSingleResult();
            return k;
        } catch (NoResultException err){
            return null;
        }
    }
    
    @Override
    public Kategori kategoriDenganId(Long id){
        Kategori k = em.find(Kategori.class, id);
        return k;
    }
    
}
