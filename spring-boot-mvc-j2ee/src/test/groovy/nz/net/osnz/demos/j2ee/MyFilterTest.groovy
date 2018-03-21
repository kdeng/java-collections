package nz.net.osnz.demos.j2ee

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringRunner)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyFilterTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testFilterEndpoint() {
        String body = this.restTemplate.getForObject("/my-filter", String.class)
        Assert.assertEquals("Block by filter", body)
    }

}
