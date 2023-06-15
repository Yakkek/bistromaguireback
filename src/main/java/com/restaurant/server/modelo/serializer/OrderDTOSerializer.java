package com.restaurant.server.modelo.serializer;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.restaurant.server.modelo.DTO.ItemDTO;
import com.restaurant.server.modelo.DTO.PedidoDTO;

public class OrderDTOSerializer extends JsonSerializer<PedidoDTO> {

    @Override
    public void serialize(PedidoDTO orderDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", orderDTO.getId());
        jsonGenerator.writeNumberField("valor", orderDTO.getValor());
        jsonGenerator.writeStringField("data", orderDTO.getData().toString());
        jsonGenerator.writeStringField("cliente", orderDTO.getCliente());
        List<ItemDTO> items = orderDTO.getItens();
        jsonGenerator.writeArrayFieldStart("itens");
        for (ItemDTO item : items) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("nome", item.getNome());
            jsonGenerator.writeNumberField("quantidade", item.getQuantidade());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
