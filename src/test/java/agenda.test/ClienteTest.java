package agenda.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import Agenda.*;
import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;

public class ClienteTest{
    @BeforeClass
    public static void init() {
        Cliente cliente1 = new Cliente("Marcos", "0001", direccion1, "al375909@uji.es", 1);
    }

    @AfterClass
    @Test
    public void ModificarTarifa() {

        assertThat(cliente1.getTipoTarifa(), is(1));
        cliente1.cambiarTarifa(2);
        assertThat(cliente1.getTipoTarifa(), is(2));

    }




}