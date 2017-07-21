package com.socialquantum.junit5.bug;

import org.junit.jupiter.api.Assertions;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created 21/07/17 16:12
 *
 * @author Vladimir Bogodukhov
 **/
public class WrongModifierBug {

    public static void main(String[] args) {
        {
            Launcher launcher = LauncherFactory.create();
            LauncherDiscoveryRequest build = LauncherDiscoveryRequestBuilder.request().
                    selectors(DiscoverySelectors.selectClass(Test1.class)). /* Added two  */
                    selectors(DiscoverySelectors.selectClass(Test2.class))  /* well formed classes */
                    .build();

            SingleList.MESSSAGES.clear(); /* Clear the result */
            launcher.execute(build);

            /* here we have a correct result */
            Assertions.assertEquals(new HashSet<>(Arrays.asList("test1()", "test2()")), SingleList.MESSSAGES);
        }

        {
            Launcher launcher = LauncherFactory.create();

            LauncherDiscoveryRequest build = LauncherDiscoveryRequestBuilder.request().
                    selectors(DiscoverySelectors.selectClass(Test1.class))./* Added two  */
                    selectors(DiscoverySelectors.selectClass(Test2.class))./* well formed classes */

                    /* and one class that has static modifier in a method annotated @BeforeEach */
                    selectors(DiscoverySelectors.selectClass(Test3WithWrongBeforeEach.class))
                    .build();

            SingleList.MESSSAGES.clear();/* Clear the result */
            launcher.execute(build);




            /*
            * it is logical that if one test class has an error it musttn't ruin the execution of other tests
            * And those classes that are "well formed" tests must be executed.  However it is not so.
            * and as result we have an empty list.
            * */
            Assertions.assertEquals(new HashSet<>(Arrays.asList("test1()", "test2()")), SingleList.MESSSAGES);
        }

    }
}
