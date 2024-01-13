package com.bookstore.main.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ERoleTest {
    @Test
    void testValueOf() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by valueOf(String)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        ERole.valueOf("ROLE_ADMIN");
    }

    @Test
    void testValues() {
        ERole[] actualValuesResult = ERole.values();
        assertEquals(3, actualValuesResult.length);
        assertEquals(ERole.ROLE_USER, actualValuesResult[0]);
        assertEquals(ERole.ROLE_MODERATOR, actualValuesResult[1]);
        assertEquals(ERole.ROLE_ADMIN, actualValuesResult[2]);
    }
}

