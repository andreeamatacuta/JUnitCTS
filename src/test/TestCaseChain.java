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

public class TestCaseChain {
    static CalatorBuilder builder;
    static Calator A;
    static Calator B;
    static Calator C;
    private static ITren trenAlbastru;

    @BeforeClass
    public static void init() {
	trenAlbastru = TrenFactory.getTrenInterRegio(Culoare.ALBASTRU, 1533);

	builder = new CalatorBuilder();
	A = builder.setNume("A").setAreReducere(true).setTelefon("0745123456").setEmail("ad@yahoo.com").build();
	builder = new CalatorBuilder();
	B = builder.setNume("B").setPensionar(false).build();
	builder = new CalatorBuilder();
	C = builder.setNume("C").setVarsta(25).setEmail("a@a.com").build();

	((TrenInterRegio) trenAlbastru).abonareObservator(A);
	((TrenInterRegio) trenAlbastru).abonareObservator(B);
	((TrenInterRegio) trenAlbastru).abonareObservator(C);
    }

    @Test
    public void testChain() {
	System.out.println("\nTestare chain of responsability");
	trenAlbastru.pleaca();
	Assert.assertEquals("PRIN TELEFON", A.tipNotificare);
	Assert.assertEquals("PRIN EMAIL", C.tipNotificare);
	Assert.assertEquals(null, B.tipNotificare);
    }

}
