package producaocom.fiap.lanchonete.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.StatusPedido;

public class StatusPagamento {

    @Test
    public void testEnumValues() {
        assertEquals("RECEBIDO", StatusPedido.RECEBIDO.name());
        assertEquals("PREPARACAO", StatusPedido.PREPARACAO.name());
        assertEquals("PRONTO", StatusPedido.PRONTO.name());
        assertEquals("FINALIZADO", StatusPedido.FINALIZADO.name());
    }

    @Test
    public void testEnumOrdinal() {
        assertEquals(0, StatusPedido.RECEBIDO.ordinal());
        assertEquals(1, StatusPedido.PREPARACAO.ordinal());
        assertEquals(2, StatusPedido.PRONTO.ordinal());
        assertEquals(3, StatusPedido.FINALIZADO.ordinal());
    }

    @Test
    public void testEnumToString() {
        assertEquals("RECEBIDO", StatusPedido.RECEBIDO.toString());
        assertEquals("PREPARACAO", StatusPedido.PREPARACAO.toString());
        assertEquals("PRONTO", StatusPedido.PRONTO.toString());
        assertEquals("FINALIZADO", StatusPedido.FINALIZADO.toString());
    }
}
