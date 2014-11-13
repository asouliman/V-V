import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuite {

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(Appearence.class);
    suite.addTestSuite(UserRights.class);
    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
