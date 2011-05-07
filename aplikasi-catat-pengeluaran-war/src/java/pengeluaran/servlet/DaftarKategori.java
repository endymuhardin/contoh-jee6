/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pengeluaran.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pengeluaran.entity.Kategori;
import pengeluaran.service.KategoriServiceLocal;

/**
 *
 * @author endy
 */
public class DaftarKategori extends HttpServlet {

    @EJB private KategoriServiceLocal kategoriService;
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Daftar Kategori</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Daftar Kategori</h1>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr><th>Kode</th><th>Nama</th></tr>");
            out.println("</thead>");
            out.println("<tbody>");
            
            for (Kategori kategori : kategoriService.semuaKategori()) {
                out.println("<tr><td>"+kategori.getKode()
                        +"</td><td>"+kategori.getNama()+"</td></tr>");
            }
            
            out.println("</tbody>");
            
            out.println("</table>");
            
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
