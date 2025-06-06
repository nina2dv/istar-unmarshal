package ca.yorku.cmg.istardt.xmlparser.objects;

/**
 * Implementation of the Condition class
 * In the XML schema, Conditions correspond to PreBox elements
 */
public class Condition extends NonDecompositionElement {
    public Condition() {
        super();
    }

    /**
     * Constructor with ID and description
     * @param id The unique identifier for this condition
     * @param description A human-readable description of this condition
     */
    public Condition(String id, String description) {
        super();
        this.setId(id);

        Atom atom = new Atom();
        atom.setId(id);
        atom.setTitleText(description);
        this.setRepresentation(atom);
    }

    @Override
    public Formula getFormula() {
        return super.getFormula();
    }
}