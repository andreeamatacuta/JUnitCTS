package test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import interfaces.ITren;
import model.Culoare;
import model.OprescNormal;
import model.OpresteDes;
import model.TrenFactory;
import model.TrenInterRegio;

public class TestCaseStrategy {
    static ITren trenVerde;
    static ITren trenNegru;

    @BeforeClass
    public static void init() {
	trenVerde = TrenFactory.getTrenInterRegio(Culoare.VERDE, 1678);
	trenNegru = TrenFactory.getTrenInterRegio(Culoare.NEGRU, 1588);
    }

    @Test
    public void testStrategyTrenVerde() {
	System.out.println("\nImplementare strategie pentru trenul verde");
	((TrenInterRegio) trenVerde).setStrategie(new OpresteDes());
	((TrenInterRegio) trenVerde).folosesteStrategie();
	Assert.assertTrue(((TrenInterRegio) trenVerde).strategie instanceof OpresteDes);
    }

    @Test
    public void testStrategyTrenNegru() {
	System.out.println("\nImplementare strategie pentru trenul negru");
	((TrenInterRegio) trenNegru).setStrategie(new OprescNormal());
	((TrenInterRegio) trenNegru).folosesteStrategie();
	Assert.assertFalse(((TrenInterRegio) trenNegru).strategie instanceof OpresteDes);
    }
}
