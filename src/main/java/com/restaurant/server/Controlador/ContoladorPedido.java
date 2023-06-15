package com.restaurant.server.Controlador;

import com.restaurant.server.dominio.DominioPedido;
import com.restaurant.server.modelo.DTO.PedidoRequestDTO;
import com.restaurant.server.modelo.DTO.PedidoDTO;
import com.restaurant.server.modelo.DTO.PedidoPdfDTO;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pedido")
@CrossOrigin(origins = "*")
public class ContoladorPedido {

    private final DominioPedido orderService;

    public ContoladorPedido(DominioPedido orderService) {
        this.orderService = orderService;
    }

    @PostMapping("criar")
    public ResponseEntity<String> criar(@RequestBody PedidoRequestDTO pedidoRequestDTO) {

        try {
            orderService.criar(pedidoRequestDTO.getClienteId(), pedidoRequestDTO.getItens());
            return ResponseEntity.ok("{\"message\": \"Pedido criado\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("buscar-todos")
    public ResponseEntity<?> buscarTodos() {
        
        try {
            
            List<PedidoDTO> pedidoDTOs = orderService.buscarTodos();
            System.out.println(pedidoDTOs.get(0).getCliente());
            return ResponseEntity.ok(pedidoDTOs);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("download")
    public void download(HttpServletResponse response) throws JRException, IOException {

        List<PedidoPdfDTO> orders = orderService.downloadPDF();

        String filePath = "src\\main\\resources\\templates\\orderReport.jrxml";

        JasperReport report = JasperCompileManager.compileReport(filePath);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("orderDataSet", dataSource);

        JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=relatorio.pdf");

        try (OutputStream out = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(print, out);
            out.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
