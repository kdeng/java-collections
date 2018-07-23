package nz.net.osnz;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import nz.net.osnz.common.jackson.JacksonHelper;
import nz.net.osnz.dto.UserDTO;
import nz.net.osnz.model.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphQLQueryIntegrationTest {

    @Autowired
    GraphQLSchema schema;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void verifySchema() {
        assertNotNull(schema);
    }

    @Test
    public void shouldReturnBadRequest() {
        ResponseEntity<String> response = restTemplate.postForEntity("/graphql", "{}", String.class);
        String message = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    public void shouldReturnUserList() {
        String request = "{\n" +
            "\t\"query\": \"{users{id}}\"\n" +
            "}";
        ResponseEntity<String> response = restTemplate.postForEntity("/graphql", request, String.class);
        String message = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void shouldAddNewUserSuccessfully() {
        String request = "{\n" +
            "\"query\":\"mutation {user(name:\\\"dkf\\\", email:\\\"dkf@asd.com\\\"){id name}}\"," +
            "\"variables\":null" +
            "}";
        ResponseEntity<String> response = restTemplate.postForEntity("/graphql", request, String.class);
        String message = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat("Response should contain name", message, CoreMatchers.containsString("\"name\":\"dkf\""));
    }

    @Test
    public void shouldExecuteProperly() {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query("query { user(id:1) { id name } }")
            .build();


        GraphQL graphQL = GraphQL.newGraphQL(schema).build();

        ExecutionResult executionResult = graphQL.execute(executionInput);


        User user = JacksonHelper.deserialize(JacksonHelper.serialize(executionResult.getData()), UserDTO.class).getUser();
        List<GraphQLError> errors = executionResult.getErrors();

        Assert.assertEquals("No error", 0, errors.size());
        Assert.assertNotNull(user);
        Assert.assertEquals("ID", 1, user.getId());


    }


}
