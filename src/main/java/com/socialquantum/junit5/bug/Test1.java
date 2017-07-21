package com.socialquantum.junit5.bug;

import org.junit.jupiter.api.Test;

/**
 * Created 21/07/17 16:18
 *
 * @author Vladimir Bogodukhov 
 **/
class Test1 {

    @Test
    void test1() {
        SingleList.MESSSAGES.add("test1()");
    }

      
}
