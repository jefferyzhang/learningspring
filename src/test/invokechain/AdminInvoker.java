package test.invokechain;

/**
* <p>
*
* </p>
*
* @author chuanzhang
* Email chuanzhang@uoko.com
* created at 2019 - 08 - 28 15:18
*/
public class AdminInvoker implements Invoker {


   @Override
   public void invoke(InvokeProcess invokeProcess) {
       System.out.println("Admin Invoking");

       invokeProcess.process();

       System.out.println("Admin Invoked");
   }
}
