package com.bookstore.main.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EOrderStatusTest {
    @Test
    void testValueOf() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by valueOf(String)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        EOrderStatus.valueOf("DELIVERED");
    }

    @Test
    void testValues() {
        EOrderStatus[] actualValuesResult = EOrderStatus.values();
        assertEquals(2, actualValuesResult.length);
        assertEquals(EOrderStatus.PROCESSING, actualValuesResult[0]);
        assertEquals(EOrderStatus.DELIVERED, actualValuesResult[1]);
    }
}

