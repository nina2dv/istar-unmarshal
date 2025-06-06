package ca.yorku.cmg.istardt.xmlparser.xml.deserializers;

import ca.yorku.cmg.istardt.xmlparser.objects.Condition;
import ca.yorku.cmg.istardt.xmlparser.objects.Formula;
import ca.yorku.cmg.istardt.xmlparser.xml.utils.DeserializerUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Deserializer for Condition objects.
 */
public class ConditionDeserializer extends BaseDeserializer<Condition> {
    private static final Logger LOGGER = Logger.getLogger(ConditionDeserializer.class.getName());

    public ConditionDeserializer() {
        super(Condition.class);
    }

    @Override
    protected Condition createNewElement() {
        return new Condition();
    }

    @Override
    protected void handleSpecificAttributes(Condition condition, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        Formula formula = null;

        try {
            if (node.has("formula")) {
                formula = ctxt.readValue(node.get("formula").traverse(p.getCodec()), Formula.class);
            }
        } catch (IOException e) {
            DeserializerUtils.handleDeserializationError(LOGGER,
                    "Error processing formula for condition " + condition.getAtom().getTitleText(), e);
        }

        // Set the formula if found
        if (formula != null) {
            condition.setValueFormula(formula);
        }
    }
}