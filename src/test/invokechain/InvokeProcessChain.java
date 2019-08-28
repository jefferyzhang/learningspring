package test.invokechain;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author chuanzhang
 * Email chuanzhang@uoko.com
 * created at 2019 - 08 - 28 15:19
 */
public class InvokeProcessChain implements InvokeProcess{

    private List<Invoker> invokers;
    private InvokeProcess target;

    public InvokeProcessChain(List<Invoker> invokers, InvokeProcess target) {
        this.invokers = invokers;
        this.target = target;
    }

    private int curInvokerIndex=0;

    @Override
    public void process() {
        if (curInvokerIndex >= invokers.size()) {
            target.process();
            return;
        }
        Invoker invoker = invokers.get(curInvokerIndex);
        curInvokerIndex++;
        invoker.invoke(this);
    }
}
