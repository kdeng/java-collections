package io.osnz;

import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class ExpressionParserHelper {

  public static final ExpressionParser PARSER = new SpelExpressionParser();

  public static final TemplateParserContext TEMPLATE_PARSER_CONTEXT = new TemplateParserContext();

  public static final MapAccessor MAP_ACCESSOR = new MapAccessor();

  public static String parseTemplate(String message, Map<String, Object> model) {
    StandardEvaluationContext context = new StandardEvaluationContext();
    context.setRootObject(model);
    context.addPropertyAccessor(MAP_ACCESSOR);
    return PARSER.parseExpression(message, TEMPLATE_PARSER_CONTEXT).getValue(context, String.class);
  }

  public static String parseExpression(String message, Map<String, Object> model) {
    StandardEvaluationContext context = new StandardEvaluationContext();
    context.setVariables(model);
    return PARSER.parseExpression(message).getValue(context, String.class);
  }

}
