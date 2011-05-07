/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
