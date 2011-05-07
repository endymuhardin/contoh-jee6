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

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pengeluaran.entity.Kategori;
import pengeluaran.entity.Transaksi;

/**
 *
 * @author endy
 */
@Stateless
@LocalBean
public class TransaksiService {
    @PersistenceContext(unitName = "aplikasi-catat-pengeluaran-ejbPU")
    private EntityManager em;
    
    public void simpan(Transaksi t) {
        em.persist(t);
    }

    public Long hitungTransaksi(Date mulai, Date sampai){
        String q = "select count(t) from Transaksi t "
                + "where t.waktu between :mulai and :sampai";
        Long hasil = (Long) em.createQuery(q)
                .setParameter("mulai", mulai)
                .setParameter("sampai", sampai)
                .getSingleResult();
        return hasil;
    }
    
    public List<Transaksi> cariTransaksi
            (Date mulai, Date sampai, Integer start, Integer rows){
        String q = "select t from Transaksi t "
                + "where t.waktu between :mulai and :sampai "
                + "order by t.waktu";
        List<Transaksi> hasil = em.createQuery(q)
                .setParameter("mulai", mulai)
                .setParameter("sampai", sampai)
                .setFirstResult(start)
                .setMaxResults(rows)
                .getResultList();
        return hasil;
    }
    
    public Long hitungTransaksi(Kategori k, Date mulai, Date sampai){
        String q = "select count(t) from Transaksi t "
                + "where t.kategori.id = :kategoriId "
                + "and t.waktu between :mulai and :sampai";
        return 0L;
    }
    
    public List<Transaksi> cariTransaksi
            (Kategori k, Date mulai, Date sampai, Integer start, Integer rows){
        String q = "select t from Transaksi t "
                + "where t.kategori.id = :kategoriId "
                + "and t.waktu between :mulai and :sampai";
        return null;
    }
}
