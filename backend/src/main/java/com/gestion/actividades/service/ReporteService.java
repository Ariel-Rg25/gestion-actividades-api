package com.gestion.actividades.service;

import com.gestion.actividades.model.Proyecto;
import com.gestion.actividades.repository.ProyectoRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    public ByteArrayInputStream generarReporte() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph("Reporte de Proyectos", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(Chunk.NEWLINE);

            
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            
            
            String[] headers = {"Título", "Estado", "Prioridad"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h));
                cell.setBackgroundColor(java.awt.Color.LIGHT_GRAY);
                table.addCell(cell);
            }

            
            List<Proyecto> proyectos = proyectoRepository.findAll();
            for (Proyecto p : proyectos) {
                table.addCell(p.getTitulo());
                table.addCell(p.getEstado());
                table.addCell(p.getPrioridad());
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}