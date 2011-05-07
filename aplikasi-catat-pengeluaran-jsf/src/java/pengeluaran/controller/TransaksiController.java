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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pengeluaran.entity.Transaksi;
import pengeluaran.service.TransaksiService;


/**
 *
 * @author endy
 */
@ManagedBean
@SessionScoped
public class TransaksiController {
    @EJB private TransaksiService transaksiService;

    private List<Transaksi> daftarTransaksi = new ArrayList<Transaksi>();
    private Date mulai = new Date();
    private Date sampai = new Date();
    private Locale locale = new Locale("id");
    
    private Transaksi transaksi = new Transaksi();
    
    public String simpan(){
        transaksiService.simpan(transaksi);
        transaksi = new Transaksi();
        return null;
    }
    
    public String cari(){
        daftarTransaksi = transaksiService.cariTransaksi(mulai, sampai, 0, 
                transaksiService.hitungTransaksi(mulai, sampai).intValue());
        return "list?faces-redirect=true";
    }
    
    
    public String reset(){
        daftarTransaksi = new ArrayList<Transaksi>();
        return "list?faces-redirect=true";
    }
    
    public String batal(){
        transaksi = new Transaksi();
        return "form?faces-redirect=true";
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public List<Transaksi> getDaftarTransaksi() {
        return daftarTransaksi;
    }

    public void setDaftarTransaksi(List<Transaksi> daftarTransaksi) {
        this.daftarTransaksi = daftarTransaksi;
    }

    public Date getMulai() {
        return mulai;
    }

    public void setMulai(Date mulai) {
        this.mulai = mulai;
    }

    public Date getSampai() {
        return sampai;
    }

    public void setSampai(Date sampai) {
        this.sampai = sampai;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    
}
