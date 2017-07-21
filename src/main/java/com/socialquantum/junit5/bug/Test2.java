package com.socialquantum.junit5.bug;

import org.junit.jupiter.api.Test;

/**
 * Created 21/07/17 16:18
 *
 * @author Vladimir Bogodukhov 
 **/
class Test2 {

    @Test
    void test2() {
        SingleList.MESSSAGES.add("test2()");
    }

      
}
