package com.socialquantum.junit5.bug;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created 21/07/17 16:18
 *
 * @author Vladimir Bogodukhov 
 **/
class Test3WithWrongBeforeEach {

    @BeforeEach
    static void beforeEachWithWrongStaticModifier() {
    }

    @Test
    void test3() {
        SingleList.MESSSAGES.add("test3()");
    }

      
}
