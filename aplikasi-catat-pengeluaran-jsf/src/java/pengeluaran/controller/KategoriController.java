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

package pengeluaran.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pengeluaran.entity.Kategori;
import pengeluaran.service.KategoriServiceLocal;

/**
 *
 * @author endy
 */
@ManagedBean(name="kategoriController")
@SessionScoped
public class KategoriController {
    @EJB private KategoriServiceLocal kategoriService;
    private DataModel<Kategori> listModelKategori;
    private List<Kategori> listKategori;
    private Kategori kategori = new Kategori();

    @PostConstruct
    public void initSemuaKategori(){
        listKategori = kategoriService.semuaKategori();
        listModelKategori = new ListDataModel<Kategori>(listKategori);
    }
    
    public String simpan(){
        kategoriService.simpan(kategori);
        kategori = new Kategori();
        initSemuaKategori();
        return "list?faces-redirect=true";
    }
    
    public DataModel<Kategori> getSemuaKategori(){
        return listModelKategori;
    }
    
    public String edit(){
        kategori = listModelKategori.getRowData();
        return "form?faces-redirect=true";
    }
    
    public String batal(){
        kategori = new Kategori();
        return "list?faces-redirect=true";
    }
    
    public String tambah(){
        kategori = new Kategori();
        return "form?faces-redirect=true";
    }
    
    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }
}
