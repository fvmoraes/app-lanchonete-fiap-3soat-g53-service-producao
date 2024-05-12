package producaocom.fiap.lanchonete.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.StatusPagamento;

public class StatusPagamentoTest {

    @Test
    public void testEnumValues() {
        assertEquals("Pago", StatusPagamento.Pago.name());
        assertEquals("EsperandoConfirmação", StatusPagamento.PENDENTE.name());
        assertEquals("Cancelado", StatusPagamento.Cancelado.name());
    }

    @Test
    public void testEnumOrdinal() {
        assertEquals(0, StatusPagamento.Pago.ordinal());
        assertEquals(1, StatusPagamento.PENDENTE.ordinal());
        assertEquals(2, StatusPagamento.Cancelado.ordinal());
    }

    @Test
    public void testEnumToString() {
        assertEquals("Pago", StatusPagamento.Pago.toString());
        assertEquals("EsperandoConfirmação", StatusPagamento.PENDENTE.toString());
        assertEquals("Cancelado", StatusPagamento.Cancelado.toString());
    }
}
