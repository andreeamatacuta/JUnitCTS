package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import interfaces.ITren;
import model.Calator;
import model.CalatorBuilder;
import model.Culoare;
import model.TrenFactory;
import model.TrenInterRegio;

public class TestCaseScenariu {
    static ITren trenGalben;
    static CalatorBuilder builder;
    static Calator D;
    static double reducere;

    @BeforeClass
    public static void init() {
	trenGalben = TrenFactory.getTrenInterRegio(Culoare.GALBEN, 1678);
	builder = new CalatorBuilder();
	D = builder.setNume("D").setPret(34.5).setAreReducere(true).setTelefon("074048842").setPensionar(true).build();
    }

    @Test
    public void testAbonareSiDezabonare() {
	Assert.assertTrue(((TrenInterRegio) trenGalben).observatori.isEmpty());
	((TrenInterRegio) trenGalben).abonareObservator(D);
	Assert.assertFalse(((TrenInterRegio) trenGalben).observatori.isEmpty());
    }

    @Test
    public void testLipsaNotificare() {

	((TrenInterRegio) trenGalben).dezabonareObserver(D);
	trenGalben.pleaca();
	Assert.assertNotEquals("PRIN EMAIL", D.tipNotificare);
    }

    @Test
    public void testReducerePretBilet() {
	double reducere = D.aplicaReducere(D.pret);
	Assert.assertTrue(D.pret == 34.5);
	assertEquals(27.6, reducere, 0.5);
    }

}
