package test.invokechain;

/**
 * <p>
 *
 * </p>
 *
 * @author chuanzhang
 * Email chuanzhang@uoko.com
 * created at 2019 - 08 - 28 15:19
 */
public class BossInvoker implements Invoker{



    @Override
    public void invoke(InvokeProcess invokeProcess) {
        System.out.println("Boss Invoking");

        invokeProcess.process();

        System.out.println("Boss Invoked");
    }
}
