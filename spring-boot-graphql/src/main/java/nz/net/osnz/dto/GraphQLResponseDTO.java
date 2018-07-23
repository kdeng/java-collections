package nz.net.osnz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import graphql.GraphQLError;

import java.util.List;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphQLResponseDTO<T> {

    private T data;

    private List<GraphQLError> errors;

}
