package net.ssehub.sparky.registry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Simple registry test class.
 * 
 * @author spark
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RegistryTests {

    /**
     * Test for loading springs application context (test for bean consistency).
     */
    @Test
    public void contextLoads() {
    }

}