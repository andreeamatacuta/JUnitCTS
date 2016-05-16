package test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import interfaces.ITren;
import model.Calator;
import model.CalatorBuilder;
import model.Culoare;
import model.TrenFactory;
import model.TrenInterRegio;
import model.TrenRegio;

public class TestCasePattern {

    static ITren trenVerde;
    static ITren trenRosu;
    static ITren trenVerdeClona;
    static ITren trenNull;
    static CalatorBuilder builder;
    static Calator A;
    static Calator B;
    static Calator C;

    @BeforeClass
    public static void init() {
	trenVerde = TrenFactory.getTrenInterRegio(Culoare.VERDE, 1533);
	trenRosu = TrenFactory.getTrenRegio(Culoare.ROSU, 1400);
	trenNull = TrenFactory.getTrenInterRegio(null, 1500);
	trenVerdeClona = TrenFactory.getTrenInterRegio(Culoare.VERDE, 1533);

	builder = new CalatorBuilder();
	A = builder.setNume("A").setAreReducere(true).setTelefon("0745123456").build();
	builder = new CalatorBuilder();
	B = builder.setNume("B").setPensionar(false).build();
	builder = new CalatorBuilder();
	C = builder.setNume("C").setVarsta(25).setEmail("a@a.com").build();

	((TrenInterRegio) trenVerde).abonareObservator(A);
	((TrenInterRegio) trenVerde).abonareObservator(C);
	((TrenRegio) trenRosu).abonareObservator(B);

	trenVerde.pleaca();
	trenRosu.soseste();
    }

    @Test
    public void testFactory() {
	Assert.assertNotNull(trenVerde);
	Assert.assertNotNull(trenRosu);
	Assert.assertNull(trenNull);
	Assert.assertTrue(trenVerde instanceof TrenInterRegio);
	Assert.assertTrue(trenRosu instanceof TrenRegio);
    }

    @Test
    public void testObserver() {
	Assert.assertEquals("Trenul IR numarul 1533 pleaca din gara", A.notificare);
	Assert.assertEquals("Trenul IR numarul 1533 pleaca din gara", C.notificare);
	Assert.assertEquals(null, B.notificare);
    }

    @Test
    public void testBuilder() {
	Assert.assertTrue(A.areReducere == true);
	Assert.assertTrue(B.pensionar == false);
	Assert.assertTrue(C.varsta == 25);
    }

    @Test
    public void testReference() {
	Assert.assertEquals(trenVerdeClona, trenVerde);
	Assert.assertNotEquals(trenRosu, trenVerde);
    }

}
