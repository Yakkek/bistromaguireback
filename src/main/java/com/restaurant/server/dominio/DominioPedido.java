package com.restaurant.server.dominio;

import com.restaurant.server.modelo.*;
import com.restaurant.server.modelo.DTO.ItemDTO;
import com.restaurant.server.modelo.DTO.PedidoDTO;
import com.restaurant.server.modelo.DTO.PedidoItemRequestDto;
import com.restaurant.server.modelo.DTO.PedidoPdfDTO;
import com.restaurant.server.persistencia.PedidoPersistencia;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DominioPedido {

    private final PedidoPersistencia pedidoPersistencia;
    private final DominioCliente dominioCliente;
    private final DominioItem dominioItem;
    private final DominioPedidoItem dominioPedidoItem;


    public DominioPedido(PedidoPersistencia pedidoPersistencia, DominioCliente dominioCliente, DominioItem dominioItem, DominioPedidoItem dominioPedidoItem) {
        this.pedidoPersistencia = pedidoPersistencia;
        this.dominioCliente = dominioCliente;
        this.dominioItem = dominioItem;
        this.dominioPedidoItem = dominioPedidoItem;
    }

    @Transactional
    public Pedido criar(Long clienteId, List<PedidoItemRequestDto> itens) {

        //Busca e salva os itens em uma lista
        List<Item> listaItens = new ArrayList<>();

        itens.forEach(pedidoItemRequest -> {
            Item item = dominioItem.buscarItemId(pedidoItemRequest.getItemId());

            if (item == null) {
                throw new RuntimeException("Item não encontrado");
            } else {
                listaItens.add(item);
            }
        });

        //Busca e salva o cliente em uma variavel
        Cliente clientCriado = dominioCliente.buscarClientId(clienteId);

        if (clientCriado == null) {
            throw new RuntimeException("Client not found");
        }


        //Calcula o valor total do pedido
        Integer valor = 0;
        for (Item item : listaItens) {
            valor += item.getPreco() * itens.stream().filter(pedidoItemRequest -> pedidoItemRequest.getItemId().equals(item.getId())).findFirst().orElseThrow(() -> new RuntimeException("Pedido item não encontrado")).getQuantidade();
        }


        Pedido pedidoCriado = criarPedidoPuro(clientCriado, valor);

        List<PedidoItem> pedidoItensLista = new ArrayList<>();
        for (Item item : listaItens) {

            int quantity = itens.stream().filter(pedidoItemRequest -> pedidoItemRequest.getItemId().equals(item.getId())).findFirst().orElseThrow(() -> new RuntimeException("Pedido item não encontrado")).getQuantidade();

            PedidoItem orderItem = new PedidoItem();
            orderItem.setPedido(pedidoCriado);
            orderItem.setItem(item);
            orderItem.setQuantidade(quantity);

            Item itemToRemoveQuantity = dominioItem.buscarItemId(item.getId());
            dominioItem.remover(itemToRemoveQuantity.getId(), orderItem.getQuantidade());

            PedidoItem pedidoItensCriado = dominioPedidoItem.criar(orderItem);
            pedidoItensLista.add(pedidoItensCriado);
        }

        for (PedidoItem orderItem : pedidoItensLista) {
            pedidoCriado.getPedidoItens().add(orderItem);

            for (Item item : listaItens) {
                item.getPedidoItens().add(orderItem);
            }

        }

        Pedido orderFinal = pedidoPersistencia.save(pedidoCriado);

        dominioItem.criarTodos(listaItens);

        return orderFinal;

    }

    @Transactional
    public Pedido criarPedidoPuro(Cliente cliente, Integer valor) {

        Pedido pedido = new Pedido();
        pedido.setValor(valor);
        pedido.setData(LocalDateTime.now().toString());
        pedido.setCliente(cliente);

        return pedidoPersistencia.save(pedido);
    }

    public List<PedidoDTO> buscarTodos() {
        List<Pedido> pedidos = pedidoPersistencia.findAll();
        List<PedidoDTO> pedidoDTOs = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            PedidoDTO pedidoDTO = new PedidoDTO();
            pedidoDTO.setId(pedido.getId());
            pedidoDTO.setValor(pedido.getValor());
            pedidoDTO.setData(pedido.getData());
            pedidoDTO.setCliente(pedido.getCliente().getNome());

            List<ItemDTO> itemDTOs = new ArrayList<>();
            for (PedidoItem pedidoItem : pedido.getPedidoItens()) {

                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setNome(pedidoItem.getItem().getnome());
                itemDTO.setQuantidade(pedidoItem.getQuantidade());

                itemDTOs.add(itemDTO);
            }
            pedidoDTO.setItens(itemDTOs);

            pedidoDTOs.add(pedidoDTO);
        }

        return pedidoDTOs;
    }

    public List<PedidoPdfDTO> downloadPDF() {

        List<Pedido> todosPedidos = pedidoPersistencia.findAll();
        List<PedidoPdfDTO> pedidosPDF = new ArrayList<>();

        for (Pedido pedido : todosPedidos) {

            PedidoPdfDTO pedidoPdfDTO = new PedidoPdfDTO();

            pedidoPdfDTO.setidPedido(pedido.getId());

            pedidoPdfDTO.setValor(pedido.getValor() / 100);

            pedidoPdfDTO.setCliente(pedido.getCliente().getNome());

            String data = pedido.getData();
            String[] firstSplit = data.split("T");
            String[] secondtSplit = firstSplit[0].split("-");
            String createdAtFinal = secondtSplit[2] + "/" + secondtSplit[1] + "/" + secondtSplit[0];
            pedidoPdfDTO.setData(createdAtFinal);

            for (PedidoItem pedidoItem : pedido.getPedidoItens()) {

                Integer quantity = pedidoItem.getQuantidade();

                if (pedidoPdfDTO.getQuantidadeItens() != null) {
                    pedidoPdfDTO.setQuantidadeItens(pedidoPdfDTO.getQuantidadeItens() + quantity);
                }
                else {
                    pedidoPdfDTO.setQuantidadeItens(quantity);
                }

            }

            pedidosPDF.add(pedidoPdfDTO);
        }

        return pedidosPDF;

    }
}